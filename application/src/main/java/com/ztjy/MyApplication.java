package com.ztjy;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.ztjy.xxxxserver.config.MyApplicationReadyEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDubboConfiguration
public class MyApplication {
    public static void main(String[] args) {
    	
    	String logPath = "log.path.prefix";
    	
    	//如果启动参数没设置日志的保存路径，则目录用logs
		if(StringUtils.isBlank(System.getProperty(logPath))){
            System.setProperty(logPath, ".");
		}
    	
        SpringApplication app = new SpringApplication(MyApplication.class);
        app.addListeners(new MyApplicationReadyEvent());
        app.run(args);
    }
}
