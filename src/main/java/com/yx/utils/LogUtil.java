package com.yx.utils;

import org.apache.log4j.Logger;

/**
 * User: LiWenC
 * Date: 18-4-26
 */
public class LogUtil {

    private static Logger log = Logger.getLogger(LogUtil.class);

    public static void error(Exception e) {
        log.error(e, e);
    }
}
