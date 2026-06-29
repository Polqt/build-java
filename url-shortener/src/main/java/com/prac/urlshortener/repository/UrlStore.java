package com.prac.urlshortener.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory store for shortened URLs.
 *
 * <p>Two maps are kept in sync:
 * <ul>
 *   <li>{@code keyToUrl} — short key → long URL (used for redirects)</li>
 *   <li>{@code urlToKey} — long URL → short key (used for idempotency)</li>
 * </ul>
 */
@Repository
public class UrlStore {

    private final Map<String, String> keyToUrl = new ConcurrentHashMap<>();
    private final Map<String, String> urlToKey = new ConcurrentHashMap<>();

    /**
     * TODO: Step 1 - Store key → url and url → key in both maps.
     *
     * @param key the short key
     * @param url the long URL
     */
    public void save(String key, String url) {
        // TODO: Step 1 - put into keyToUrl and urlToKey
    }

    /**
     * TODO: Step 1 - Look up whether a long URL already has a short key (idempotency check).
     *
     * @param url the long URL
     * @return an {@link Optional} containing the existing key, or empty if not found
     */
    public Optional<String> findKeyByUrl(String url) {
        // TODO: Step 1 - return Optional of urlToKey.get(url)
        return Optional.empty();
    }

    /**
     * TODO: Step 2 - Look up the long URL for a given short key.
     *
     * @param key the short key
     * @return an {@link Optional} containing the long URL, or empty if not found
     */
    public Optional<String> findUrlByKey(String key) {
        // TODO: Step 2 - return Optional of keyToUrl.get(key)
        return Optional.empty();
    }

    /**
     * TODO: Step 3 - Remove a key and its reverse mapping. No-op if key does not exist.
     *
     * @param key the short key to delete
     */
    public void delete(String key) {
        // TODO: Step 3 - remove from keyToUrl; use the removed url to remove from urlToKey
    }
}
