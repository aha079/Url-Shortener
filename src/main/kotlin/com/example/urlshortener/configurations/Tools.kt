package com.example.urlshortener.configurations

import com.example.urlshortener.repository.entity.ShortURL
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.Index
import java.util.regex.Pattern


@Configuration
class Tools {
    companion object {
        const val URL_REGEX = "^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$"

    }

    @Bean
    fun createIndex(mongoTemplate: MongoTemplate , @Value("\${custom.expire}") expire:Long): String =
        mongoTemplate.indexOps(ShortURL.COLLECTION_NAME).ensureIndex(
            Index().on(ShortURL.CREATE_DATE, Sort.Direction.ASC).named("deleteAt")
                .expire(expire)
        )

    @Bean
    fun createObjectMapper(): ObjectMapper = jacksonObjectMapper()

    @Bean
    fun createURLMatcher(): Pattern = Pattern.compile(URL_REGEX)


}