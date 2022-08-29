package com.zerobase.api.processor.okhttp3;

import com.zerobase.api.config.WifiApiConfig;
import com.zerobase.api.config.WifiApiInfo;
import com.zerobase.api.dto.WifiApiRequest;
import okhttp3.Request;

import java.io.IOException;
import java.net.URL;

public class OkHttp3RequestMaker {

    private final WifiApiInfo info;

    public OkHttp3RequestMaker() {
        this.info = WifiApiConfig.getInstance().getInfo();
    }

    public Request createRequest(WifiApiRequest apiRequest) throws IOException {
        requestCountValidate(apiRequest);
        return createRequestInternal(createURL(apiRequest));
    }

    private Request createRequestInternal(URL url) {
        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }

    private URL createURL(WifiApiRequest apiRequest) throws IOException {

        String url = info.getDomain() +
                "/" + info.getAuthenticationKey() +
                "/" + apiRequest.getRequestFileType() +
                "/" + info.getServiceName() +
                "/" + apiRequest.getStart() +
                "/" + apiRequest.getEnd();
        return new URL(url);
    }

    private void requestCountValidate(WifiApiRequest apiRequest) {
        if(apiRequest.getEnd() - apiRequest.getStart() > info.getMaxRequestCount()) {
            throw new IllegalArgumentException("최대 요청 데이터 건수가 넘었습니다.");
        }
    }
}
