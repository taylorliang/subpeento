package com.supbio.peento.mapper;

import com.supbio.peento.models.entity.User;
import com.supbio.peento.models.model.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public List<User> findAllUser();

    User findOne(String id);

    void saveOne(UserDTO userDTO);
}
