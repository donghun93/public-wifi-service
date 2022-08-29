package com.zerobase.api.processor;

import com.zerobase.api.dto.WifiApiRequest;


public interface WifiApiProcessor {

    String getWifiApiMessageBody(WifiApiRequest apiRequest);
}
