package com.home.imagewebspider.downloads.services;

import com.home.imagewebspider.downloads.models.ImageDownloadTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageDownloadTaskService {
    private final KafkaTemplate<String, ImageDownloadTask> kafkaTemplate;
    @Value("${image-download-tasks.topic.name}")
    private String imageDownloadsTopicName;

    public CompletableFuture<SendResult<String, ImageDownloadTask>> sendTaskForExecution(ImageDownloadTask task) {
        log.info("Sending a message to the broker");
        CompletableFuture<SendResult<String, ImageDownloadTask>> result = kafkaTemplate.send(imageDownloadsTopicName, task);

        return result;
    }
}
