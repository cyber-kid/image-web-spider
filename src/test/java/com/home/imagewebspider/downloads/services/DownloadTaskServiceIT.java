package com.home.imagewebspider.downloads.services;

import com.home.imagewebspider.downloads.models.ImageDownloadTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class DownloadTaskServiceIT {
    @Autowired
    private ImageDownloadTaskService downloadTaskService;
    @Autowired
    private EmbeddedKafkaBroker broker;

    @Test
    public void sendMessageTest() throws ExecutionException, InterruptedException {
        ImageDownloadTask task = new ImageDownloadTask("id");

        CompletableFuture<SendResult<String, ImageDownloadTask>> result = downloadTaskService.sendTaskForExecution(task);

        // wait for the message to be sent
        result.get();

        assertTrue(result.isDone());
    }
}
