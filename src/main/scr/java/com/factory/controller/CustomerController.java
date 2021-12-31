package com.factory.controller;

import com.factory.mapping.CustomerRepostory;
import com.factory.pojo.Customer;
import com.factory.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepostory customerRepostory;

    @RequestMapping("/quick")
    @ResponseBody
    @Transactional
    public List<Customer> quickCustomer(){
        List<Customer> customers = customerRepostory.findAll();
        return customers;
    }

    @RequestMapping("/add")
    @ResponseBody
    @Transactional
    public void addCustomer(@RequestParam(value = "name")String name,@RequestParam(value = "phone")String phone){
        Customer customer =new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customerRepostory.save(customer);
    }

    @RequestMapping("/edit")
    @ResponseBody
    @Transactional
    public void editCustomer(@RequestParam(value = "name")String name, @RequestParam(value = "phone")String phone,
                             @RequestParam(value = "c_id")String c_id
    ){
        Customer customer =new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setC_id(Long.parseLong(c_id));
        customerRepostory.save(customer);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Transactional
    public void deleteCustomer(@RequestParam(value = "c_id")String c_id){
        Customer customer=new Customer();
        customer.setC_id(Long.parseLong(c_id));
        customerRepostory.delete(customer);
    }
}
