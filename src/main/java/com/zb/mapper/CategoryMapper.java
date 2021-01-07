package com.zb.mapper;

import com.zb.pojo.Category;

/**
 * @Entity generate.Category
 */
public interface CategoryMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int insert(Category record);

    /**
     * @mbg.generated
     */
    int insertSelective(Category record);

    /**
     * @mbg.generated
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Category record);
}