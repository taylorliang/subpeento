package com.supbio.peento.servicecenter.impl;

import com.supbio.peento.mapper.LoginMapper;
import com.supbio.peento.models.model.LoginDTO;
import com.supbio.peento.servicecenter.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginDTO findLoginByPhone(String account) {
        return loginMapper.findLoginByPhone(account);
    }

    @Override
    public void updateLoginByPhone(String phone, String password) {
        loginMapper.updateLoginByPhone(phone, password);
    }

}
