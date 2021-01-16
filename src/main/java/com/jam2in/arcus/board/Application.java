package com.jam2in.arcus.board;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.ArcusClientPool;
import net.spy.memcached.ConnectionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class Application {
    static ArcusClientPool arcusClient;
    static public boolean CACHE = false;

    public static void main(String[] args) {
        if (CACHE) {
            try {
                arcusClient = ArcusClient.createArcusClientPool("10.0.0.1:2181", "test", new ConnectionFactoryBuilder(), 8);
            } catch (IllegalStateException e) {
                arcusClient.shutdown();
                CACHE = false;
            }
            System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.SLF4JLogger");
        }
        SpringApplication.run(Application.class, args);
    }
}
