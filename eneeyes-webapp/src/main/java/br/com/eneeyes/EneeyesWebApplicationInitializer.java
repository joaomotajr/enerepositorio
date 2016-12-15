package br.com.eneeyes;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class EneeyesWebApplicationInitializer implements WebApplicationInitializer,ServletContainerInitializer {
    Log log = LogFactory.getLog(getClass());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        String defaultProfile = System.getProperty("spring.profiles.default");

        if (defaultProfile == null || defaultProfile.trim().length() < 1) {
            System.setProperty("spring.profiles.default","mi");
        }


        log.info(String.format("app.env: %s", System.getProperty("spring.profiles.default")));

        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        servletContext.addFilter("hiddenHttpMethodFilter", hiddenHttpMethodFilter)
        //.getServletNameMappings().add("/*");
        .addMappingForUrlPatterns(null, false, "/*");

        CharacterEncodingFilter characterEncondingFilter = new CharacterEncodingFilter();
        characterEncondingFilter.setEncoding("utf8");
        characterEncondingFilter.setForceEncoding(true);
        servletContext.addFilter("characterEncodingFilter", characterEncondingFilter)
        //.getServletNameMappings().add("/*");
        .addMappingForUrlPatterns(null, true, "/*");

        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();
        FilterRegistration.Dynamic filter = servletContext.addFilter("springSecurityFilterChain", springSecurityFilterChain);
        EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST);
        filter.addMappingForUrlPatterns(dispatcherTypes, false, "/*");

        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("br.com.eneeyes");
        return context;
    }

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        onStartup(ctx);
    }
}