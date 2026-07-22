package com.footdablit2310.footdiscordmc;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class FootDiscordHTTPUtil {

    // Swap between secure and unsafe depending on environment
    private static HttpClient CLIENT;

    private FootDiscordHTTPUtil() {}

    /** Initialize the HTTP client once */
    public static void init(boolean unsafe) {
        CLIENT = unsafe ? FootDiscordSSLUtil.createUnsafeClient()
                : FootDiscordSSLUtil.createSecureClient();
        System.out.println("[FootDiscordMC] HTTP client initialized (" + (unsafe ? "UNSAFE" : "SECURE") + ")");
    }

    public static String get(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static String post(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
