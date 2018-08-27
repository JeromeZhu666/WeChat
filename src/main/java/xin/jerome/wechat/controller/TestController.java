package xin.jerome.wechat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test controller
 *
 * @author Jerome Zhu
 * @since 2018.08.27 11:19
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String helloTest() {
        return "this is deploy test, deploy success !";
    }

}
