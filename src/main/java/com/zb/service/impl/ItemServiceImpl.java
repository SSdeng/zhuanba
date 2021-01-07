package com.zb.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.mapper.ItemMapper;
import com.zb.pojo.Item;
import com.zb.pojo.Item_Category;
import com.zb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务实现类
 *
 * @author YeFeng
 */
@Service
public class ItemServiceImpl implements ItemService {

    /** 商品映射 */
    final private ItemMapper itemMapper;

    /**
     * 构造器依赖注入
     *
     * @param itemMapper 商品映射
     */
    @Autowired
    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    /**
     * 发布商品
     *
     * @param newItem
     *            新增Item对象
     * @param userId
     *            新增Item的外键
     * @return 插入后Item对象
     */
    @Override
    public Item insert(Item newItem,int userId) {
        itemMapper.insertSelective(newItem, userId);
        return newItem;
    }

    /**
     * 根据商品id删除商品
     *
     * @param item_id
     *            商品id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(int item_id) {
        int cnt = itemMapper.deleteByPrimaryKey(item_id);
        return cnt > 0;
    }

    /**
     * 更新商品信息
     *
     * @param item
     *            需更新Item对象
     * @return 更新后Item对象
     */
    @Override
    public Item updateItemInfo(Item item) {
        itemMapper.updateByPrimaryKeySelective(item);
        return item;
    }

    /**
     * 修改商品id对应商品的审核状态
     *
     * @see com.zb.pojo.Item
     *
     * @param item_id
     *            商品id
     * @param manager_id
     *            管理员id
     * @param status
     *            新审核状态
     * @return 修改后的Item对象
     */
    @Override
    public Item setAuditStatus(int item_id, int manager_id, int status) {
        //TODO
        return null;
    }

    /**
     * 分页查询
     *
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 商品列表
     */
    @Override
    public PageInfo<Item> findPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Item> list = itemMapper.selectALl();
        return new PageInfo<>(list);
    }

    /**
     * 根据商品id查找商品
     *
     * @param id
     *            商品id
     * @return 对应Item对象
     */
    @Override
    public Item findById(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页搜索商品
     *
     * @param info 搜索信息
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 结果商品列表
     */
    @Override
    public PageInfo<Item> searchPage(String info, int pageNo, int pageSize){
        PageHelper.startPage(pageNo, pageSize);
        List<Item> list = itemMapper.selectByInfo(info);
        return new PageInfo<>(list);
    }
}
