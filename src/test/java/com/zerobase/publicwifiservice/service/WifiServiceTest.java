package com.zerobase.publicwifiservice.service;

import com.zerobase.api.WifiApiServiceImpl;
import com.zerobase.api.config.WifiApiConfig;
import com.zerobase.api.config.WifiApiInfo;
import com.zerobase.api.parser.okhttp3.GsonWifiApiMessageParser;
import com.zerobase.api.processor.okhttp3.OkHttp3RequestMaker;
import com.zerobase.api.processor.okhttp3.OkHttp3WifiApiProcessor;
import com.zerobase.publicwifiservice.config.ConnectionInfo;
import com.zerobase.publicwifiservice.dto.WifiNearDetailInfoResponse;
import com.zerobase.publicwifiservice.dto.WifiNearInfoRequest;
import com.zerobase.publicwifiservice.repository.jdbc.JdbcHistoryRepository;
import com.zerobase.publicwifiservice.repository.jdbc.JdbcWifiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WifiServiceTest {

    WifiService wifiService;


    @BeforeEach
    void before() {
        WifiApiInfo wifiApiInfo = WifiApiInfo.builder()
                .domain("http://openapi.seoul.go.kr:8088")
                .authenticationKey("7872494948616c7336335849706877")
                .serviceName("TbPublicWifiInfo")
                .maxRequestCount(1000)
                .build();
        WifiApiConfig.getInstance().init(wifiApiInfo);

        WifiApiServiceImpl wifiApiService = new WifiApiServiceImpl(
                new OkHttp3WifiApiProcessor(new OkHttp3RequestMaker()),
                new GsonWifiApiMessageParser()
        );

        ConnectionInfo connectionInfo = ConnectionInfo.builder()
                .batchSize(1000)
                .driverName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/wifi_db")
                .user("wifi")
                .pass("wifi1234")
                .build();
        
        wifiService = new WifiService(
                wifiApiService,
                new JdbcWifiRepository(connectionInfo),
                new JdbcHistoryRepository(connectionInfo)
        );
    }
    
    @DisplayName("wifi 페이징 로딩 후 저장 테스트")
    @Test
    void wifiLoadAndSaveTest() {
        wifiService.loadWifiAndSave();
    }
    
    @DisplayName("wifi 근처 데이터 조회 테스트")
    @Test
    void wifiFindNearTest() {
        // given
        WifiNearInfoRequest request = WifiNearInfoRequest.builder()
                .lat(37.502982)
                .lnt(127.041564)
                .build();

        // when
        List<WifiNearDetailInfoResponse> wifiSearchNearInfo = wifiService.getWifiSearchNearInfo(request);

        // then
        assertEquals(wifiSearchNearInfo.size(), 20);
    }
}