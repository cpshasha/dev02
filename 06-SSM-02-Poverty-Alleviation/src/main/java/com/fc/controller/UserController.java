package com.fc.controller;

import com.fc.entity.User;
import com.fc.service.UserService;
import com.fc.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    //如果传id就查询单个，不传id就查询全部
    @GetMapping("getlist")
    public ResultVO getList(@RequestParam(value = "pageNum" ,defaultValue = "1")Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize,
                            User user){
        return userService.getList(pageNum,pageSize,user);

    }

    //添加用户
    @PostMapping("add")
    public ResultVO add(@RequestBody User user){
        return userService.add(user);
    }

    //修改用户信息
    @PostMapping("update")
    public ResultVO update(@RequestBody User user){
        return userService.update(user);
    }

    //删除用户
    @GetMapping("delete")
    public ResultVO delete(Long id){
        return userService.delete(id);
    }

}
