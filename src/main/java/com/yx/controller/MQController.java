package com.yx.controller;

import com.yx.bean.Person;
import com.yx.service.MQProducer;
import com.yx.utils.GenResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "mq")
public class MQController {

    @Resource
    private MQProducer mqProducer;

    @RequestMapping(value = "test_one")
    @ResponseBody
    public Map<String, Object> testOne() throws IOException {
        mqProducer.sendDataToQueue("queue_one_key", new Person(1, "中国", 12));
        return GenResult.SUCCESS.genResult();
    }

    @RequestMapping(value = "test_two")
    @ResponseBody
    public Map<String, Object> testTwo() {
        mqProducer.sendDataToQueue("queue_two_key", "queue_two_value");
        return GenResult.SUCCESS.genResult();
    }
}
