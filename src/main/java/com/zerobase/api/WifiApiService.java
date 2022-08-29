package com.zerobase.api;

import com.zerobase.api.dto.WifiApiRequest;
import com.zerobase.api.dto.WifiApiPagingInfo;
import com.zerobase.api.dto.WifiApiResponse;

public interface WifiApiService {

    WifiApiPagingInfo getWifiTotalCountAndQueryCount();
    WifiApiResponse getWifi(WifiApiRequest apiRequest);
}
