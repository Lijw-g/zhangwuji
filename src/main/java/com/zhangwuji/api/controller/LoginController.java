package com.zhangwuji.api.controller;

import com.google.common.base.Preconditions;
import com.zhangwuji.api.Enum.ConstantEnum;
import com.zhangwuji.api.po.User;
import com.zhangwuji.api.service.system.UserService;
import com.zhangwuji.api.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: railway_manager
 * @description: 用户注册及登录验证
 * @author: lijiwen
 * @create: 2019-09-15 15:48
 **/
@Api(tags = "登录及注册功能接口", value = "登录及注册功能接口")
@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ResponseBody
    @ApiOperation(value = "用户登录", notes = "用户登录")
    public Map<String, Object> login(HttpServletRequest request,
                                     @RequestParam String userName,
                                     @RequestParam String password,
                                     @RequestParam(required = false) String imageCode,
                                     @RequestParam(required = false) String userTaskId) {

        //检查验证码
        Map<String, Object> resultMap = new HashMap<String, Object>();

//        if (StringUtils.isBlank(imageCode)) {
//            resultMap.put("code", "501");
//            resultMap.put("description", "请输入验证码");
//            return resultMap;
//        }
//        if (StringUtils.isBlank(userTaskId)) {
//            resultMap.put("code", "502");
//            resultMap.put("description", "唯一标识错误");
//            return resultMap;
//        }
//
//        //验证不通过
//        if (!veriCodeService.verifyCode(imageCode.trim(), userTaskId.trim())) {
//            resultMap.put("code", "503");
//            resultMap.put("description", "验证码错误");
//            return resultMap;
//        }

        //核对参数
        if (StringUtils.isBlank(userName)) {
            resultMap.put("code", "504");
            resultMap.put("description", "请输入用户名");
            return resultMap;
        }
        if (StringUtils.isBlank(password)) {
            resultMap.put("code", "505");
            resultMap.put("description", "请输入密码");
            return resultMap;
        }

        //检查用户
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userNameEqual", userName);
        paramMap.put("passwordEqual", password);
        List<UserVo> userList = userService.getList(paramMap);

        if (userList == null || userList.size() < 1) {
            resultMap.put("code", "506");
            resultMap.put("description", "用户名或密码错误");
            return resultMap;
        } else if (userList.size() > 1) {
            resultMap.put("code", "507");
            resultMap.put("description", "存在多条用户信息，请及时联系管理员");
            return resultMap;
        }

        if (userList.get(0).getStatus() != ConstantEnum.status_yes) {
            resultMap.put("code", "508");
            resultMap.put("description", "用户已禁用");
            return resultMap;
        }

        resultMap.put("code", "200");
        resultMap.put("description", "登录成功");
        resultMap.put("user", userList.get(0));

        //清除缓存中验证码
        // veriCodeService.delCacheCode(imageCode.trim(), userTaskId.trim());
        return resultMap;
    }

    @PostMapping("/registerUser")
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Map<String, Object> registerUser(@RequestParam String userName, @RequestParam String password,
                                            @RequestParam String pwdConfirm, @RequestParam String phone,
                                            @RequestParam(defaultValue = "0") int sex) {
        Preconditions.checkNotNull(phone, "手机号不能为空");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (StringUtils.isBlank(userName)) {

            resultMap.put("code", "501");
            resultMap.put("description", "用户名不能为空");
            return resultMap;
        }

        //检查用户名是否唯一
        if (!userService.checkUserName(userName)) {
            resultMap.put("code", "502");
            resultMap.put("description", "用户名已被注册");
            return resultMap;
        }

        //检查两次输入密码是否一致
        if (StringUtils.isBlank(password)) {
            resultMap.put("code", "503");
            resultMap.put("description", "密码不能为空");
            return resultMap;
        }
        if (!password.trim().equals(pwdConfirm)) {
            resultMap.put("code", "504");
            resultMap.put("description", "确认密码与密码不一致");
            return resultMap;
        }

        //添加用户
        User user = new User();
        user.setUserName(userName.trim())
                .setDisplayName(user.getUserName())
                .setPassword(password.trim())
                .setCreateUser(user.getUserName())
                .setUpdateUser(user.getUserName())
                .setPhone(phone.trim())
                .setSex(sex);
        user.setStatus(ConstantEnum.status_yes);
        try {
            int num = userService.add(user);

            if (num > 0) {
                resultMap.put("code", "200");
                resultMap.put("description", "注册成功");
                resultMap.put("user", user);
                return resultMap;
            } else {
                resultMap.put("code", "505");
                resultMap.put("description", "注册失败");
                return resultMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", "506");
            resultMap.put("description", "注册失败");
            return resultMap;
        }
    }
}
