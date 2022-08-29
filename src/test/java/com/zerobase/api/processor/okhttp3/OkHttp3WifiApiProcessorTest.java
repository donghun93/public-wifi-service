package com.zerobase.api.processor.okhttp3;

import com.zerobase.api.config.WifiApiConfig;
import com.zerobase.api.config.WifiApiInfo;
import com.zerobase.api.dto.WifiApiRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.zerobase.api.dto.WifiLoadRequestFileType.JSON;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OkHttp3WifiApiProcessorTest {

    OkHttp3WifiApiProcessor wifiApiProcessor;

    @BeforeEach
    void before() {
        WifiApiInfo wifiApiInfo = WifiApiInfo.builder()
                .domain("http://openapi.seoul.go.kr:8088")
                .authenticationKey("7872494948616c7336335849706877")
                .serviceName("TbPublicWifiInfo")
                .maxRequestCount(1000)
                .build();
        WifiApiConfig.getInstance().init(wifiApiInfo);


        OkHttp3RequestMaker requestMaker = new OkHttp3RequestMaker();
        wifiApiProcessor = new OkHttp3WifiApiProcessor(requestMaker);
    }

    @DisplayName("okhttp3 요청 테스트")
    @Test
    void requestTest() {
        // given
        WifiApiRequest wifiApiRequest = WifiApiRequest.builder()
                .start(0)
                .end(1)
                .requestFileType(JSON)
                .build();

        // when
        String messageBody = wifiApiProcessor.getWifiApiMessageBody(wifiApiRequest);

        // then
        assertNotNull(messageBody);
    }
}