package org.patentminer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.patentminer.bean.PageResultBean;
import org.patentminer.bean.ResultBean;
import org.patentminer.model.User;
import org.patentminer.service.UserService;
import org.patentminer.util.CommonUtil;
import org.patentminer.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "/user", tags = "用户接口模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页获取用户信息
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @ApiOperation(value = "分页获取用户信息", notes = "展示用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页号", required = false, defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = false, defaultValue = "10", dataType = "Integer"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @GetMapping("")
    public PageResultBean<Page<User>> listUser(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request, HttpServletResponse response) {
        return new PageResultBean<>(userService.listByCondition(
                CommonUtil.getParameterMap(request), pageNo, pageSize), response);
    }

    /**
     * 登录获取token
     * @param user
     * @return
     */
    @ApiOperation(value = "登录获取token", notes = "用户名密码登录获取token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("/login")
    public ResultBean<String> login(@RequestBody User user, HttpServletResponse res) {
        return new ResultBean<>(userService.login(user.getUserName(), user.getPassword()), res);
    }

    /**
     * 注册新用户
     * @param user
     * @return
     */
    @ApiOperation(value = "注册新用户", notes = "通过用户名密码注册新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })
    @PostMapping("")
    public ResultBean<Integer> register(@RequestBody User user, HttpServletResponse res) {
        return new ResultBean<>(userService.register(user), res);
    }

    /**
     *
     * @param user
     * @param request
     * @return
     */
    @ApiOperation(value = "更新用户信息", notes = "通过token更改用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "请求头带登录凭证", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, dataType = "String")
    })
    @PutMapping("")
    public ResultBean<Integer> updateUser(@RequestBody User user,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        return new ResultBean<>(userService.update(
                JWTUtil.getId(CommonUtil.getHeader(request, "token")), user), response);
    }

    /**
     *
     * @param req
     * @param res
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "通过token删除用户")
    @ApiImplicitParam(name = "token", value = "请求头带登录凭证", required = true, dataType = "String")
    @DeleteMapping("")
    public ResultBean<Integer> deleteUser(HttpServletRequest req, HttpServletResponse res) {
        return new ResultBean<>(userService.delete(
                JWTUtil.getId(CommonUtil.getHeader(req, "token"))), res);
    }

}
