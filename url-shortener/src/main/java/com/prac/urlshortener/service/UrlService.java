package com.prac.urlshortener.service;

import com.prac.urlshortener.model.ShortenResponse;
import com.prac.urlshortener.repository.UrlStore;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Business logic for URL shortening, resolving, and deletion.
 *
 * <p>Key generation: take first 6 Base62 characters of the SHA-256 hash of the URL.
 * On collision (same key, different URL), append an incrementing salt and rehash.
 */
@Service
public class UrlService {

    private static final int KEY_LENGTH = 6;
    private static final String BASE_URL = "http://localhost:8080/";

    private final UrlStore store;

    public UrlService(UrlStore store) {
        this.store = store;
    }

    /**
     * Shortens a URL. Idempotent: submitting the same URL twice returns the same key.
     *
     * <p>TODO: Step 1
     * <ol>
     *   <li>Call {@code store.findKeyByUrl(url)} — if present, build and return existing {@link ShortenResponse}.</li>
     *   <li>Generate a 6-char Base62 key: SHA-256 hash the URL bytes, Base64-encode,
     *       keep only alphanumeric chars, take first {@value #KEY_LENGTH}.</li>
     *   <li>Handle collision: if key already exists in store for a different URL,
     *       append a salt (e.g. {@code url + salt++}) and rehash until key is free.</li>
     *   <li>Call {@code store.save(key, url)} and return a new {@link ShortenResponse}.</li>
     * </ol>
     *
     * @param url the long URL to shorten
     * @return a {@link ShortenResponse} containing key, longUrl, and shortUrl
     */
    public ShortenResponse shorten(String url) {
        // TODO: Step 1 - See Javadoc above
        return null;
    }

    /**
     * Resolves a short key to its long URL.
     *
     * <p>TODO: Step 2 - Delegate to {@code store.findUrlByKey(key)}.
     *
     * @param key the short key
     * @return an {@link Optional} containing the long URL, or empty if not found
     */
    public Optional<String> resolve(String key) {
        // TODO: Step 2 - return store.findUrlByKey(key)
        return Optional.empty();
    }

    /**
     * Deletes a shortened URL. No-op if the key does not exist.
     *
     * <p>TODO: Step 3 - Delegate to {@code store.delete(key)}.
     *
     * @param key the short key to delete
     */
    public void delete(String key) {
        // TODO: Step 3 - store.delete(key)
    }
}
