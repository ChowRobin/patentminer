package org.patentminer.controller;

import org.patentminer.bean.ResultBean;
import org.patentminer.model.User;
import org.patentminer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResultBean<List<User>> listUser(
            @RequestParam(name = "pageno", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request) {
        return new ResultBean<List<User>>(userService.listAll(request, pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResultBean<User> findOneById(@PathVariable int id) {
        return new ResultBean<>(userService.findById(id));
    }

}
