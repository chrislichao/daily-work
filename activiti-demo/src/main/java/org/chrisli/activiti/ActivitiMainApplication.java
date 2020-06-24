package org.chrisli.activiti;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.chrisli.activiti.dao")
public class ActivitiMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiMainApplication.class, args);
    }
}