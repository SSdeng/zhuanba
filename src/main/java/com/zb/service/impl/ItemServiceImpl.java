package com.zb.service.impl;

import javax.annotation.Resource;

import com.zb.exception.MyException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
     * @param newItem 新增Item对象
     * @return 插入后Item对象
     */
    @Override
    public Item insertSelective(Item newItem) {
        if (getById(newItem.getId()) != null) {
            throw new DataIntegrityViolationException("相同id的item已存在");
        }
        return itemRepository.saveAndFlush(newItem);
    }

    /**
     * 根据商品id删除商品
     *
     * @param item_id 商品id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(long item_id) {
        itemRepository.deleteById(item_id);
        return true;
    }

    /**
     * 更新商品信息
     *
     * @param item 需更新Item对象
     * @return 更新后Item对象
     */
    @Override
    public Item updateItemInfo(Item item) {
        // TODO
        return itemRepository.save(item);
    }

    /**
     * 修改商品id对应商品的审核状态
     *
     * @param item_id    商品id
     * @param manager_id 管理员id
     * @param status     新审核状态
     * @return 修改后的Item对象
     * @see com.zb.entity.Item
     */
    @Override
    public Item setAuditStatus(long item_id, long manager_id, int status) {
        // TODO
        return null;
    }

    /**
     * 分页查询
     *
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 商品列表
     */
    @Override
    public Page<Item> findAllByPage(int pageNo, int pageSize) {
        return itemRepository.findAll(PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 根据商品id查找商品
     *
     * @param id 商品id
     * @return 对应Item对象 未找到返回null
     */
    @Override
    public Item findById(long id) {
        Item item = getById(id);
        if (item == null) {
            throw new MyException("商品未找到");
        }
        return item;
    }

    /**
     * 获取id对应item
     *
     * @param id item_id
     * @return item对象 不存在时返回null
     */
    private Item getById(Long id) {
        return id == null ? null : itemRepository.findById(id).orElse(null);
    }

    /**
     * 按条件分页查找
     *
     * @param searchInfo 搜索信息
     * @param pageNo     起始页码
     * @param pageSize   分页大小
     * @return 查询结果
     */
    @Override
    public Page<Item> searchByPage(String searchInfo, int pageNo, int pageSize) {
        // TODO
        return null;
    }
}
