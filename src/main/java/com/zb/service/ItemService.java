package com.zb.service;

import com.zb.entity.Item;
import com.zb.entity.vo.ItemVO;
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
     * @param itemVO 新增Item对象
     * @return 插入后Item对象
     */
    Item insertSelective(ItemVO itemVO);

    /**
     * 发布商品
     *
     * @param item 新增Item对象
     * @return 插入后Item对象
     */
    Item insertSelective(Item item);

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
     * @param json json字符串
     * @param item 需更新Item对象
     * @return 更新后Item对象
     */
    Item updateItemInfo(String json, Item item);

    /**
     * 根据商品id设置图片名
     *
     * @param itemId 商品id
     * @param image 图片名
     * @return 设置后Item对象
     */
    Item setImageById(long itemId, String image);

    /**
     * 修改商品id对应商品的审核状态
     *
     * @param itemId  商品id
     * @param adminId 管理员id
     * @param status  新审核状态
     * @return 修改后的Item对象
     * @see com.zb.entity.Item
     */
    Item setAuditStatus(long itemId, long adminId, int status);

    /**
     * 根据状态分页查询所有商品
     *
     * @param status 审核装态
     * @param pageNo 起始页码
     * @param pageSize 分页大小
     * @return 商品列表
     */
    Page<Item> findAllPageByStatus(int status, int pageNo, int pageSize);

    /**
     * 分页查询所有商品
     *
     * @param pageNo   起始页码
     * @param pageSize 分页大小
     * @return 商品列表
     */
    Page<Item> findAllPage(int pageNo, int pageSize);

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
     * @param pageNo     起始页码
     * @param pageSize   分页大小
     * @return 查询结果
     */
    Page<Item> searchByPage(String searchInfo, int pageNo, int pageSize);
}
