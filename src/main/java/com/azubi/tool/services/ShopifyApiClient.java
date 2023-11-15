package com.azubi.tool.services;

import okhttp3.*;

import java.io.IOException;

public class ShopifyApiClient {
    private static final String SHOPIFY_API_URL = "https://make-it-yours-1918.myshopify.com/admin/api/2023-04";
    private static final String API_KEY = "360c16bb9fc08d9ca14fca6482ca7657";
    private static final String API_PASSWORD = "82563cc61eb0bc70e2030280bdb640ae";

    private final OkHttpClient httpClient;

    public ShopifyApiClient() {
        this.httpClient = new OkHttpClient();
    }

    public String getCustomerData(String customerId) throws IOException {
        String url = SHOPIFY_API_URL + "/customers/" + customerId + ".json";
        Request request = new Request.Builder()
                .url(url)
                .header(API_KEY, API_PASSWORD)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }
            return response.body().string();
        }
    }
}