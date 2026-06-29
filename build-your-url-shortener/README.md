# url-shortener

Java practice project — building a URL shortening service from scratch, no third-party frameworks beyond the HTTP layer. Based on the [Coding Challenges](https://codingchallenges.fyi/challenges/challenge-url-shortener) series.

## What it does

Accepts a long URL, returns a short key. Redirects short key requests to the original URL. Supports deletion. Think bit.ly / tinyurl.com.

## API

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/` | Shorten a URL |
| `GET` | `/{key}` | Redirect to long URL (302) |
| `DELETE` | `/{key}` | Delete a shortened URL |

### POST /

**Request:**
```json
{ "url": "https://www.example.com" }
```

**Response `201 Created`:**
```json
{
  "key": "Y3S9wb",
  "long_url": "https://www.example.com",
  "short_url": "http://localhost:8080/Y3S9wb"
}
```

**Response `400 Bad Request`** — missing or invalid `url` field.

Idempotent: submitting the same URL twice returns the existing short URL.

### GET /{key}

**Response `302 Found`** — `Location` header set to the long URL.

**Response `404 Not Found`** — key does not exist.

### DELETE /{key}

**Response `200 OK`** — deleted (or never existed, no-op).

## Steps

| Step | Goal |
|------|------|
| 1 | `POST /` — shorten a URL, store in memory |
| 2 | `GET /{key}` — redirect with 302 |
| 3 | `DELETE /{key}` — remove a shortened URL |
| 4 (optional) | GUI — web form to create/copy short URLs |
| 4 (optional) | CI/CD — GitHub Actions pipeline |

## Design notes

- **Storage:** `HashMap<String, String>` (key → long URL) for now. Swap for Redis or a DB later.
- **Key generation:** Base62 truncated hash of the URL (6 chars). Collision rehashes with a salt.
- **Port:** `8080` by default.

## Requirements

- Java 21+
- No external dependencies for the core service (use `com.sun.net.httpserver` or Spring Boot — your choice)

## Usage

```powershell
# Compile and run (example with Spring Boot)
./mvnw spring-boot:run

# Shorten a URL
curl -X POST http://localhost:8080/ `
  -H "Content-Type: application/json" `
  -d '{"url": "https://www.example.com"}'

# Redirect
curl http://localhost:8080/{key} -i

# Delete
curl -X DELETE http://localhost:8080/{key} -i
```

## License

MIT
