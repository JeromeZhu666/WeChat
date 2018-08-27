package xin.jerome.weichat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.jerome.weichat.entity.TextMsg;
import xin.jerome.weichat.util.CheckUtil;
import xin.jerome.weichat.util.MsgUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Map;

/**
 * WeiChat check server
 *
 * @author Jerome Zhu
 * @since 2018.08.27 10:38
 */
@RestController
public class CheckController {

    @GetMapping("/")
    public String checkServer(@RequestParam String signature, @RequestParam String timestamp,
                              @RequestParam String nonce, @RequestParam String echostr) {

        if(CheckUtil.checkSignature(signature,timestamp,nonce)) {
            return echostr;
        }
        return null;
    }


}
