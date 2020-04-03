package com.zhangwuji.api.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @program: railway_manager
 * @description: 整合返回结果数据
 * @author: chenglin
 * @create: 2020-01-01 10:18
 **/
public class ResultMapUtil {

    /**
     * 返回添加结果数据
     *
     * @param count
     * @return
     */
    public static Map<String, Object> getAddResult(int count) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (count > 0) {
            resultMap.put("code", "200");
            resultMap.put("description", "添加成功");
        } else {
            resultMap.put("code", "501");
            resultMap.put("description", "添加失败");
        }

        return resultMap;
    }

    /**
     * 返回更新结果数据
     *
     * @param count
     * @return
     */
    public static Map<String, Object> getUpdateResult(int count) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (count > 0) {
            resultMap.put("code", "200");
            resultMap.put("description", "更新成功");
        } else {
            resultMap.put("code", "501");
            resultMap.put("description", "更新失败");
        }

        return resultMap;
    }

    /**
     * 返回删除结果数据
     *
     * @param count
     * @return
     */
    public static Map<String, Object> getDeleteResult(int count) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (count > 0) {
            resultMap.put("code", "200");
            resultMap.put("description", "删除成功");
        } else {
            resultMap.put("code", "501");
            resultMap.put("description", "删除失败");
        }

        return resultMap;
    }
}
