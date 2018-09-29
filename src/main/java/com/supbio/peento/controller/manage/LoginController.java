package com.supbio.peento.controller.manage;

import com.aliyuncs.exceptions.ClientException;
import com.supbio.peento.models.model.LoginDTO;
import com.supbio.peento.models.params.UserParam;
import com.supbio.peento.models.params.manage.LoginParam;
import com.supbio.peento.servicecenter.ILoginService;
import com.supbio.peento.servicecenter.sms.AliSendMessage;
import com.supbio.peento.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author liangqiang
 * @date 2018/9/29 10:35
 */
@Api(value = "LoginController", description = "登录相关API")
@RestController
@RequestMapping("/api/manage")
public class LoginController extends BaseManageController {

    @Autowired
    private ILoginService loginService;

    @ApiOperation(value = "登录接口", notes = "后台管理系统登录接口")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginParam param){
        logger.info("/api/manage/login:{}", param);
        String account = param.getUserName();
        String password = MD5Util.createPasswordByMD5(param.getPassword());
        LoginDTO loginDTO = loginService.findLoginByPhone(account);
        if (loginDTO == null){
            return "该手机号未注册为后台管理员";
        }
        if (password.equals(loginDTO.getPassword())){
            return "帐号密码正确";
        }
        return "密码不正确，请重新输入";
    }

    @ApiOperation(value = "发送验证码接口", notes = "发送验证码接口")
    @RequestMapping(value = "/sendCode", method = RequestMethod.POST)
    public String sendCode(String phone, HttpServletRequest request, HttpServletResponse response){
        logger.info("/api/manage/sendCode:{}", phone);
        LoginDTO loginDTO = loginService.findLoginByPhone(phone);
        if (loginDTO == null){
            return "该手机号未注册为后台管理员";
        }
        //生成6个随机验证码
        String code = AliSendMessage.createCode();
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        String sendStatus = "";
        try {
            sendStatus = AliSendMessage.sendMessage(phone, code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return sendStatus;
    }

    @ApiOperation(value = "重设密码接口", notes = "重设密码接口")
    @RequestMapping(value = "/reSetPassword", method = RequestMethod.POST)
    public String reSetPassword(String phone, String code, String password, String confirmPassword, HttpServletRequest request, HttpServletResponse response){
        logger.info("/api/manage/reSetPassword:{}", phone);
        LoginDTO loginDTO = loginService.findLoginByPhone(phone);
        if (loginDTO == null){
            return "该手机号未注册为后台管理员";
        }
        //取出session中保存的6位随机验证码
        HttpSession session = request.getSession();
        String sessionCode = (String)session.getAttribute("code");
        if (code.equals(sessionCode)){
            if (password.equals(confirmPassword)){
                session.removeAttribute("code");
                return "成功重置密码";
            }
            return "两次密码不一致";
        }
        return "验证码不对";
    }

}
