package com.fc.service;

import com.fc.entity.User;
import com.fc.vo.ResultVO;

public interface UserService {
    //如果传id就查询单个，不传id就查询全部
    ResultVO getList(Integer pageNum, Integer pageSize, User user);

    //添加用户
    ResultVO add(User user);

    //修改用户信息
    ResultVO update(User user);

    //删除用户
    ResultVO delete(Long id);
}
