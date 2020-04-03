package com.zhangwuji.api.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @program: railway_manager
 * @description: 用户类
 * @author: lijiwen
 * @create: 2019-09-15 07:30
 **/
@Data
@ApiModel("用户信息表")
@Accessors(chain = true)
public class User {

    public User() {

    }

    public User(UserAdd userAdd) {
        this.userName = userAdd.getUserName().trim();
        this.displayName = userAdd.getDisplayName();
        this.sex = userAdd.getSex();
        this.phone = userAdd.getPhone();
        this.status = userAdd.getStatus();
        this.password = "000000";
    }

    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("展示名")
    private String displayName;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("二级密码")
    private String secondPwd;
    @ApiModelProperty("上次登陆时间")
    private Date lastLoginTime;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("性别")
    private Integer sex;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
    @ApiModelProperty("图片")
    private String photo;
    /**
     * 关联属性 1
     */
    @ApiModelProperty("用户角色")
    private List<String> roleCode;
    private Integer id;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
}
