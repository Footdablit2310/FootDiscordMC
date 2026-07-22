package com.footdablit2310.footdiscordmc;

import javax.net.ssl.*;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public final class FootDiscordSSLUtil {

    private FootDiscordSSLUtil() {}

    /**
     * Creates an HttpClient that accepts all SSL certificates.
     * Use only for development or controlled environments.
     */
    public static HttpClient createUnsafeClient() {
        try {
            TrustManager[] trustAll = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAll, new java.security.SecureRandom());

            return HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create SSL client", e);
        }
    }

    /**
     * Creates a secure HttpClient using the system default trust store.
     * Use this in production.
     */
    public static HttpClient createSecureClient() {
        return HttpClient.newBuilder()
                .sslContext(getDefaultSSLContext())
                .build();
    }

    private static SSLContext getDefaultSSLContext() {
        try {
            return SSLContext.getDefault();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No default SSLContext available", e);
        }
    }
}
