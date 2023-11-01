package com.sky.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: kante_yang
 * @Date: 2023/11/1
 */
@Service
public class UserServiceImpl implements UserService {

    //微信服务接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 1 发送网络请求 获得openId
        String openId = getOpenId(userLoginDTO.getCode());

        // 2 如果用户不存在 构建User对象 插入表中
        return Optional.ofNullable(userMapper.getUserByOpenId(openId))
                .orElseGet(() -> {
                    User user = User.builder().openid(openId).build();
                    userMapper.insert(user);
                    return user;
                });
    }

    private String getOpenId(String code) {
        // 调用微信的接口服务，获得当前微信登录用户的openid
        Map<String, String> map = new HashMap<>();
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSONObject.parseObject(json);

        return jsonObject.getString("openid");
    }
}
