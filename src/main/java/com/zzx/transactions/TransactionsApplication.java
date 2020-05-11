package com.zzx.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"beforeBean.xml","containConfig.xml"})
public class TransactionsApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(TransactionsApplication.class, args);
        System.out.println(configurableApplicationContext.getBean("serviceComponent"));
    }

}
