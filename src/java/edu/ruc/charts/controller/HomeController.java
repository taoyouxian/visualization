package edu.ruc.charts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 这只是一个关于跳转到首页的控制器
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    /**
     * @return
     * @Description: 跳转到首页的控制器，处理来自客户端的根目录请求及home请求，请求方式为gei方式
     */
    @RequestMapping({"/index"})
    public String home() {
        return "home";
    }


}
