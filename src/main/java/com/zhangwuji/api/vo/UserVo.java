package com.zhangwuji.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: railway_manager
 * @description: 用户类vo
 * @author: chenglin
 * @create: 2020-01-03 06:00
 **/
@Data
@ApiModel("用户信息表vo")
@Accessors(chain = true)
public class UserVo {
    private int id;
    @ApiModelProperty("账号")
    private String userName;
    @ApiModelProperty(value = "头像")
    private String photo;
    @ApiModelProperty("用户名")
    private String displayName;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("性别代码")
    private int sex;
    @ApiModelProperty("性别名称")
    private String sexName;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
    @ApiModelProperty("状态名称")
    private String statusName;
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("角色编码")
    private String roleCode;
}
