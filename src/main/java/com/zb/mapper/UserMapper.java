package com.zb.mapper;

import org.apache.ibatis.annotations.Param;

import com.zb.pojo.User;

public interface UserMapper {
    User selectById(@Param("id") int id);
}
