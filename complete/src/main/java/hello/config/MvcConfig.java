package hello.config;

import hello.web.RequestContext;
import hello.web.RequestContextFilter;
import hello.web.RequestContextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(MvcConfig.class);


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
       //
    }

    /**
     * Automatically injects the RequestContextFilter into the servlet filter chain.
     * @return
     */
    @Bean
    public RequestContextFilter requestContextFilter() {
        logger.info("Instantiating RequestContextFilter bean");
        return new RequestContextFilter();
    }

    /**
     * Configures a request scoped bean that will be populated by the filter above (RequestContextFilter).
     * @return
     */
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestContext requestContext() {
        return new RequestContextImpl();
    }

}
