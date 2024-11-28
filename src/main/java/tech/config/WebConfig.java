/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tech.config;

/**
 * WebConfig - Mô tả lớp (hoặc giao diện).
 * <p>
 * Tác giả: Thanh Duoc</br>
 * Ngày sinh: 25/10/2003</br>
 * Ngày tạo: Nov 23, 2024
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import javax.validation.Validator;

@Configuration
public class WebConfig {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}

