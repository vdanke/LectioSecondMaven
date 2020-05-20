package org.step.lectio.maven.project;

public interface ApplicationContext<T> {

    T getBean(String name);
}
