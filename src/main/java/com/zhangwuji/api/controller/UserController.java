package com.zhangwuji.api.controller;

import com.google.common.collect.Maps;
import com.zhangwuji.api.po.UserAdd;
import com.zhangwuji.api.service.system.UserService;
import com.zhangwuji.api.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: user info
 * @author: lijiwen
 * @create: 2019-09-09 20:32
 **/
@Api(tags = "用户信息接口", value = "用户信息接口")
@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有用户", notes = "查询方法")
    public Map<String, Object> getListUser(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String advanceColumn) {
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize < 1) {
            pageSize = 10;
        }
        int count = userService.getCount(advanceColumn);
        List<UserVo> userList = userService.getList(pageNum, pageSize, advanceColumn);

        //返回结果
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("code", "200");
        resultMap.put("description", "查询成功");
        resultMap.put("allCount", count);
        resultMap.put("currentCount", userList.size());
        resultMap.put("userList", userList);

        return resultMap;
    }

    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加用户信息", notes = "用户名唯一")
    public Map<String, Object> addUser(UserAdd user) {
        return userService.add(user);
    }

    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    public Map<String, Object> editUser(UserAdd user) {
        return userService.editUser(user);
    }

    @PostMapping("/editStatus")
    @ResponseBody
    @ApiOperation(value = "修改用户状态", notes = "根据用户名修改用户状态，用户名禁止修改")
    public Map<String, Object> editStatus(@ApiParam("用户名") @RequestParam String userName,
                                          @ApiParam("状态编码：1启用，0禁止") @RequestParam Integer status) {
        return userService.editUserStatus(userName, status);
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    @ApiOperation(value = "重置用户密码", notes = "重置用户密码")
    public Map<String, Object> resetPassword(@RequestParam String userName, @RequestParam String oldPassword,
                                             @RequestParam String newPassword, @RequestParam String pwdConfirm) {
        return userService.resetPassword(userName, oldPassword, newPassword, pwdConfirm);
    }

    @GetMapping("/personInfo")
    @ResponseBody
    @ApiOperation(value = "个人中心", notes = "个人中心")
    public UserVo getPersonInfo(@RequestParam String userId) {
        return userService.getPersonInfo(userId);
    }

    @PostMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile file, String userId) {
        try {
            BASE64Encoder bEncoder = new BASE64Encoder();
            String base64EncoderImg = bEncoder.encode(file.getBytes()).replaceAll("[\\s*\t\n\r]", "");

            userService.uploadImage(base64EncoderImg, userId);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "success";
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除用户信息", notes = "根据用户名删除用户信息")
    public Map<String, Object> delete(@ApiParam("用户名") @RequestParam String userName) {

        //返回结果
        Map<String, Object> resultMap = userService.deleteUser(userName);

        return resultMap;
    }
}
