package com.kunpeng.springcloud.service;

import com.kunpeng.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientFeign {

    @GetMapping("/dept/get/{id}")
    Dept queryById(@PathVariable("id") int id);

    @GetMapping("/dept/list")
    List<Dept> queryAll();

    @PostMapping("/dept/add")
    boolean addDept(Dept dept);
}
