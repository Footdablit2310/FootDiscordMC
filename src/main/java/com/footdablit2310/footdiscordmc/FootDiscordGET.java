package com.footdablit2310.footdiscordmc;

import java.net.http.HttpTimeoutException;

public final class FootDiscordGET {

    private FootDiscordGET() {}

    public static String fetchDiscordData(String endpoint) throws HttpTimeoutException {
        try {
            return FootDiscordHTTPUtil.get(endpoint);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching data: " + e.getMessage();
        }
    }
}
