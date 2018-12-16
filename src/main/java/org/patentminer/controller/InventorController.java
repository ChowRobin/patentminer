package org.patentminer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.patentminer.bean.ResultBean;
import org.patentminer.model.Inventor;
import org.patentminer.model.InventorDTO;
import org.patentminer.service.InventorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "发明人模块")
@RestController
@RequestMapping("/inventor")
public class InventorController {

    @Autowired
    private InventorService inventorService;

    @ApiOperation(value = "查询发明人", notes = "通过名字查询发明人")
    @ApiImplicitParam(value = "name", required = true, dataType = "String")
    @GetMapping("")
    public ResultBean<InventorDTO> findByName(@RequestParam String name, HttpServletResponse res) {
        return new ResultBean<>(inventorService.findByName(name), res);
    }
}
