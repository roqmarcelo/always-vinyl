package io.github.alwaysvinyl.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Field;

import static java.util.Optional.ofNullable;

@Configuration
public class LoggerConfiguration {

    @Bean
    @Scope("prototype")
    public Logger logger(final InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(ofNullable(injectionPoint.getMethodParameter())
                .<Class>map(MethodParameter::getContainingClass)
                .orElseGet(() -> ofNullable(injectionPoint.getField())
                        .map(Field::getDeclaringClass)
                        .orElseThrow(IllegalArgumentException::new)));
    }
}