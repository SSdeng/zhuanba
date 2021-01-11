package com.zb.service;

import com.zb.entity.Item;
import org.springframework.data.domain.Page;

/**
 * 商品服务接口
 *
 * @author YeFeng
 */
public interface ItemService {

    /**
     * 发布商品
     *
     * @param newItem 新增Item对象
     * @return 插入后Item对象
     */
    Item insertSelective(Item newItem);

    /**
     * 根据商品id删除商品
     *
     * @param item_id 商品id
     * @return 删除结果
     */
    boolean deleteById(long item_id);

    /**
     * 更新商品信息
     *
     * @param item 需更新Item对象
     * @return 更新后Item对象
     */
    Item updateItemInfo(Item item);

    /**
     * 修改商品id对应商品的审核状态
     *
     * @param item_id    商品id
     * @param manager_id 管理员id
     * @param status     新审核状态
     * @return 修改后的Item对象
     * @see com.zb.entity.Item
     */
    Item setAuditStatus(long item_id, long manager_id, int status);

    /**
     * 分页查询
     *
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 商品列表
     */
    Page<Item> findAllByPage(int pageNo, int pageSize);

    /**
     * 根据商品id查找商品
     *
     * @param id 商品id
     * @return 对应Item对象
     */
    Item findById(long id);

    /**
     * 按条件分页查找
     *
     * @param searchInfo 搜索信息
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 查询结果
     */
    Page<Item> searchByPage(String searchInfo, int pageNo, int pageSize);
}
