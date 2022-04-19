package com.fc.service.impl;

import com.fc.dao.UserMapper;
import com.fc.entity.User;
import com.fc.entity.UserExample;
import com.fc.service.UserService;
import com.fc.vo.DataVO;
import com.fc.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //如果传id就查询单个，不传id就查询全部
    @Override
    public ResultVO getList(Integer pageNum, Integer pageSize, User param) {
        List<User> users;

        ResultVO resultVO;
        try {
            if (param.getId() != null) {
                //单个学生
                User user = userMapper.selectByPrimaryKey(param.getId());
                users = new ArrayList<>();
                users.add(user);
            } else {
                //开启分页
                PageHelper.startPage(pageNum, pageSize);

                UserExample userExample = new UserExample();
                UserExample.Criteria criteria = userExample.createCriteria();

                if (param.getName()!=null) {
                    criteria.andNameLike("%" + param.getName() + "%");
                }
                if (param.getUsername()!=null){
                    criteria.andUsernameLike("%"+param.getUsername()+"%");
                }
                if (param.getGender()!=null){
                    criteria.andGenderEqualTo(param.getGender());
                }

                users = userMapper.selectByExample(userExample);
            }

            //分页信息
            PageInfo<User> pageInfo = new PageInfo<>(users);

            DataVO<User> dataVO = new DataVO<>(pageInfo.getTotal(), users, pageNum, pageSize);

            resultVO = new ResultVO(200, "成功", true, dataVO);
        } catch (Exception e) {
            resultVO = new ResultVO(-1, "失败", false, null);

        }

        return resultVO;
    }

    //添加用户
    @Override
    public ResultVO add(User user) {

        //如果用户的创建时间为空就给新建一个当前时间
        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        if(user.getPhoto()==null){
            user.setPhoto("https://tse4-mm.cn.bing.net/th/id/OIP-C.nQbX4992Y7diUq0T-LETNAHaHa?w=185&h=185&c=7&r=0&o=5&dpr=1.5&pid=1.7");
        }
        //设置一下主键回填
        int affectedRows = userMapper.insertSelective(user);

        ResultVO resultVO;

        if (affectedRows > 0) {
            resultVO = new ResultVO(200, "成功", true, user);
        } else {
            resultVO = new ResultVO(-1, "失败", false, null);
        }

        return resultVO;
    }

    //修改用户信息
    @Override
    public ResultVO update(User user) {
        int affectedRows = userMapper.updateByPrimaryKeySelective(user);

        ResultVO resultVO;

        if (affectedRows > 0) {

            User result = userMapper.selectByPrimaryKey(user.getId());

            resultVO = new ResultVO(200, "成功", true, result);
        } else {
            resultVO = new ResultVO(-1, "失败", false, null);
        }


        return resultVO;
    }

    //删除用户
    @Override
    public ResultVO delete(Long id) {
        int affectedRows = userMapper.deleteByPrimaryKey(id);

        ResultVO resultVO;

        if (affectedRows > 0) {
            resultVO = new ResultVO(200, "成功", true, null);
        } else {
            resultVO = new ResultVO(-1, "失败", false, null);
        }

        return resultVO;
    }
}
