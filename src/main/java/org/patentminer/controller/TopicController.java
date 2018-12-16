package org.patentminer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.patentminer.bean.ResultBean;
import org.patentminer.model.TopicDTO;
import org.patentminer.model.TopicDetail;
import org.patentminer.service.TopicService;
import org.patentminer.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "主题模块")
@RestController
@RequestMapping("topic")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private JSONUtil jsonUtil;

    @ApiOperation(value = "获取所有主题")
    @GetMapping("/all")
    public ResultBean<List<TopicDTO>> listAll() {
        return new ResultBean<>(topicService.listAll());
    }

    @ApiOperation(value = "查询主题详情", notes = "根据主题id查询详情")
    @ApiImplicitParam(name = "主题id", required = true, dataType = "String")
    @GetMapping("/detail")
    public ResultBean<TopicDetail> findById(@RequestParam int id) {
        return new ResultBean<>(topicService.findDetailById(id));
    }

//    @GetMapping("/import")
//    public void importD() {
//        jsonUtil.importTopicJson();
//    }

}
