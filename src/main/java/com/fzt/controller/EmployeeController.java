package com.fzt.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fzt.common.BaseContext;
import com.fzt.entity.Employee;
import com.fzt.entity.R;
import com.fzt.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        log.info("登录");
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp= employeeService.getOne(queryWrapper);
        if(emp==null){
            return R.error("登陆失败");
        }
        if(!emp.getPassword().equals(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()))){
            return R.error("密码不正确");
        }
        if(emp.getStatus() == 0){
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        log.info("登出");
        if(request.getSession().getAttribute("employee")==null){
            return R.error("登出失败");
        }
        request.getSession().removeAttribute("employee");
        BaseContext.remove();
        return R.success("登出成功");
    }

    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工，员工信息：{}",employee.toString());
//        Long empId= (Long) request.getSession().getAttribute("employee");
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setUpdateUser(empId);
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page pageInfo=new Page(page,pageSize);
        LambdaQueryWrapper<Employee> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name).orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){


        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee emp = employeeService.getById(id);
        if(emp==null){
            return R.error("没有查询到对应员工信息");
        }
        return R.success(emp);
    }
}
