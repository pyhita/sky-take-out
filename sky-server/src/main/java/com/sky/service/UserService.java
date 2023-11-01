package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @Author: kante_yang
 * @Date: 2023/11/1
 */
public interface UserService {


    User wxLogin(UserLoginDTO userLoginDTO);
}
