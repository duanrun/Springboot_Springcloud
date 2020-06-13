package com.dt.demospringboot_redis01.controller;


import com.dt.demospringboot_redis01.pojo.User;
import com.dt.demospringboot_redis01.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestResdisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/addKey")
    public Object addKey(){
        stringRedisTemplate.opsForValue().set("test1","大木頭");
        return "redis is ok!";
    }
    @RequestMapping("/getValue")
    public Object getValue(){
       String value= stringRedisTemplate.opsForValue().get("test1");
        System.out.println("-------->接收redis的值:"+value);
        return value;
    }

    @RequestMapping("/addKey2")
    public Object addKey2(){
        User user = new User();
        user.setId(110);
        user.setUname("小木頭");
        user.setUpwd("123456");
        //这里还可以处理arraylist，hashmap
        String jstr = JsonUtils.objToString(user);//通过工具类，把对象转换成json[jackson]
        stringRedisTemplate.opsForValue().set("test2",jstr);
        return "redis json is ok!";
    }

    @RequestMapping("/getValue2")
    public Object getValue2(){
        String value= stringRedisTemplate.opsForValue().get("test2");
        System.out.println("-------->接收数据:"+value);
        return JsonUtils.stringToObj(value,User.class);
    }
}
