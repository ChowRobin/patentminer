package org.patentminer.controller;

import io.swagger.annotations.Api;
import org.patentminer.bean.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "通用")
@RestController
public class CommonController {

    @RequestMapping("/unlogin")
    public ResultBean<String> unlogin() {
        return new ResultBean<>(ResultBean.NO_LOGIN,"not login");
    }

    @RequestMapping("/unaccess")
    public ResultBean<String> unaccess() {
        return new ResultBean<>(ResultBean.NO_PERMISSION, "have not permission to access");
    }
}
