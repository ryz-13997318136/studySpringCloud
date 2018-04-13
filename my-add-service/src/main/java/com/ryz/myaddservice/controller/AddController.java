package com.ryz.myaddservice.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddController {
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @RequestMapping(value = "/adda", method = RequestMethod.GET)
    public String adda(int a,int b){
        System.out.println("add 服务被请求，参数为"+a+ ", "+b);
        return String.valueOf(a+ b);
    }

    @RequestMapping(value = "/addadd", method = RequestMethod.GET)
    public String addadd(@RequestParam Integer a, @RequestParam Integer b){
        System.out.println("add 服务被请求，参数为"+a+ ", "+b);
        return String.valueOf(a+ b);
    }

    @RequestMapping(value = "/addaddf/{a}/{b}", method = RequestMethod.GET)
    public String addaddf(@PathVariable("a") Integer a, @PathVariable("b") Integer b){
        System.out.println("add 服务被请求，参数为"+a+ ", "+b);
        return String.valueOf(a+ b);
    }
}
