package hu.zsoltii.jsf.config;

import org.apache.catalina.Context;
import org.primefaces.webapp.filter.FileUploadFilter;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

@Configuration
public class JsfConfig {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                context.addWelcomeFile("index.xhtml");
                super.postProcessContext(context);
            }
        };
    }

    @Bean
    public ServletRegistrationBean facesServletRegistrationBean() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
        registrationBean.setName("Faces Servlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "false");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
            servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
            servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
            servletContext.setInitParameter("primefaces.UPLOADER", "commons");
            servletContext.setInitParameter("primefaces.THEME", "omega");
            servletContext
                    .setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", Boolean.TRUE
                            .toString());
            servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        };
    }

    @Bean
    public FilterRegistrationBean facesUploadFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new FileUploadFilter(), facesServletRegistrationBean());
        registrationBean.setName("PrimeFaces FileUpload Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD, DispatcherType.REQUEST);
        return registrationBean;
    }
}
