package com.footdablit2310.footdiscordmc;

public final class FootDiscordPOST {

    private FootDiscordPOST() {}

    public static String sendDiscordPayload(String endpoint, String jsonPayload) {
        try {
            return FootDiscordHTTPUtil.post(endpoint, jsonPayload);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending payload: " + e.getMessage();
        }
    }
}
