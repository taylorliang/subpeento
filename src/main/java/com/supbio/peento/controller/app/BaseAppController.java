package com.supbio.peento.controller.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * web基础Controller
 * @author liangqiang
 */
public abstract class BaseAppController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 得到本地机器的IP
     * @return
     */
    public String getClientIp(){
        String ip = "";
        try{
            ip = InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException e){
            e.printStackTrace();
        }
        return ip;
    }

}
