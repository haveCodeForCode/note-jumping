package com.root.cognition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动项
 * @author LineInkBook
 */
@MapperScan("com.root.cognition.*.dao")
@SpringBootApplication
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

