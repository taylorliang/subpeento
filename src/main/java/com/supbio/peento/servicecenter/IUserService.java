package com.supbio.peento.servicecenter;

import com.supbio.peento.models.entity.User;
import com.supbio.peento.models.model.UserDTO;

import java.util.List;

public interface IUserService {

    List<User> findAllUser();

    User findOne(String id);

    void saveOne(UserDTO userDTO);
}
