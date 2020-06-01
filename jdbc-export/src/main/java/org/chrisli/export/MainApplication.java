package org.chrisli.export;

import org.chrisli.log4jdbc.annotation.EnableLog4jdbc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * [管理员项目启动类]
 *
 * @author Chris li[黎超]
 * @create [2017-04-12]
 */
@MapperScan("org.chrisli.*.mapper")
@SpringBootApplication(scanBasePackageClasses = MainApplication.class, exclude = {DataSourceAutoConfiguration.class})
@EnableLog4jdbc
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