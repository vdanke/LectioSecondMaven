package org.step.lectio.maven.project.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectRequestListener implements ServletRequestListener {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletContext servletContext = servletRequestEvent.getServletContext();
        HttpServletRequest servletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();

        String contextPath = servletRequest.getContextPath();

        if (contextPath.equalsIgnoreCase("/main")) {
            atomicInteger.addAndGet(1);
        }
    }
}
