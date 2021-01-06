package com.zb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zb.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {}
