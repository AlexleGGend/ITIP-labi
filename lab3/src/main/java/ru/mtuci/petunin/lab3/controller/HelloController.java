package ru.mtuci.petunin.lab3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Привет, Spring Boot!";
    }

    @GetMapping("/goodbye")
    public String goodbye() {
        return "До свидания, Spring Boot!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Привет, " + name + "!";
    }

    @GetMapping("/profile")
    public String profile(@RequestParam String name, @RequestParam int course) {
        return "Студент: " + name + ", курс: " + course;
    }
}
