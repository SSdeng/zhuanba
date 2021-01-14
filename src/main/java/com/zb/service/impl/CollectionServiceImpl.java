package com.zb.service.impl;

import com.zb.entity.*;
import com.zb.exception.MyException;
import com.zb.repository.CollectionRepository;
import com.zb.service.CollectionService;
import com.zb.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收藏服务实现类
 *
 * @author whz
 * 2021/1/11
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Resource
    private CollectionRepository collectionRepository;
    @Resource
    private ItemService itemService;

    /**
     * 检查收藏夹中是否已有商品
     *
     * @param itemId 商品id
     * @return true表有 false表无
     */
    @Override
    public boolean hasItem(long id, long itemId) {
        return findItemByItemId(findById(id).getItems(), itemId) != null;
    }

    /**
     * 添加商品到收藏
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @return 收藏
     */
    @Override
    public Collection addItem(Long userId, Long itemId) {
        Collection collection = findById(userId);
        if (hasItem(collection, itemId)) {
            throw new MyException("商品已在收藏夹中！");
        }
        collection.getItems().add(itemService.findById(itemId));
        return collectionRepository.save(collection);
    }

    /**
     * 从收藏夹移除商品
     *
     * @param collectionId 收藏id
     * @param itemId 商品id
     */
    @Override
    public Collection removeItem(Long collectionId, Long itemId) {
        Collection collection = findById(collectionId);
        List<Item> list = collection.getItems();
        Item item = findItemByItemId(list, itemId);
        if (item != null) {
            list.remove(item);
        } else {
            throw new MyException("商品不存在");
        }
        return collectionRepository.save(collection);
    }

    /**
     * 按商品id查找购物车中订单
     *
     * @param list   购物车
     * @param itemId 商品id
     * @return 购物车订单 不存在返回null
     */
    private Item findItemByItemId(List<Item> list, long itemId) {
        for (Item item : list) {
            if (item.getId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    /**
     * 通过Id获取收藏
     *
     * @param id 收藏id
     * @return 收藏
     */
    @Override
    public Collection findById(Long id) {
        Collection collection = getById(id);
        if (collection == null) {
            throw new MyException("收藏未找到");
        }
        return collection;
    }

    /**
     * 获取id对应收藏
     *
     * @param id 收藏id
     * @return collection 对象 不存在时返回null
     */
    private Collection getById(Long id) {
        return id == null ? null : collectionRepository.findById(id).orElse(null);
    }


    /**
     * 检查收藏夹中是否已有商品
     *
     * @param itemId 商品id
     * @return true表有 false表无
     */
    private boolean hasItem(Collection collection, long itemId) {
        return findItemByItemId(collection.getItems(), itemId) != null;
    }
}
