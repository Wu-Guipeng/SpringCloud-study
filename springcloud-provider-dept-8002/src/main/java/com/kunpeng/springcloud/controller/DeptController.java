package com.kunpeng.springcloud.controller;

import com.kunpeng.springcloud.pojo.Dept;
import com.kunpeng.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        System.out.println(dept.getDname());
        return deptService.addDept(dept);
    }
    @GetMapping("/dept/query/{id}")
    public Dept queryDeptId(@PathVariable("id") int id){
        return deptService.queryDeptId(id);
    }
    @GetMapping("/dept/queryAll")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }
    @GetMapping("/dept/discovery")
    public Object discovery(){
        List<ServiceInstance> instances = client.getInstances("springcloud-provider-dept-8002");
        for (ServiceInstance instance : instances){
            System.out.println(
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getUri() + "\t" +
                    instance.getServiceId() + "\t" +
                    instance.getScheme()
            );
        }
        return this.client;
    }
}
