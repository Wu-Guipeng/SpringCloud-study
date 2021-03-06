package com.kunpeng.springcloud.service;

import com.kunpeng.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.*;


@Service
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory=DeptClientServiceFallbackFactory.class) // 服务降级注册：fallbackFactory
public interface DeptClientService {

    @GetMapping("/dept/query/{id}")
    Dept queryById(@PathVariable("id") int id);

    @GetMapping("/dept/queryAll")
    List<Dept> queryAll();

    @PostMapping("/dept/add")
    boolean addDept(Dept dept);
}
