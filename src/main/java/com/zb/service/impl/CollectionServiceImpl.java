package com.zb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zb.entity.Collection;
import com.zb.exception.MyException;
import com.zb.repository.CollectionRepository;
import com.zb.service.CollectionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 收藏服务实现类
 * @author whz
 * 2021/1/11
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Resource
    private CollectionRepository collectionRepository;

    /**
     * 通过收藏夹名称查询
     * @param collectionName
     * @return
     */
    @Override
    public Collection findByName(String collectionName) {
        return collectionRepository.findByName(collectionName);
    }

    /**
     * 分页显示所有收藏夹
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Collection> getAllCollections(int pageNo,int pageSize) {
        return collectionRepository.findAll(PageRequest.of(pageNo-1,pageSize));
    }

    /**
     * 添加收藏夹
     * @param newCollection
     * @return
     */
    @Override
    public Collection addCollection(Collection newCollection) {
        if (getById(newCollection.getId()) != null) {
            throw new DataIntegrityViolationException("相同id的collection已存在");
        }
        return collectionRepository.saveAndFlush(newCollection);
    }

    @Override
    public Collection findById(Long id) {
        Collection collection = getById(id);
        if (collection == null) {
            throw new MyException("商品未找到");
        }
        return collection;
    }

    /**
     * 更新收藏夹
     * @param JSONCollection
     * @param collectionId
     * @return
     */
    @Override
    public Collection UpdateCollection(String JSONCollection,Long collectionId) {
        Collection dataCollection = findById(collectionId);
        ObjectMapper mapper = new ObjectMapper();
        // 利用jackson相关API，实现非null值的合并更新
         Collection newCollection = null;
        try {
            newCollection = mapper.readerForUpdating(dataCollection).readValue(JSONCollection);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collectionRepository.save(newCollection);
    }

    /**
     * 删除收藏夹
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        collectionRepository.deleteById(id);
        return true;
    }
    /**
     * 获取id对应item
     *
     * @param id collection_id
     * @return item对象 不存在时返回null
     */
    private Collection getById(Long id) {
        return id == null ? null : collectionRepository.findById(id).orElse(null);
    }
}
