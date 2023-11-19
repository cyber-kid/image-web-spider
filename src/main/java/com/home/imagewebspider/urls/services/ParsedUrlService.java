package com.home.imagewebspider.urls.services;

import com.home.imagewebspider.urls.models.ParsedUrlModel;
import com.home.imagewebspider.urls.repositories.ParsedUrlRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParsedUrlService {
    private final ParsedUrlRepository parsedUrlRepository;

    public void saveParsedUrl(ParsedUrlModel parsedUrl) {
        parsedUrlRepository.save(parsedUrl);
    }

    public void addImageSourcesToParsedUrl(String url, Set<String> imageSources) {
        Optional<ParsedUrlModel> modelOptional = parsedUrlRepository.findById(url);

        ParsedUrlModel parsedUrl;
        if (modelOptional.isPresent()) {
            parsedUrl = modelOptional.get();
            log.info("An entity found: " + parsedUrl);
            parsedUrl.getImageSources().addAll(imageSources);
        } else {
            log.info("No record found. Creating one.");
            parsedUrl = ParsedUrlModel.builder()
                    .imageSources(imageSources)
                    .url(url)
                    .build();
        }

        parsedUrlRepository.save(parsedUrl);
    }

    public boolean checkIfImageSourceWasParsed(String url, String imageSource) {
        Optional<ParsedUrlModel> modelOptional = parsedUrlRepository.findById(url);

        if (modelOptional.isPresent()) {
            ParsedUrlModel parsedUrl = modelOptional.get();
            log.info("An entity found: " + parsedUrl);
            return parsedUrl.getImageSources().contains(imageSource);
        }

        log.info("No record found");
        return false;
    }
}
