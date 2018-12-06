package com.lhm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "PythonController", description = "爬虫")
@RestController
@RequestMapping(value="/python",produces="text/plain;charset=utf-8")
public class PythonController {

    @ApiOperation(value = "解析HTML文件")
    @GetMapping(value="getUserList.do")
    public String analysisHtml() {
        return null;
    }
}
