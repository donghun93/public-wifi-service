package com.zerobase.publicwifiservice.repository.jdbc;

import com.zerobase.publicwifiservice.config.ConnectionInfo;
import com.zerobase.publicwifiservice.config.JdbcConfig;
import com.zerobase.publicwifiservice.domain.History;
import com.zerobase.publicwifiservice.domain.Wifi;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.ZeroBaseJdbcTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZeroBaseJdbcTemplateTest {

    ZeroBaseJdbcTemplate zeroBaseJdbcTemplate;

    @BeforeEach
    void beforeEach() {
        ConnectionInfo connectionInfo = ConnectionInfo.builder()
                .batchSize(1000)
                .driverName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/wifi_db")
                .user("wifi")
                .pass("wifi1234")
                .build();

        JdbcConfig.getInstance().init(connectionInfo);
        zeroBaseJdbcTemplate = new ZeroBaseJdbcTemplate();
    }


    @DisplayName("와이파이 조회 테스트")
    @Test
    void selectTest() {
        List<Wifi> wifiList = zeroBaseJdbcTemplate.executeSelectQuery("select * from wifi limit 20", (rs, rowNum) -> Wifi.builder()
                .id(rs.getLong("wifi_id"))
                .mgrNo(rs.getString("mgr_no"))
                .wrdoFc(rs.getString("wrdofc"))
                .mainName(rs.getString("main_name"))
                .address1(rs.getString("address1"))
                .address2(rs.getString("address2"))
                .instlFloor(rs.getString("instl_floor"))
                .instlTy(rs.getString("instl_ty"))
                .instlMby(rs.getString("instl_mby"))
                .svcSE(rs.getString("svc_se"))
                .cmcwr(rs.getString("cmcwr"))
                .cnstcYear(rs.getString("cnstc_year"))
                .inoutDoor(rs.getString("inout_door"))
                .remars3(rs.getString("remars3"))
                .lat(rs.getDouble("lat"))
                .lnt(rs.getDouble("lnt"))
                .workDateTime(rs.getTimestamp("work_datetime").toString())
                .build());

        assertEquals(20, wifiList.size());
    }
    
    @DisplayName("히스토리 조회 테스트")
    @Test
    void historySelectTest() {
        // given
        List<History> histories = zeroBaseJdbcTemplate.executeSelectQuery("select * from history", (rs, rowNum) -> History.builder()
                .id(rs.getLong("history_id"))
                .xLocation(rs.getDouble("x_location"))
                .yLocation(rs.getDouble("y_location"))
                .searchDate(rs.getTimestamp("search_date").toLocalDateTime())
                .build());

        // when
        
        // then
        System.out.println();
    }
}