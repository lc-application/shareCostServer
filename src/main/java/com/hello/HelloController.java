package com.hello;

import com.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final HelloRepository helloRepository;

    @Autowired
    public HelloController(HelloRepository helloRepository) {
        this.helloRepository = helloRepository;
    }

    @RequestMapping("/")
    public String helloIndex(){
        return "Welcome";
    }

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
        EmailService.sendRegisterEmail(message, "Hello");
        helloRepository.save(obj);
        helloRepository.flush();
        return obj;
    }

    @RequestMapping("/api/hello/select/all")
    public List<Hello> selectAllHelloObjects() {
        return helloRepository.findAll();
    }

    @RequestMapping("/api/hello/string")
    public String sayHello() {
        return "Hello Team!";
    }

    @RequestMapping("/api/hello/object")
    public Hello sayHelloObject() {
        return new Hello("Hello Team!");
    }

}