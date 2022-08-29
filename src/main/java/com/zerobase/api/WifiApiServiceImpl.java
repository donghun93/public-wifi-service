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
        WifiApiResponse wifi = getWifi(WifiApiRequest.builder()
                .start(0)
                .end(1)
                .requestFileType(JSON)
                .build());
        return WifiApiPagingInfo.builder()
                .totalCount(wifi.getTotalCount())
                .queryCount(wifi.getTotalCount() / WifiApiConfig.getInstance().getInfo().getMaxRequestCount() + 1)
                .build();
    }

    @Override
    public WifiApiResponse getWifi(WifiApiRequest apiRequest) {
        String messageBody = apiProcessor.getWifiApiMessageBody(apiRequest);
        return messageParser.parsing(messageBody, apiRequest.getRequestFileType());
    }
}
