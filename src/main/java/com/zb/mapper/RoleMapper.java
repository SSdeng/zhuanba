package com.zb.mapper;

import com.zb.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
  User delByAcademyAndPassword(int id);
}
