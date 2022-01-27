package com.example.urlshortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlshortenerApplication

fun main(args: Array<String>) {
	runApplication<UrlshortenerApplication>(*args)
}
