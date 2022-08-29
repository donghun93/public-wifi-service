package com.zerobase.api;

import com.zerobase.api.dto.WifiApiRequest;
import com.zerobase.api.dto.WifiApiResponse;

public interface WifiApiService {

    WifiApiResponse getWifi(WifiApiRequest apiRequest);
}
