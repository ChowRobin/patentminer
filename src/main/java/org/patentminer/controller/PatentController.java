package org.patentminer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.patentminer.bean.ResultBean;
import org.patentminer.model.Patent;
import org.patentminer.service.PatentService;
import org.patentminer.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "/patent", tags = "专利接口模块")
@RestController
@RequestMapping("/patent")
public class PatentController {

    @Autowired
    private PatentService patentService;

    /**
     *
     * @param pageNo
     * @param pageSize
     * @param request
     * @return
     */
    @ApiOperation(value = "查询专利", notes = "根据条件查询专利")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页号", required = false, defaultValue = "1", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = false, defaultValue = "10", dataType = "Integer"),
    })
    @GetMapping("")
    public ResultBean<List<Patent>> listPatentByCondition(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request) {
        return new ResultBean<>(patentService.listByCondition(
                CommonUtil.getParameterMap(request), pageNo, pageSize));
    }
}
