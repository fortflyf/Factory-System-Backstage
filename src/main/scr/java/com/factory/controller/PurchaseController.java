package com.factory.controller;

import com.factory.mapping.PurchaseRepostory;
import com.factory.pojo.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    PurchaseRepostory purchaseRepostory;



    @RequestMapping("/quick")
    @ResponseBody
    @Transactional
    public List<Purchase> quick(){
        List<Purchase> purchases = purchaseRepostory.findAll();
        return purchases;
    }

    @RequestMapping("/add")
    @ResponseBody
    @Transactional
    public void add(
            @RequestParam(value = "p_company") String p_company, @RequestParam("worker[][p_material]") String[] p_materials,
            @RequestParam("worker[][p_length]") String[] p_lengths,@RequestParam("worker[][p_wide]") String[] p_wides,
            @RequestParam("worker[][p_high]") String[] p_highs, @RequestParam("worker[][p_quantity]") String[] p_quantitys,
            @RequestParam("worker[][p_price]") String[] p_prices, @RequestParam("worker[][p_state]") String[] p_states,
            @RequestParam("worker[][p_orderdate]") String[] p_orderdates,@RequestParam( value="worker[][p_remarks]",required = false) String[] p_remarks,
            @RequestParam("worker[][p_nowdate]") String[] p_nowdates
    ){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0;i<p_materials.length;i++){
            if (p_materials[i] !=""){
                Purchase purchase=new Purchase();
                try {
                    //下单日期
                    if (p_orderdates[i]!=""){
                        purchase.setP_orderdate(format1.parse(p_orderdates[i]));

                    }
                    //防止数组越界（原因 有些数组没有 p_nowdates 所以导致访问越界）
                    if(p_nowdates.length>i){
                        if(p_nowdates[i] != "" && p_nowdates[i] != null){
                            purchase.setP_nowdate(format.parse(p_nowdates[i]));
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                purchase.setP_company(p_company);
                purchase.setP_material(p_materials[i]);
                purchase.setP_quantity(Long.parseLong(p_quantitys[i]));
                purchase.setP_high(Long.parseLong(p_highs[i]));
                purchase.setP_length(Long.parseLong(p_lengths[i]));
                purchase.setP_wide(Long.parseLong(p_wides[i]));
                purchase.setP_price(Long.parseLong(p_prices[i]));
                //防止数组越界（原因 有些数组没有 p_remarks 所以导致访问越界）
                if(p_remarks.length>i){
                    purchase.setP_remarks(p_remarks[i]);
                }
                purchase.setP_state(p_states[i]);
                purchaseRepostory.save(purchase);
            }

        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    @Transactional
    public void editPurchase(
            @RequestParam(value = "p_company") String p_company, @RequestParam(value = "p_orderdate") String p_orderdate,
            @RequestParam(value = "p_remarks") String p_remarks,@RequestParam(value = "p_id") String p_id,
            @RequestParam(value = "p_length") String p_length,@RequestParam(value = "p_material") String p_material,
            @RequestParam(value = "p_wide") String p_wide,@RequestParam(value = "p_high") String p_high,
            @RequestParam(value = "p_quantity") String p_quantity,@RequestParam(value = "p_price") String p_price,
            @RequestParam(value = "p_state") String p_state,@RequestParam("p_nowdate") String p_nowdate

    ){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
        Purchase purchase=new Purchase();
        try {
            //下单日期
            if (p_orderdate!=""){
                purchase.setP_orderdate(format1.parse(p_orderdate));
            }
            if (p_nowdate!="" && p_nowdate != null){
                purchase.setP_nowdate(format.parse(p_nowdate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        purchase.setP_company(p_company);
        purchase.setP_material(p_material);
        purchase.setP_quantity(Long.parseLong(p_quantity));
        purchase.setP_high(Long.parseLong(p_high));
        purchase.setP_length(Long.parseLong(p_length));
        purchase.setP_wide(Long.parseLong(p_wide));
        purchase.setP_price(Long.parseLong(p_price));
        purchase.setP_remarks(p_remarks);
        purchase.setP_state(p_state);
        purchase.setP_id(Long.parseLong(p_id));
        purchaseRepostory.save(purchase);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Transactional
    public void deletePurchase(@RequestParam(value = "p_id") String p_id){
        Purchase purchase=new Purchase();
        purchase.setP_id(Long.parseLong(p_id));
        purchaseRepostory.delete(purchase);
    }
}
