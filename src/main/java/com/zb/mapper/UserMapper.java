package com.zb.mapper;

import com.zb.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
  User selectById(@Param("id") int id);
}
