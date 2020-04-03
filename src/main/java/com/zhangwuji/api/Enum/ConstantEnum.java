package com.zhangwuji.api.Enum;

/**
 * Created by chenglin on 2020/1/4.
 */
public interface ConstantEnum {

    /**
     * 权限菜单类别：目录
     */
    int menuType_dir = 0;
    /**
     * 权限菜单类别：菜单
     */
    int menuType_menu = 1;
    /**
     * 权限菜单类别：按钮
     */
    int menuType_btn = 2;

    /**
     * 是否叶子节点：否
     */
    int isLeaf_no = 0;
    /**
     * 是否叶子节点：是
     */
    int isLeaf_yes = 1;

    /**
     * 状态：启用
     */
    int status_yes = 1;
    /**
     * 状态：禁用
     */
    int status_no = 0;

    String dict_type = "dictType";
}
