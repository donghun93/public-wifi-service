package com.zerobase.api;

import com.zerobase.api.dto.WifiApiRequest;
import com.zerobase.api.dto.WifiApiResponse;
import com.zerobase.api.parser.WifiApiMessageParser;
import com.zerobase.api.processor.WifiApiProcessor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WifiApiServiceImpl implements WifiApiService {

    private final WifiApiProcessor apiProcessor;
    private final WifiApiMessageParser messageParser;

    @Override
    public WifiApiResponse getWifi(WifiApiRequest apiRequest) {
        String messageBody = apiProcessor.getWifiApiMessageBody(apiRequest);
        return messageParser.parsing(messageBody, apiRequest.getRequestFileType());
    }
}
