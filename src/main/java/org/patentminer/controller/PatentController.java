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
import org.springframework.web.bind.annotation.*;

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
            @ApiImplicitParam(name = "inventionTitle", value = "专利名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "applicationDate", value = "申请日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "publicationDate", value = "发布日期", required = true, dataType = "String"),
    })
    @GetMapping("")
    public ResultBean<List<Patent>> listPatentByCondition(
            @RequestParam(name = "pageNo", required = false, defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize,
            HttpServletRequest request) {
        return new ResultBean<>(patentService.listByCondition(
                CommonUtil.getParameterMap(request), pageNo, pageSize));
    }

    @ApiOperation(value = "添加专利", notes = "增加专利")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inventionTitle", value = "专利名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "abstractStr", value = "概要", dataType = "String"),
            @ApiImplicitParam(name = "applicants", value = "申请单位列表", dataType = "List<Applicant>"),
            @ApiImplicitParam(name = "applicationDate", value = "申请日期", dataType = "String"),
            @ApiImplicitParam(name = "publicationDate", value = "发布日期", dataType = "String"),
    })
    @PostMapping("")
    public ResultBean<String> createPatent(@RequestBody Patent patent) {
        return new ResultBean<>(patentService.create(patent));
    }

    @ApiOperation(value = "更新专利信息", notes = "通过专利号更新专利信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "专利号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "inventionTitle", value = "专利名", dataType = "String"),
            @ApiImplicitParam(name = "abstractStr", value = "概要", dataType = "String"),
            @ApiImplicitParam(name = "applicants", value = "申请单位列表", dataType = "List<Applicant>"),
            @ApiImplicitParam(name = "applicationDate", value = "申请日期", dataType = "String"),
            @ApiImplicitParam(name = "publicationDate", value = "发布日期", dataType = "String"),
    })
    @PutMapping("")
    public ResultBean<String> updatePatent(@RequestParam String id, @RequestBody Patent patent) {
        return new ResultBean<>(patentService.update(patent, id));
    }

    @ApiOperation(value = "删除专利", notes = "根据专利号删除专利")
    @ApiImplicitParam(name = "id", value = "专利号", required = true, dataType = "String")
    @DeleteMapping("")
    public ResultBean<String> deletePatent(@RequestParam String id) {
        return new ResultBean<>(patentService.delete(id));
    }
}
