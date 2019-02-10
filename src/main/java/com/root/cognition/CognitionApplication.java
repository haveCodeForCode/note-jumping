package com.root.cognition;

import com.root.cognition.until.ThreadsTools;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.ExecutorService;

/**
 * 启动项
 * @author LineInkBook
 */
@EnableTransactionManagement
@MapperScan("com.root.cognition.*.dao")
@SpringBootApplication
@EnableCaching

public class CognitionApplication {
    public static void main(String[] args) {
        SpringApplication.run(CognitionApplication.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    应用启动成功      ヾ(◍°∇°◍)ﾉﾞ  \n" +
                " ________        ________    ___________       _________         \n" +
                "|_   ___  \\     |   _____|  |____   ____|    /   _______|        \n" +
                "  | |__ )  |    |  |_____       |   |      /    /                \n" +
                "  |  __' //.    |   _____|      |   |      |   |    ___         \n" +
                " _| |   \\ |_    |  |_____       |   |      \\  \\ ___ |  |        \n" +
                "|___|   |__ |   |________|      |___|       \\__________|         "  );
    }
}

