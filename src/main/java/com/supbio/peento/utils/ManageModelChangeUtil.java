package com.supbio.peento.utils;

import com.supbio.peento.models.entityenum.DoctorStatusEnum;
import com.supbio.peento.models.model.DoctorDTO;
import com.supbio.peento.models.result.manage.DoctorJson;

import java.util.List;

/**
 * Created by liangqiang on 2018/10/9.
 */
public class ManageModelChangeUtil {

    public static List<DoctorJson> changeToDoctorJsonList(List<DoctorDTO> doctorDTOList, List<DoctorJson> doctorJsonList){
        for (DoctorDTO doctorDTO : doctorDTOList) {
            DoctorJson json = new DoctorJson();
            json.setDoctorId(doctorDTO.getDoctorId());
            json.setHeadUrl(doctorDTO.getHeadUrl());
            json.setUserName(doctorDTO.getUserName());
            json.setAcceptStatus(doctorDTO.getAcceptStatus());
            json.setPushStatus(doctorDTO.getPushStatus());
            json.setDoctorTitle(doctorDTO.getDoctorTitle());
            json.setIDNumber(doctorDTO.getIDNumber());
            json.setPhone(doctorDTO.getPhone());
            json.setSex(doctorDTO.getSex());
            json.setStatus(doctorDTO.getStatus());
            json.setHospital(doctorDTO.getHospital());
            json.setTitle(doctorDTO.getTitle());
            json.setOffice(doctorDTO.getOffice());
            json.setChecker(doctorDTO.getChecker());
            json.setSubmitTime(DateUtil.date2Str(DateUtil.dateString2Date(doctorDTO.getSubmitTime(),
                    DateUtil.DF_yyyy_MM_dd_HH_mm_ss), DateUtil.DF_yyyy_MM_dd_HH_mm_ss));
            if (DoctorStatusEnum.WAITCHECK.getCode() == doctorDTO.getStatus()){
                //当状态为待审核时，不传认证资料按钮不显示
                json.setIsShow(1);
            }else {
                json.setIsShow(0);
            }
            doctorJsonList.add(json);
        }
        return doctorJsonList;
    }

}
