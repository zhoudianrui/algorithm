package io.frlib.algorithm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.LongAdder;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    private final LongAdder count = new LongAdder();

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name",defaultValue = "china") String name){
        count.increment();
        logger.debug("pv is " + count);
        return "Hello " + name + ", welcome to the kingdom of algorithm!";
    }
}
