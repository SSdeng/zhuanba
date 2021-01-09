package com.zb.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.entity.Item;
import com.zb.repository.ItemRepository;
import com.zb.service.ItemService;

/**
 * 商品服务实现类
 *
 * @author YeFeng
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemRepository itemRepository;

    /**
     * 发布商品
     *
     * @param newItem
     *            新增Item对象
     * @return 插入后Item对象
     */
    @Override
    public Item insert(Item newItem) {
        itemRepository.insertSelective(newItem);
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
        int cnt = itemRepository.deleteByPrimaryKey(item_id);
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
        itemRepository.updateByPrimaryKeySelective(item);
        return item;
    }

    /**
     * 修改商品id对应商品的审核状态
     *
     * @see com.zb.entity.Item
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
        // TODO
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
        List<Item> list = itemRepository.selectALl();
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
        return itemRepository.selectByPrimaryKey(id);
    }

    /**
     * 分页搜索商品
     *
     * @param info
     *            搜索信息
     * @param pageNo
     *            起始页码
     * @param pageSize
     *            分页大小
     * @return 结果商品列表
     */
    @Override
    public PageInfo<Item> searchPage(String info, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Item> list = itemRepository.selectByInfo(info);
        return new PageInfo<>(list);
    }
}
