package com.supbio.peento.controller.manage;

import com.aliyuncs.exceptions.ClientException;
import com.supbio.peento.models.entity.User;
import com.supbio.peento.models.model.UserDTO;
import com.supbio.peento.models.params.UserParam;
import com.supbio.peento.servicecenter.IUserService;
import com.supbio.peento.servicecenter.sms.AliSendMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


/**
 * @author liangqiang
 * @date 2018/9/27 13:37
 */
@Api(value = "TestController", description = "测试-相关API")
@RestController
@RequestMapping("/api/peento/manage/login")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "保存用户信息", notes = "保存用户信息")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(UserParam param){
        logger.info("/api/peento/manage/saveUser:{}", param);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(param.getUserName());
        userDTO.setAge(param.getAge());
        userDTO.setSex(param.getSex());
        userDTO.setCreateTime(new Date());
        userDTO.setUpdateTime(new Date());

        return null;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findOne(@PathVariable String id){
        User user = iUserService.findOne(id);
        return user;
    }

    @RequestMapping(value = "/findAllUser", method = RequestMethod.POST)
    @ResponseBody
    public List<User> findAllUser(){
        logger.info("/api/peento/manage/findAllUser:{}");
        List<User> user = iUserService.findAllUser();
        logger.info("<<<<---" + user.get(0).getUserName());
        return user;
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ResponseBody
    public String sendMessage(String phone, HttpServletRequest request, HttpServletResponse response){
        logger.info("/api/peento/manage/sendMessage:{}", phone);
        String string = "";

        String code = AliSendMessage.createCode();
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        logger.info("------before" + session.getAttribute("code"));
        try {
            string = AliSendMessage.sendMessage(phone, code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        session.removeAttribute("code");
        logger.info("------after" + session.getAttribute("code"));
        return string;
    }

}
