package com.factory.controller;

import com.factory.mapping.FinanceRepostory;
import com.factory.mapping.WorkerRepostory;
import com.factory.pojo.Finance;
import com.factory.pojo.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/finance")
public class FactoryController {

    @Autowired
    private FinanceRepostory financeRepostory;

    @Autowired
    private WorkerRepostory workerRepostory;

    @RequestMapping("/quick")
    @ResponseBody
    @Transactional
    public List<Finance> quick(){
        //读取后进行扫描看看finance数据库有没有空的
        List<Finance> finances=financeRepostory.findAll();

        deleFinance(finances);

        List<Finance> finances1=financeRepostory.findAll();

        return finances1;
    }

    public void deleFinance(List<Finance> finances){
        for (Finance finance:finances) {
            if (finance.getWorkers().isEmpty()){
                financeRepostory.delete(finance);
            }
        }
    }
    @RequestMapping("/deletefinance")
    @ResponseBody
    @Transactional
    public void deleteFinance(@RequestParam(value = "f_id") String f_id){
        Finance finance=new Finance();
        finance.setF_id(Long.parseLong(f_id));
        financeRepostory.delete(finance);
    }

    @RequestMapping("/text")
    @ResponseBody
    public List<Worker> text(){
        List<Worker> workers=workerRepostory.findAll();
        return workers;
    }

    @RequestMapping("/edit")
    @ResponseBody
    @Transactional
    public void edit(@RequestParam(value = "customer") String customer, @RequestParam(value = "orderdate") String orderdate,
                      @RequestParam(value = "deliverydate") String deliverydate,@RequestParam(value = "remarks") String remarks,
                      @RequestParam(value = "state") String state,@RequestParam(value = "arrears") String arrears,
                      @RequestParam(value = "nowdate" , required = false)String nowdate,
                      //修改id
                      @RequestParam(value = "f_id") String f_id,@RequestParam(value = "worker[0][w_id]") String w_id,
                      //材料0
                      @RequestParam(value = "worker[0][abrasives]") String abrasives0,@RequestParam(value = "worker[0][price]") String price0,
                      @RequestParam(value = "worker[0][machining]") String machining0,@RequestParam(value = "worker[0][length]") String length0,
                      @RequestParam(value = "worker[0][wide]") String wide0,@RequestParam(value = "worker[0][high]") String high0,
                      @RequestParam(value = "worker[0][quantity]") String quantity0,@RequestParam(value = "worker[0][material]") String material0
    ){
        //用来更新(可用)
        //需要 f_id 和 w_id

        //finance 数据库数据
        Finance finance=new Finance();
        finance.setCustomer(customer);
        finance.setRemarks(remarks);
        finance.setState(state);

        if (f_id!=""){
            finance.setF_id(Long.parseLong(f_id));
        }

        if (arrears !=""){
            finance.setArrears(Long.parseLong(arrears));
        }

        //--------------     时间转换   -------------------------------------------------------------
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        try {
            //下单日期
            if (nowdate!="" && nowdate != null){
                finance.setNowdate(format.parse(nowdate));
            }
            if (orderdate!=""){
                finance.setOrderdate(format1.parse(orderdate));
            }
            if (deliverydate!=""){
                finance.setDeliverydate(format1.parse(deliverydate));
            }
            //结束日期

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //-------------------------------------------------------------------------------------------
        //worker 数据库数据 顺便单条保存进数据库
        if (material0 !=""){
            Worker worker0 =new Worker();
            worker0.setMaterial(material0);
            worker0.setMachining(machining0);
            worker0.setAbrasives(abrasives0);
            if (w_id !=""){
                worker0.setW_id(Long.parseLong(w_id));
            }
            if (quantity0 !=""){
                worker0.setQuantity(Long.parseLong(quantity0));
            }
            if (high0 !=""){
                worker0.setHigh(Long.parseLong(high0));
            }
            if (length0 !="") {
                worker0.setLength(Long.parseLong(length0));
            }
            if (wide0 !=""){
                worker0.setWide(Long.parseLong(wide0));
            }
            if (price0 !="" && price0 !=null){
                worker0.setPrice(Long.parseLong(price0));
            }
            worker0.setFinance(finance);
            workerRepostory.save(worker0);
        }
    }

    @RequestMapping("/editArrears")
    @ResponseBody
    @Transactional
    public void editArrears(
            @RequestParam(value = "customer") String customer, @RequestParam(value = "orderdate") String orderdate,
            @RequestParam(value = "deliverydate") String deliverydate,@RequestParam(value = "remarks") String remarks,
            @RequestParam(value = "state") String state,@RequestParam(value = "arrears") String arrears,
            @RequestParam(value = "f_id") String f_id,@RequestParam(value = "nowdate" ,required = false) String nowdate,
            @RequestParam(value = "copystate" ,required = false) String copystate
    ){
        //finance 数据库数据
        Finance finance=new Finance();
        finance.setCustomer(customer);
        finance.setRemarks(remarks);
        finance.setCopystate(copystate);
        finance.setState(state);
        if (f_id!=""){
            finance.setF_id(Long.parseLong(f_id));
        }

        if (arrears !=""){
            finance.setArrears(Long.parseLong(arrears));
        }

        //--------------     时间转换   -------------------------------------------------------------
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        try {
            //下单日期
            if (orderdate!=""){
                finance.setOrderdate(format1.parse(orderdate));
            }
            if (deliverydate!=""){
                finance.setDeliverydate(format1.parse(deliverydate));
            }
            //结束日期
            if (nowdate!=""&& nowdate != null){
                finance.setNowdate(format.parse(nowdate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        financeRepostory.save(finance);
        //-------------------------------------------------------------------------------------------
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Transactional
    public void delete(@RequestParam(value = "w_id") String w_id){
            Worker worker=new Worker();
            worker.setW_id(Long.parseLong(w_id));
            workerRepostory.delete(worker);
    }

    @PostMapping("/add")
    @ResponseBody
    @Transactional
            // 用字符串接收数组 因为自定义类无法读取值  对象数组第一个为空
    public void add(//为finance单一数据
            @RequestParam(value = "customer") String customer, @RequestParam(value = "orderdate") String orderdate,
            @RequestParam(value = "deliverydate") String deliverydate,@RequestParam(value = "remarks") String remarks,
            @RequestParam(value = "state") String state,@RequestParam(value = "arrears") String arrears,
            @RequestParam("worker[][abrasives]") String[] abrasives,@RequestParam("worker[][price]") String[] prices,
            @RequestParam("worker[][machining]") String[] machinings,@RequestParam("worker[][material]") String[] materials,
            @RequestParam("worker[][length]") String[] lengths,@RequestParam("worker[][wide]") String[] wides,
            @RequestParam("worker[][high]") String[] highs,@RequestParam("worker[][quantity]") String[] quantitys
    ){
        //用来多对一保存

        //finance 数据库数据
        Finance finance=new Finance();
        finance.setCustomer(customer);
        finance.setRemarks(remarks);
        finance.setState(state);
        if (arrears !=""){
            finance.setArrears(Long.parseLong(arrears));
        }
        //--------------     时间转换   -------------------------------------------------------------
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            //下单日期
            if (orderdate!=""){
                finance.setOrderdate(format.parse(orderdate));
            }
            if (deliverydate!=""){
                finance.setDeliverydate(format.parse(deliverydate));
            }
            //结束日期

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //-------------------------------------------------------------------------------------------
        //开始对worker对象数组开始操作
        //多对一保存
        for (int i=1;i<materials.length;i++){
            Worker worker=new Worker();
            worker.setMaterial(materials[i]);
            worker.setMachining(machinings[i]);
            worker.setQuantity(Long.parseLong(quantitys[i]));
            worker.setHigh(Long.parseLong(highs[i]));
            worker.setLength(Long.parseLong(lengths[i]));
            worker.setWide(Long.parseLong(wides[i]));
            worker.setFinance(finance);
            worker.setAbrasives(abrasives[i]);
            worker.setPrice(Long.parseLong(prices[i]));
            workerRepostory.save(worker);
        }
    }
}
