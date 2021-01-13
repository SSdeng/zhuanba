package com.zb.service.impl;

import javax.annotation.Resource;
import javax.annotation.Resources;

import com.zb.entity.vo.ItemVO;
import com.zb.exception.MyException;
import com.zb.repository.UserRepository;
import com.zb.util.JsonTransfer;
import org.springframework.beans.BeanUtils;
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
    @Resource
    private UserRepository userRepository;

    /**
     * 发布商品
     *
     * @param itemVO 新增Item对象
     * @return 插入后Item对象
     */
    @Override
    public Item insertSelective(ItemVO itemVO) {
        Item newItem = new Item();
        newItem.setUser(userRepository.getOne(itemVO.getUserId()));
        BeanUtils.copyProperties(itemVO, newItem);
        return itemRepository.saveAndFlush(newItem);
    }

    /**
     * 发布商品
     *
     * @param item 新增Item对象
     * @return 插入后Item对象
     */
    @Override
    public Item insertSelective(Item item) {
        return itemRepository.save(item);
    }

    /**
     * 根据商品id删除商品
     *
     * @param itemId 商品id
     * @return 删除结果
     */
    @Override
    public boolean deleteById(long itemId) {
        itemRepository.deleteById(itemId);
        return true;
    }

    /**
     * 更新商品信息
     *
     * @param json json字符串
     * @param item 需更新Item对象
     * @return 更新后Item对象
     */
    @Override
    public Item updateItemInfo(String json, Item item) {
        item = JsonTransfer.updateSelective(json, item);
        return itemRepository.save(item);
    }

    /**
     * 修改商品id对应商品的审核状态
     *
     * @param itemId    商品id
     * @param adminId 管理员id
     * @param status     新审核状态
     * @return 修改后的Item对象
     * @see com.zb.entity.Item
     */
    @Override
    public Item setAuditStatus(long itemId, long adminId, int status) {
        Item item = findById(itemId);
        item.setAuditId(adminId);
        item.setStatus(status);
        return itemRepository.save(item);
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
