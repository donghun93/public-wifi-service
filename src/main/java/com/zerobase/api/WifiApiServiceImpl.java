package com.zerobase.api;

import com.zerobase.api.config.WifiApiConfig;
import com.zerobase.api.dto.WifiApiRequest;
import com.zerobase.api.dto.WifiApiPagingInfo;
import com.zerobase.api.dto.WifiApiResponse;
import com.zerobase.api.parser.WifiApiMessageParser;
import com.zerobase.api.processor.WifiApiProcessor;
import lombok.RequiredArgsConstructor;

import static com.zerobase.api.dto.WifiLoadRequestFileType.JSON;

@RequiredArgsConstructor
public class WifiApiServiceImpl implements WifiApiService {

    private final WifiApiProcessor apiProcessor;
    private final WifiApiMessageParser messageParser;

    @Override
    public WifiApiPagingInfo getWifiTotalCountAndQueryCount() {
        WifiApiResponse response = getWifi(WifiApiRequest.builder()
                .start(0)
                .end(1)
                .requestFileType(JSON)
                .build());

        int requestCount = getRequestCount(response);

        return WifiApiPagingInfo.builder()
                .totalCount(response.getTotalCount())
                .requestCount(requestCount)
                .build();
    }

    @Override
    public WifiApiResponse getWifi(WifiApiRequest apiRequest) {
        String messageBody = apiProcessor.getWifiApiMessageBody(apiRequest);
        return messageParser.parsing(messageBody, apiRequest.getRequestFileType());
    }

    private int getRequestCount(WifiApiResponse response) {
        int maxRequestCount = WifiApiConfig.getInstance().getInfo().getMaxRequestCount();
        int requestCount = response.getTotalCount() / maxRequestCount;
        if (requestCount % maxRequestCount > 0) {
            requestCount += 1;
        }
        return requestCount;
    }
}
