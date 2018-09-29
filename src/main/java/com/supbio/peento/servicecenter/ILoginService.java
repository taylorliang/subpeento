package com.supbio.peento.servicecenter;

import com.supbio.peento.models.model.LoginDTO;

public interface ILoginService {

    LoginDTO findLoginByPhone(String account);

}
