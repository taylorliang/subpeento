package com.supbio.peento.mapper;

import com.supbio.peento.common.response.PageParameter;
import com.supbio.peento.models.model.DoctorDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liangqiang on 2018/10/9.
 */
@Repository
public interface DoctorManageMapper {

    List<DoctorDTO> findAllDoctor(@Param("userName") String userName, @Param("office") String office, @Param("title") String title,
                                  @Param("submitStartTime") String submitStartTime, @Param("submitEndTime") String submitEndTime,
                                  @Param("number") String number, @Param("status") Integer status, PageParameter pageParameter);

    void updateDoctor(@Param("id") String doctorId, @Param("acceptStatus") String acceptStatus, @Param("pushStatus") String pushStatus, @Param("doctorTitle") String doctorTitle);

}
