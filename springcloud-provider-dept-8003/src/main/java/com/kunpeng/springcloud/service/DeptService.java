package com.kunpeng.springcloud.service;

import com.kunpeng.springcloud.pojo.Dept;

import java.util.List;

public interface DeptService {
    boolean addDept(Dept dept);

    Dept queryDeptId(int id);

    List<Dept> queryAll();
}
