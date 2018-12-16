package org.patentminer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.patentminer.bean.ResultBean;
import org.patentminer.model.CompanyDTO;
import org.patentminer.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "公司模块")
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value = "查询公司", notes = "通过名字查询公司")
    @ApiImplicitParam(value = "name", required = true, dataType = "String")
    @GetMapping("")
    public ResultBean<CompanyDTO> findByName(@RequestParam String name, HttpServletResponse res) {
        return new ResultBean<CompanyDTO>(companyService.findByName(name), res);
    }

}
