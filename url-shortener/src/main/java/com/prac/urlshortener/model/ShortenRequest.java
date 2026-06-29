package com.prac.urlshortener.model;

/**
 * Request body for POST /
 *
 * <p>Jackson deserializes the incoming JSON into this record.
 * The {@code url} field is the long URL to shorten.
 */
public record ShortenRequest(String url) {}
