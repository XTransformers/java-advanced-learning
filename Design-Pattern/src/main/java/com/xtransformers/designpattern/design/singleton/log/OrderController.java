package com.xtransformers.designpattern.design.singleton.log;

/**
 * @author daniel
 * @date 2021-05-25
 */
public class OrderController {

    private Logger logger = new Logger();

    public void create(String order) {
        logger.log(order + "created.");
        LoggerV2.getInstance().log(order + "created.");

    }
}
