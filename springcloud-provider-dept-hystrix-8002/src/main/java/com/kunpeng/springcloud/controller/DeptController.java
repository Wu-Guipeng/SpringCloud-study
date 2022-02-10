package com.kunpeng.springcloud.controller;


import com.kunpeng.springcloud.pojo.Dept;
import com.kunpeng.springcloud.service.DeptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 服务熔断机制：在服务端进行处理，当请求为null的时候抛出异常，走hystrixGet方法
    @GetMapping("/dept/query/{id}")
//    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") int id){
        Dept dept = deptService.queryDeptId(id);

        if(dept == null){
            throw new RuntimeException("不存在该部门");
        }
        return  dept;
    }

    // 备用方法
    public Dept hystrixGet(@PathVariable("id") int id){
        return new Dept().setDeptno(id).setDname("不存在该部门").setDb_source("not database");
    }

}
