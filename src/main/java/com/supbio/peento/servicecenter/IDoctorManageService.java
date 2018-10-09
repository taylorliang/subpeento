package com.supbio.peento.servicecenter;

import com.supbio.peento.common.response.PageParameter;
import com.supbio.peento.models.model.DoctorDTO;

import java.util.List;

/**
 * Created by liangqiang on 2018/10/9.
 */
public interface IDoctorManageService {

    List<DoctorDTO> findAllDoctor(String userName, String office, String title, String submitStartTime, String submitEndTime,
                                  String number, Integer status, PageParameter pageParameter);

    void updateDoctor(String doctorId, String acceptStatus, String pushStatus, String doctorTitle);
}
