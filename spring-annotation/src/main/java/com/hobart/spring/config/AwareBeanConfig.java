package com.hobart.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hobart.spring.bean.Bank;

/**
 * Aware子接口的实现类注入Spring底层组件
 * @author hobart
 *
 */
@Configuration
@Import(value={Bank.class})
public class AwareBeanConfig {

}
