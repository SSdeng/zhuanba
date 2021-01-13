package com.zb.service.impl;

import com.zb.entity.*;
import com.zb.exception.MyException;
import com.zb.repository.CollectionRepository;
import com.zb.repository.ItemRepository;
import com.zb.repository.UserRepository;
import com.zb.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 收藏服务实现类
 * @author whz
 * 2021/1/11
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Resource
    private CollectionRepository collectionRepository;
    @Resource
    private ItemRepository itemRepository;
    @Resource
    private UserRepository userRepository;
    /**
     * 添加商品到收藏
     *
     * @param userId  用户id
     * @param itemId  商品id
     * @return 收藏
     */

    @Override
    public Collection addItem(Long userId, Long itemId) {
        User user = userRepository.getOne(userId);
        Collection collection = user.getCollection();
        collection.getItems().add(getItemById(itemId));
        return collectionRepository.save(collection);
    }

    /**
     * 从收藏夹移除商品
     * @param collectionId 收藏id
     * @param itemId 商品id
     */
    @Override
    public Collection removeItem(Long collectionId, Long itemId) {
        Collection collection = findById(collectionId);
        collection.getItems().remove(getItemById(itemId));
        return collectionRepository.save(collection);
    }

    /**
     * 通过Id获取收藏
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
     * 通过id获取商品
     * @param id 商品id
     * @return 商品
     */
    @Override
    public Item findItemById(Long id) {
        Item item = getItemById(id);
        if(item == null ){
            throw new MyException("商品未找到");
        }
        return item;
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
     * 获取id对应item
     *
     * @param id 商品id
     * @return item 对象 不存在时返回null
     */
    private Item getItemById(Long id) {
        return id == null ? null : itemRepository.findById(id).orElse(null);
    }
}
