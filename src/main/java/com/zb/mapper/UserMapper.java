package com.zb.mapper;

import com.zb.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  User selectById(int id);
}
