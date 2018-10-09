package com.supbio.peento.controller.manage;

import com.supbio.peento.common.response.AppPageResultObj;
import com.supbio.peento.common.response.AppResultObj;
import com.supbio.peento.common.response.PageParameter;
import com.supbio.peento.models.model.DoctorDTO;
import com.supbio.peento.models.params.manage.DoctorManageParam;
import com.supbio.peento.models.result.manage.DoctorJson;
import com.supbio.peento.servicecenter.IDoctorManageService;
import com.supbio.peento.utils.JacksonUtil;
import com.supbio.peento.utils.ManageModelChangeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangqiang on 2018/10/9.
 */
@Api(value = "DoctorManageController", description = "医生管理模块-相关API")
@RestController
@RequestMapping("/api/manage/doctor")
public class DoctorManageController extends BaseManageController {

    @Autowired
    private IDoctorManageService doctorManageService;

    @ApiOperation(value = "医生认证审核列表接口", notes = "医生认证审核列表接口")
    @RequestMapping(value = "/doctorCheckList", method = RequestMethod.POST)
    public AppPageResultObj<List<DoctorJson>> doctorCheckList(@RequestBody DoctorManageParam.DoctorCheck param){
        logger.info("/api/manage/doctor/doctorCheckList: {}", JacksonUtil.toJSON(param));
        PageParameter pageParameter = new PageParameter(param.getPage(), param.getCount());
        List<DoctorDTO> doctorDTOList = doctorManageService.findAllDoctor(param.getUserName(), param.getOffice(),
                param.getTitle(), param.getSubmitStartTime(), param.getSubmitEndTime(), param.getNumber(), param.getStatus(), pageParameter);
        List<DoctorJson> doctorJsonList = new ArrayList<>();
        if (CollectionUtils.isEmpty(doctorDTOList)){
            return AppPageResultObj.success(doctorJsonList);
        }
        doctorJsonList = ManageModelChangeUtil.changeToDoctorJsonList(doctorDTOList, doctorJsonList);
        return AppPageResultObj.success(doctorJsonList, pageParameter);
    }

    @ApiOperation(value = "接收状态/是否推送/医生标签更改接口", notes = "接收状态/是否推送/医生标签更改接口")
    @RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
    public AppResultObj<String> changeStatus(@RequestBody DoctorManageParam.ChangeDoctorStatus param){
        logger.info("/api/manage/doctor/changeStatus: {}", JacksonUtil.toJSON(param));
        doctorManageService.updateDoctor(param.getDoctorId(), param.getAcceptStatus(), param.getPushStatus(), param.getDoctorTitle());
        return AppResultObj.success("修改成功");
    }

}
