package com.example.Redis.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    RedisTemplate<String, User> redisUserTemplate;

    @PostMapping("/add_user")
    public void addUser(@RequestParam("id") String id,
                        @RequestBody() User user){
        redisUserTemplate.opsForValue().set(id, user);
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable() String id){
        return redisUserTemplate.opsForValue().get(id);
    }

    @PostMapping("/lpush")
    public void lPushUser(@RequestParam("id") String id,
                          @RequestBody() User user){
        redisUserTemplate.opsForList().leftPush(id, user);
    }

    @GetMapping("/lpop")
    public List<User> lPopUser(@RequestParam("id") String id, @RequestParam("count") Long count){
        return redisUserTemplate.opsForList().leftPop("users",2);
    }
}
