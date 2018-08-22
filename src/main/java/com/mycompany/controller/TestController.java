package com.mycompany.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
 
    @RequestMapping("/test/hw")
    public String helloWorld(){
        return "hello";//-WEB-INF/view/hello.jsp   
    }
    
    @RequestMapping("/")
    public String enquiryForm(){
     return "enq_form";//-WEB-INF/view/hello.jsp   
    }
    
    @RequestMapping("/test/ajax_test")
    public String tetsPackage(){
        return "test";//jsp   
    }
    
    @RequestMapping("/test/get_time")
    @ResponseBody
    public String getServerTime(){
        System.out.println("~~~~~~~~getServerTime()~~~~~~~~");
        Date d = new Date();
        return d.toString();   
    }

}