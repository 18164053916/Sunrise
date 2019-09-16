package cn.com.sunrise;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.com.sunrise.dao")
public class SunriseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunriseApplication.class, args);
    }

}
