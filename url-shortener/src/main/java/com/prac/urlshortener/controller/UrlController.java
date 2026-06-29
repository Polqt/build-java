package com.prac.urlshortener.controller;

import com.prac.urlshortener.model.ShortenRequest;
import com.prac.urlshortener.model.ShortenResponse;
import com.prac.urlshortener.service.UrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing the URL shortener API.
 *
 * <pre>{@code
 * POST   /        — shorten a URL
 * GET    /{key}   — redirect to long URL
 * DELETE /{key}   — delete a shortened URL
 * }</pre>
 */
@RestController
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    /**
     * Shortens a URL.
     *
     * <p>TODO: Step 1
     * <ol>
     *   <li>Validate request: if {@code body} is null or {@code body.url()} is blank,
     *       return {@code 400 Bad Request} with message {@code "Missing field: url"}.</li>
     *   <li>Call {@code service.shorten(body.url())}.</li>
     *   <li>Return {@code 201 Created} with the {@link ShortenResponse} as JSON body.</li>
     * </ol>
     *
     * @param body the request body containing the long URL
     * @return {@code 201} with {@link ShortenResponse}, or {@code 400} on bad input
     */
    @PostMapping("/")
    public ResponseEntity<?> shorten(@RequestBody(required = false) ShortenRequest body) {
        // TODO: Step 1 - validate body, call service.shorten(), return 201
        return ResponseEntity.ok().build();
    }

    /**
     * Redirects a short key to its long URL.
     *
     * <p>TODO: Step 2
     * <ol>
     *   <li>Call {@code service.resolve(key)}.</li>
     *   <li>If present: return {@code 302 Found} with {@code Location} header set to the long URL.</li>
     *   <li>If empty: return {@code 404 Not Found} with body {@code "URL not found"}.</li>
     * </ol>
     *
     * @param key the short key
     * @return {@code 302} redirect, or {@code 404} if not found
     */
    @GetMapping("/{key}")
    public ResponseEntity<?> redirect(@PathVariable String key) {
        // TODO: Step 2 - resolve key, return 302 or 404
        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes a shortened URL.
     *
     * <p>TODO: Step 3
     * <ol>
     *   <li>Call {@code service.delete(key)}.</li>
     *   <li>Return {@code 200 OK} regardless of whether the key existed.</li>
     * </ol>
     *
     * @param key the short key to delete
     * @return {@code 200 OK}
     */
    @DeleteMapping("/{key}")
    public ResponseEntity<Void> delete(@PathVariable String key) {
        // TODO: Step 3 - service.delete(key), return 200
        return ResponseEntity.ok().build();
    }
}
