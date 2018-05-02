package com.pr.corina.lab4pr.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@Configuration
@ComponentScan({ "com.mkyong.form.web", "com.mkyong.form.service", "com.mkyong.form.dao",
		"com.mkyong.form.exception", "com.mkyong.form.validator", "com.mkyong.form.model" })
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}


      @Bean(name="multipartResolver")
	public StandardServletMultipartResolver resolver(){
		return new StandardServletMultipartResolver();
	}
  
//    https://memorynotfound.com/spring-mvc-file-upload-example-validator/https://memorynotfound.com/spring-mvc-file-upload-example-validator/
        
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
    
//    /**Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
//     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
//     * This is a workaround for this issue.
//     */
//    @Override
//    public void configurePathMatch(PathMatchConfigurer matcher) {
//        matcher.setUseRegisteredSuffixPatternMatch(true);
//    }
    
    
}