package com.ryz.myservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private DiscoveryClient client;

    @Autowired
    AddServiceFeign addServiceFeign;

    @Autowired
    private RestTemplateBuilder builder;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        List<String> serviceList = client.getServices();
        for (int i = 0; i < serviceList.size(); i++) {
            logger.info("获取第"+i+"个 service id ：" + serviceList.get(i));
            ServiceInstance instances = client.getInstances(serviceList.get(i)).get(0);
            logger.info("/hello,host:" + instances.getHost() + ",service_id:" + instances.getServiceId());
        }
        return "Hello World";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(int a,int b) {
        List<ServiceInstance> instances = client.getInstances("my-add-service");
        ServiceInstance serviceInstance = instances.stream().findFirst().get();
        logger.info("add service :" + serviceInstance.getHost() + ",service_id:" + serviceInstance.getServiceId());
        Map map = new HashMap();
        map.put("a",a);
        map.put("b",b);
        RestTemplate restTemplate = builder.build();
        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/adda?a="+a+"&b="+b;
        logger.info("要请求的url :" + url);
        Object json = restTemplate.getForObject(url,Integer.class,map);
        return String.valueOf(json);
    }

    @RequestMapping(value = "/addadd", method = RequestMethod.GET)
    public String addadd(int a,int b) {
        List<ServiceInstance> instances = client.getInstances("my-add-service");
        ServiceInstance serviceInstance = instances.stream().findFirst().get();
        logger.info("add service :" + serviceInstance.getHost() + ",service_id:" + serviceInstance.getServiceId());
        Map map = new HashMap();
        map.put("a",a);
        map.put("b",b);
        RestTemplate restTemplate = builder.build();
        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/addadd?a={a}&b={b}";
        logger.info("要请求的url :" + url);
        Object json = restTemplate.getForObject(url,Integer.class,a,b);
        return String.valueOf(json);
    }

    @RequestMapping(value = "/addaddf", method = RequestMethod.GET)
    public String addaddf(int a,int b) {
        List<ServiceInstance> instances = client.getInstances("my-add-service");
        ServiceInstance serviceInstance = instances.stream().findFirst().get();
        logger.info("add service :" + serviceInstance.getHost() + ",service_id:" + serviceInstance.getServiceId());
        Map map = new HashMap();
        map.put("a",a);
        map.put("b",b);
        Object json = addServiceFeign.addadd(a,b);
        return String.valueOf(json);
    }
}
