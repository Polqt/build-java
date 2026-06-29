package com.prac.urlshortener.model;

/**
 * Response body for POST /
 *
 * @param key      the generated short key (e.g. {@code Y3S9wb})
 * @param longUrl  the original long URL
 * @param shortUrl the full shortened URL (e.g. {@code http://localhost:8080/Y3S9wb})
 */
public record ShortenResponse(String key, String longUrl, String shortUrl) {}
