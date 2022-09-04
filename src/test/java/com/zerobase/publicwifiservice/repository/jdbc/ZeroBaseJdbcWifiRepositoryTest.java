package com.zerobase.publicwifiservice.repository.jdbc;

import com.zerobase.publicwifiservice.config.ConnectionInfo;
import com.zerobase.publicwifiservice.config.JdbcConfig;
import com.zerobase.publicwifiservice.domain.Wifi;
import com.zerobase.publicwifiservice.domain.WifiNearInfo;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.ZeroBaseJdbcTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ZeroBaseJdbcWifiRepositoryTest {

    ZeroBaseJdbcWifiRepository zeroBaseJdbcWifiRepository;

    @BeforeEach
    void before() {
        ConnectionInfo connectionInfo = ConnectionInfo.builder()
                .batchSize(1000)
                .driverName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/wifi_db")
                .user("wifi")
                .pass("wifi1234")
                .build();
        JdbcConfig.getInstance().init(connectionInfo);

        zeroBaseJdbcWifiRepository = new ZeroBaseJdbcWifiRepository(new ZeroBaseJdbcTemplate());
        zeroBaseJdbcWifiRepository.deleteAll();
    }
    
    @DisplayName("근처 와이파이 조회 테스트")
    @Test
    @Disabled
    void wifiNearSelectTest() {
        // given
        WifiNearInfo wifiNearInfo = WifiNearInfo.builder()
                .limit(20)
                .lat(37.502982)
                .lnt(127.041564)
                .build();

        // when
        List<Wifi> nearWifiInfo = zeroBaseJdbcWifiRepository.findNearWifiInfo(wifiNearInfo);

        // then
        //assertEquals(20, nearWifiInfo.size());
    }
    
    @DisplayName("와이파이 전체 삭제 테스트")
    @Test
    void wifiDeleteAllTest() {
        // given
        mockSave();

        // when
        int deleteCount = zeroBaseJdbcWifiRepository.deleteAll();

        // then
        assertEquals(1, deleteCount);
    }
    
    @DisplayName("와이파이 저장 테스트")
    @Test
    void wifiSaveTest() {
        // given
        List<Wifi> list = new ArrayList<>();
        list.add(Wifi.builder()
                .mgrNo("ARI00001")
                .wrdoFc("서대문구")
                .mainName("상수도사업본부")
                .address1("서소문로 51")
                .address2("본관 1F")
                .instlFloor("")
                .instlTy("7-1.커뮤니티-행정")
                .instlMby("서울시(AP)")
                .svcSE("공공WIFI")
                .cmcwr("수도사업소자가망")
                .cnstcYear("2014")
                .inoutDoor("실내")
                .remars3("")
                .lat(126.96675)
                .lnt(37.561924)
                .workDateTime("2022-09-04 10:58:00.0")
                .build());

        // when
        int savedCount = zeroBaseJdbcWifiRepository.saveAll(list);

        // then
        assertEquals(savedCount, 1);
    }

    private void mockSave() {
        List<Wifi> list = new ArrayList<>();
        list.add(Wifi.builder()
                        .mgrNo("ARI00001")
                        .wrdoFc("서대문구")
                        .mainName("상수도사업본부")
                        .address1("서소문로 51")
                        .address2("본관 1F")
                        .instlFloor("")
                        .instlTy("7-1.커뮤니티-행정")
                        .instlMby("서울시(AP)")
                        .svcSE("공공WIFI")
                        .cmcwr("수도사업소자가망")
                        .cnstcYear("2014")
                        .inoutDoor("실내")
                        .remars3("")
                        .lat(126.96675)
                        .lnt(37.561924)
                        .workDateTime("2022-09-04 10:58:00.0")
                .build());

        zeroBaseJdbcWifiRepository.saveAll(list);
    }
}