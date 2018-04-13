package com.ryz.myservice.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("my-add-service")
public interface AddServiceFeign {
    @RequestMapping(value = "/addaddf/{a}/{b}", method = RequestMethod.GET)
    String addadd(@PathVariable("a") int a,@PathVariable("b") int b);
}
