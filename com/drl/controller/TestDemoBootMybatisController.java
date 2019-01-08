package com.drl.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.drl.entity.Demo;
import com.drl.service.DemoService;

@RestController
@RequestMapping("/demo")
public class TestDemoBootMybatisController {
	@Resource
    private DemoService demoService;

    @RequestMapping("/showDemo")
    @ResponseBody
    public Demo toIndex(HttpServletRequest request, Model model){
        int demoId = Integer.parseInt(request.getParameter("id"));
        Demo demo = this.demoService.getDemoById(demoId);
        return demo;
    }
}
