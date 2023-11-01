package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kante_yang
 * @Date: 2023/11/1
 */
@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties) {
        log.info("开始创建OssUtil工具类");

        return AliOssUtil.builder().endpoint(aliOssProperties.getEndpoint())
                .bucketName(aliOssProperties.getBucketName())
                .accessKeyId(aliOssProperties.getAccessKeyId())
                .accessKeySecret(aliOssProperties.getAccessKeyId())
                .build();
    }
}
