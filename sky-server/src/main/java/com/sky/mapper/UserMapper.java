package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: kante_yang
 * @Date: 2023/11/1
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE open_id = #{openId}")
    User getUserByOpenId(String openId);

    @Insert("INSERT INTO user(openid, name, phone, sex, id_number, avatar, create_time)" +
            "VALUES (#{openid}, #{name}, #{phone}, #{sex}, #{idNumber}, #{avatar}, NOW()})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(User user);
}
