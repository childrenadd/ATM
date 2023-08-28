package com.sph;

import cn.hutool.core.util.NetUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Project parentProject
 * @Classname AtmServiceZuulApplication
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/8/27 23:25
 * @Created by panqiang
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class AtmServiceZuulApplication {
    public static void main(String[] args) {
        int port = 8050;
        if(!NetUtil.isUsableLocalPort(port)) {
            System.err.printf("端口%d被占用了，无法启动%n", port );
            System.exit(1);
        }
        new SpringApplicationBuilder(AtmServiceZuulApplication.class).properties("server.port=" + port).run(args);

    }
}