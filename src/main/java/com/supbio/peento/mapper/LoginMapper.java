package com.supbio.peento.mapper;

import com.supbio.peento.models.model.LoginDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {

    LoginDTO findLoginByPhone(@Param("phone") String account);

}
