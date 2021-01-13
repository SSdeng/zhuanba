package com.zb.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json字符串转对象工具
 *
 * @author lijiacheng
 * @version 1.0
 */
public class JsonTransfer {

    private static ObjectMapper mapper;

    /**
     * json非空值属性更新对象
     *
     * @param json json字符串
     * @param object 待更新对象
     * @param <T> 对象类型
     * @return 更新后
     */
    public static <T> T updateSelective(String json, T object){
        mapper = new ObjectMapper();
        // 利用jackson相关API，实现非null值的合并更新
        T newObject = null;
        try {
            newObject = mapper.readerForUpdating(object).readValue(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return newObject;
    }
}
