package org.chrisli.export;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * [管理员项目启动类]
 *
 * @author Chris li[黎超]
 * @create [2017-04-12]
 */
@SpringBootApplication
@ComponentScan(basePackages = "org.chrisli.export")
@MapperScan("org.chrisli.*.mapper")
public class MainApplication {
    /**
     * [SpringBoot启动的方法]
     *
     * @author Chris li[黎超]
     * @create [2017-04-12]
     */
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}