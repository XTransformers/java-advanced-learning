package com.xtransformers.designpattern.design.singleton.log;

/**
 * @author daniel
 * @date 2021-05-25
 */
public class UserController {

    private Logger logger = new Logger();

    public void login(String userName, String password) {
        logger.log(userName + " login!");
        LoggerV2.getInstance().log(userName + "login!");

    }
}
