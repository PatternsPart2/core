package com.galinazabelina.core;

import com.galinazabelina.core.core.rabbit.RabbitConfiguration;
import com.galinazabelina.core.model.entity.Account;
import com.galinazabelina.core.model.entity.EntityConfig;
import com.galinazabelina.core.model.entity.Operation;
import com.galinazabelina.core.model.entity.User;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EntityScan(basePackageClasses = {Account.class, Operation.class, User.class})
@Import({EntityConfig.class, RabbitConfiguration.class})
public class Application {
    public static void main(final String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
