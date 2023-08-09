package com.fzt.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzt.entity.Employee;
import com.fzt.mapper.EmployeeMapper;
import com.fzt.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
