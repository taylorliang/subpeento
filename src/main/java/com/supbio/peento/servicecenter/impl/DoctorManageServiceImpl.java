package com.supbio.peento.servicecenter.impl;

import com.supbio.peento.common.response.PageParameter;
import com.supbio.peento.mapper.DoctorManageMapper;
import com.supbio.peento.models.model.DoctorDTO;
import com.supbio.peento.servicecenter.IDoctorManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liangqiang on 2018/10/9.
 */
@Service
@Transactional
public class DoctorManageServiceImpl implements IDoctorManageService {

    @Autowired
    private DoctorManageMapper doctorManageMapper;

    @Override
    public List<DoctorDTO> findAllDoctor(String userName, String office, String title, String submitStartTime,
                                         String submitEndTime, String number, Integer status, PageParameter pageParameter) {
        return doctorManageMapper.findAllDoctor(userName, office, title, submitStartTime, submitEndTime, number, status, pageParameter);
    }

    @Override
    public void updateDoctor(String doctorId, String acceptStatus, String pushStatus, String doctorTitle) {
        doctorManageMapper.updateDoctor(doctorId, acceptStatus, pushStatus, doctorTitle);
    }

}
