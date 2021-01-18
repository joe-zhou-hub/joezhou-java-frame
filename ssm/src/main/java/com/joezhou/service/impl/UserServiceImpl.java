package com.joezhou.service.impl;

import com.joezhou.mapper.UserMapper;
import com.joezhou.pojo.User;
import com.joezhou.service.UserService;
import com.joezhou.util.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoeZhou
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> paging(PagingUtil pagingUtil) {

        if (pagingUtil == null) {
            return new ArrayList<>();
        }

        if (pagingUtil.getLimitSuffix() == null || "".equals(pagingUtil.getLimitSuffix())) {
            return new ArrayList<>();
        }
        pagingUtil.buildTotalAndPages(userMapper.count());
        pagingUtil.buildNumbers();
        return userMapper.paging(pagingUtil.getLimitSuffix());
    }

    @Override
    public User selectById(Integer id) {
        if (id < 0) {
            return new User();
        }
        return userMapper.selectById(id);
    }

    @Transactional(rollbackForClassName = "Exception")
    @Override
    public void insert(User user) {
        if (user != null) {
            userMapper.insert(user);
        }
        // test @Transactional
        // throw new RuntimeException("exception and rollback...");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(User user) {
        if (user != null) {
            userMapper.updateById(user);
        }
        // test @Transactional
        // throw new RuntimeException("exception and rollback...");
    }

    @Transactional(rollbackForClassName = "Exception")
    @Override
    public void deleteByIds(Integer[] ids) {
        if (ids != null && ids.length != 0) {
            for (Integer id : ids) {
                if (id == null) {
                    throw new NullPointerException("ids中有元素为null...");
                }
            }
            userMapper.deleteByIds(ids);
        }
        // test @Transactional
        // throw new RuntimeException("exception and rollback...");
    }
}