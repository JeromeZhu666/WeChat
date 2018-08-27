package xin.jerome.wechat.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import xin.jerome.wechat.WechatApplication;

/**
 * init Servlet for package to war
 *
 * @author Jerome Zhu
 * @since 2018.08.27 13:24
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WechatApplication.class);
    }

}
