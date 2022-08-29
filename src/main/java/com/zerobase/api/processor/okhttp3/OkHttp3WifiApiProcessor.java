package com.zerobase.api.processor.okhttp3;

import com.zerobase.api.dto.WifiApiRequest;
import com.zerobase.api.processor.WifiApiProcessor;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class OkHttp3WifiApiProcessor implements WifiApiProcessor {

    private final OkHttp3RequestMaker requestMaker = new OkHttp3RequestMaker();
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public String getWifiApiMessageBody(WifiApiRequest apiRequest) {
        try {
            Request request = requestMaker.createRequest(apiRequest);
            try (Response response = client.newCall(request).execute()) {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
