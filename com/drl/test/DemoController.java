package com.drl.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 演示spring-boot:
 * @author Administrator
 */
@Controller
public class DemoController {

//	在controller上加注解@Controller 和@RestController都可以在前端调通接口，
//	但是二者的区别在于.
//	因为当使用@Controller 注解时，spring默认方法返回的是view对象（页面）。而加上@ResponseBody，则方法返回的就是具体对象了。
//	@RestController的作用就相当于@Controller+@ResponseBody的结合体.
	
	/**
	 * 直接显示Hello World
	 * @return
	 */
	@RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "Hello World";
    }
	
//	
//	/**修改了配置文件，此处失效
//	 * 跳转到/boot1/src/main/resources/templates/test.html的页面
//	 * @return
//	 */
	@RequestMapping("/test")
    public String test(){
        return "test";
    }
	
	
	/**
	 * 跳转到/boot1/src/main/resources/templates/test.html的页面
	 * @return
	 */
	@RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
