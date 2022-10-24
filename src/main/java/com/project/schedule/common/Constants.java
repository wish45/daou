package com.project.schedule.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static String path;

    public static String suffix;

    public static String allowIp;

    @Value("${path}")
    public void setPath(String path) {
        this.path = path;
    }

    @Value("${suffix}")
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Value("${allowIp}")
    public void setAllowIp(String allowIp) {
        this.allowIp = allowIp;
    }
}
