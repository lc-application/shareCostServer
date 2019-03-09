package com.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private HelloRepository helloRepository;

    @RequestMapping("/api/hello/insert")
    public Hello insertHelloObject() {
        Hello obj = new Hello("Hello my team!");
        helloRepository.save(obj);
        helloRepository.flush();
        return obj;
    }

    @RequestMapping("/api/hello/insert/{msg}")
    public Hello insertMessage(@PathVariable("msg") String message) {
        Hello obj = new Hello(message);
        helloRepository.save(obj);
        helloRepository.flush();
        return obj;
    }

    @RequestMapping("/api/hello/select/all")
    public List<Hello> selectAllHelloObjects() {
        List<Hello> hellos =
                (List<Hello>)helloRepository.findAll();
        return hellos;
    }

    @RequestMapping("/api/hello/string")
    public String sayHello() {
        return "Hello Team!";
    }

    @RequestMapping("/api/hello/object")
    public Hello sayHelloObject() {
        Hello obj = new Hello("Hello Team!");
        return obj;
    }

}