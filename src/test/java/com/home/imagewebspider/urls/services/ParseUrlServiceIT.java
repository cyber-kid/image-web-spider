package com.home.imagewebspider.urls.services;

import com.home.imagewebspider.urls.models.ParsedUrlModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import redis.embedded.RedisServer;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ParseUrlServiceIT {
    private RedisServer redisServer;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Autowired
    private ParsedUrlService parsedUrlService;

    @BeforeEach
    public void init() {
        this.redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @AfterEach
    public void destroy() {
        redisServer.stop();
    }

    @Test
    public void saveParsedUrlTest() {
        ParsedUrlModel parsedUrlModel = ParsedUrlModel.builder()
                .url("test")
                .imageSources(Collections.singleton("image1"))
                .build();

        parsedUrlService.saveParsedUrl(parsedUrlModel);

        boolean result = parsedUrlService.checkIfImageSourceWasParsed("test", "image1");

        assertTrue(result);
    }

    @Test
    public void addImageSourcesToParsedUrlTest() {
        ParsedUrlModel parsedUrlModel = ParsedUrlModel.builder()
                .url("test")
                .imageSources(Collections.singleton("image1"))
                .build();

        parsedUrlService.saveParsedUrl(parsedUrlModel);

        parsedUrlService.addImageSourcesToParsedUrl("test", Collections.singleton("image2"));

        boolean result = parsedUrlService.checkIfImageSourceWasParsed("test", "image2");

        assertTrue(result);
    }
}
