package com.tpe;

//Java tabanlı web uygulamaları web.xml ile configure edilir.
//bu classı web.xml yerine kullanacağız

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//abstractAnn... classının metodlarını override ederek DispatcherServlet i configure edip
//başlatabiliriz.
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    advance bilgi detay;
    dispatcher:
        Servlet WebAppContext --> controller - handlermapping- viewresolver
        Root WebAppContext --> dataya erişim: repos - services
     */

    @Override
    protected Class<?>[] getRootConfigClasses() { // dataya erişim: hibernate-jdbc
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { // controller - handlermapping- viewresolver(MVC)
        return new Class[]{
           WebMvcConfig.class
        };
    }

    @Override //hangi url ile gelen requestler(istekler) servlet tarafından karşılanacak.
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }
}
