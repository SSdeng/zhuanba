package com.zb.mapper;

import com.zb.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CategoryDAO层
 * 
 * @author dengzhijian
 * @version 1.0
 */
@Repository
public interface CategoryMapper {
    /**
     * 根据主键删除分类（真删除）
     * 
     * @param id
     *            主键
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新分类
     * 
     * @param record
     *            待插入分类
     */
    int insert(Category record);

    /**
     * 插入新分类（不插入空字段）
     * 
     * @param record
     *            待插入分类
     */
    int insertSelective(Category record);

    /**
     * 根据主键查询分类（包含商品列表）
     * 
     * @param id
     *            主键
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * 根据商品id获取简单分类列表
     * 
     * @param id
     *            主键
     */
    List<Category> selectByItemId(Integer id);

    /**
     * 根据主键更新分类（不更新空字段）
     * 
     * @param record
     *            新的分类
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 根据主键更新分类
     * 
     * @param record
     *            新的分类
     */
    int updateByPrimaryKey(Category record);

    /**
     * 按类别名查找类别
     *
     * @param name 类别名
     * @return 类别对象
     */
    Category findByName(@Param("name") String name);

    /**
     * 分页返回分类列表
     *
     * @return 商品列表
     */
     List<Category> selectAll();
}