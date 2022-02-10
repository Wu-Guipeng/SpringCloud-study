package com.kunpeng.springcloud.controller;


import com.kunpeng.springcloud.pojo.Dept;
import com.kunpeng.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptConsumerController {
    //@Autowired
    //private RestTemplate restTemplate;
    // 启动ribbon之后，应该使用实例id去eureka中找服务器
    //private static final String REST_URL_PREFIX = "http://127.0.0.1:8001";
    //private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    @Autowired
    private DeptClientService service;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") int id){
        return service.queryById(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return service.queryAll();
    }

    @PostMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return service.addDept(dept);
    }
}
