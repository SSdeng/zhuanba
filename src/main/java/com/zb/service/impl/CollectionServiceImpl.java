package com.zb.service.impl;

import com.zb.entity.*;
import com.zb.exception.MyException;
import com.zb.repository.CollectionRepository;
import com.zb.repository.ItemRepository;
import com.zb.repository.UserRepository;
import com.zb.service.CollectionService;
import com.zb.service.ItemService;
import com.zb.service.UserService;
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
    @Resource
    private UserService userService;

    /**
     * 添加商品到收藏
     *
     * @param userId 用户id
     * @param itemId 商品id
     * @return 收藏
     */
    @Override
    public Collection addItem(Long userId, Long itemId) {
        Collection collection = userService.findById(userId).getCollection();
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
        boolean flag = false;
        for (Item item : list) {
            if (item.getId().equals(itemId)) {
                list.remove(item);
                flag = true;
                break;
            }
        }
        if (flag) {
            return collectionRepository.save(collection);
        } else {
            throw new MyException("商品不存在");
        }

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
     * 根据用户id获取收藏夹
     *
     * @param userId
     * @return
     */
    @Override
    public Collection findByUser(Long userId) {
        Collection collection = collectionRepository.findByUser_Id(userId);
        if(collection == null){
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

}
