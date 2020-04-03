package com.zhangwuji.api.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: railway_manager
 * @description: 添加用户信息类
 * @author: chenglin
 * @create: 2020-01-03 07:30
 **/
@Data
@ApiModel("用户信息")
@Accessors(chain = true)
public class UserAdd {

    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("姓名")
    private String displayName;
    @ApiModelProperty("手机号码")
    private String phone;
    @ApiModelProperty("性别（1男 0女）")
    private Integer sex;
    @ApiModelProperty("状态（1启用 0不启用）")
    private Integer status;
    @ApiModelProperty("角色编码")
    private String roleCode;
}
