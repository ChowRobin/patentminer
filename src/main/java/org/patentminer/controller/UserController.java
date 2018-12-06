package org.patentminer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页号", required = false, defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = false, defaultValue = "10", dataType = "Integer"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
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
    @GetMapping("/token")
    public ResultBean<String> login(@RequestParam String userName, @RequestParam String password) {
        return new ResultBean<>(userService.login(userName, password));
    }

    @ApiOperation(value = "注册新用户", notes = "通过用户名密码注册新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("")
    public ResultBean<Integer> register(@RequestBody User user) {
        return new ResultBean<>(userService.register(user));
    }

    @ApiOperation(value = "更新用户信息", notes = "通过用户id更改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, dataType = "String")
    })
    @PutMapping("/{id}")
    public ResultBean<Integer> updateUser(@PathVariable Integer id,
                                          @RequestBody User user) {
        return new ResultBean<>(userService.update(id, user));
    }

    @ApiOperation(value = "删除用户", notes = "通过用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    @DeleteMapping("/{id}")
    public ResultBean<Integer> deleteUser(@PathVariable Integer id) {
        return new ResultBean<>(userService.delete(id));
    }

}
