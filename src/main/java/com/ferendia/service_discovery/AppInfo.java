package com.ferendia.service_discovery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.HashMap;
import java.util.Map;

public class AppInfo implements InfoContributor {

    @Value("${spring.application.name}")
    String appName;

    @Value("${spring.application.description}")
    String appDescription;

    @Value("${spring.application.version}")
    String appVersion;

    @Value("${spring.application.java.version}")
    String appJavaVersion;



    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> details = new HashMap<>();
        Map<String, Object> appInfo = new HashMap<>();
        appInfo.put("name", appName);
        appInfo.put("description", appDescription);
        appInfo.put("version", appVersion);
        details.put("app-info", appInfo);
        details.put("java-version", appJavaVersion);
        builder.withDetails(details);
    }
}
