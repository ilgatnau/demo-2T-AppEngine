package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/")
public class DemoController {

    @Autowired
    private DemoTableRepository demoTableRepository;

   @GetMapping("")
   public String test() {
       return "HELLO WORLD";
   }

   @GetMapping("/data")
   public void testData() {
    //    DemoTable message = demoTableRepository.findById(1);
    //    return message.getMessage();
   }

}