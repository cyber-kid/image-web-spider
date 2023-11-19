package com.home.imagewebspider.urls.repositories;

import com.home.imagewebspider.urls.models.ParsedUrlModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParsedUrlRepository extends CrudRepository<ParsedUrlModel, String> {
    Optional<ParsedUrlModel> findByUrl(String url);
}
