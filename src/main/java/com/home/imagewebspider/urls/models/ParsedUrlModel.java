package com.home.imagewebspider.urls.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Set;

@Data
@RedisHash
@Builder
public class ParsedUrlModel {
    @Id
    private String url;
    private Set<String> imageSources;
}
