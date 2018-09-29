package com.supbio.peento.servicecenter.impl;

import com.supbio.peento.models.entity.User;
import com.supbio.peento.mapper.UserMapper;
import com.supbio.peento.models.model.UserDTO;
import com.supbio.peento.servicecenter.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findOne(String id) {
        return userMapper.findOne(id);
    }

    @Override
    public void saveOne(UserDTO userDTO) {
        userMapper.saveOne(userDTO);
    }

}
