package xin.jerome.weichat.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import xin.jerome.weichat.WeichatApplication;

/**
 * init Servlet for package to war
 *
 * @author Jerome Zhu
 * @since 2018.08.27 13:24
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WeichatApplication.class);
    }

}
