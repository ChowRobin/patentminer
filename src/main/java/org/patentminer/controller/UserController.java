package org.patentminer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.patentminer.bean.ResultBean;
import org.patentminer.model.User;
import org.patentminer.service.UserService;
import org.patentminer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "/user", tags = "用户接口模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页获取用户信息", notes = "展示用户信息")
    @GetMapping("")
    public ResultBean<List<User>> listUser(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request) {
        return new ResultBean<>(userService.listByCondition(
                CommonUtil.getParameterMap(request), pageNo, pageSize));
    }

    @ApiOperation(value = "登录获取token", notes = "用户名密码登录获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("/login")
    public ResultBean<String> login(@RequestBody User user) {
        return new ResultBean<>(userService.login(user));
    }

    @ApiOperation(value = "注册新用户", notes = "通过用户名密码注册新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("/register")
    public ResultBean<Integer> register(@RequestBody User user) {
        return new ResultBean<>(userService.register(user));
    }

    @PostMapping("/")

}
