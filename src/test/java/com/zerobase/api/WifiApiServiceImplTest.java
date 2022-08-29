package com.zerobase.api;

import com.zerobase.api.config.WifiApiConfig;
import com.zerobase.api.config.WifiApiInfo;
import com.zerobase.api.dto.WifiApiRequest;
import com.zerobase.api.dto.WifiApiResponse;
import com.zerobase.api.dto.WifiLoadRequestFileType;
import com.zerobase.api.parser.okhttp3.GsonWifiApiMessageParser;

import com.zerobase.api.processor.okhttp3.OkHttp3RequestMaker;
import com.zerobase.api.processor.okhttp3.OkHttp3WifiApiProcessor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class WifiApiServiceImplTest {

    WifiApiServiceImpl wifiApiService;

    @BeforeEach
    void before() {
        WifiApiInfo wifiApiInfo = WifiApiInfo.builder()
                .domain("http://openapi.seoul.go.kr:8088")
                .authenticationKey("7872494948616c7336335849706877")
                .serviceName("TbPublicWifiInfo")
                .maxRequestCount(1000)
                .build();
        WifiApiConfig.getInstance().init(wifiApiInfo);

        wifiApiService = new WifiApiServiceImpl(
                new OkHttp3WifiApiProcessor(new OkHttp3RequestMaker()),
                new GsonWifiApiMessageParser()
        );
    }

    @DisplayName("wifi 요청 테스트")
    @Test
    void getWifiRequestTest() {
        // given
        WifiApiRequest request = WifiApiRequest.builder()
                .start(0)
                .end(50)
                .requestFileType(WifiLoadRequestFileType.JSON)
                .build();
        // when
        WifiApiResponse wifi = wifiApiService.getWifi(request);

        // then
        assertNotNull(wifi);
    }
}