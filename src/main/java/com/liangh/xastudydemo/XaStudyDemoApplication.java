package com.liangh.xastudydemo;

import bitronix.tm.BitronixTransactionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class XaStudyDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(XaStudyDemoApplication.class, args);

        //判断分布式事物管理器BitronixTransactionManager是否存在
        BitronixTransactionManager bitronixTransactionManager = context.getBean(BitronixTransactionManager.class);
        log.info("BitronixTransactionManager is : {}", bitronixTransactionManager);
        assert bitronixTransactionManager != null;


    }

}
