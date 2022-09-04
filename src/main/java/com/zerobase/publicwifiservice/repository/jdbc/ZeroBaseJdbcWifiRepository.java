package com.zerobase.publicwifiservice.repository.jdbc;

import com.zerobase.publicwifiservice.domain.Wifi;
import com.zerobase.publicwifiservice.domain.WifiNearInfo;
import com.zerobase.publicwifiservice.repository.WifiRepository;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.JdbcSaveStrategy;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.RowMapper;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.ZeroBaseJdbcTemplate;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
public class ZeroBaseJdbcWifiRepository implements WifiRepository {

    private final ZeroBaseJdbcTemplate zeroBaseJdbcTemplate;

    @Override
    public List<Wifi> findNearWifiInfo(WifiNearInfo wifiNearInfo) {
        String selectNearWifiQuery = "select *" +
                ", format((6371 * acos(cos(radians(" + wifiNearInfo.getLat() + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + wifiNearInfo.getLnt() + ")) " +
                "+ sin(radians(" + wifiNearInfo.getLat() + ")) * sin(radians(lat)))), 4) as distance " +
                " from wifi " +
                " order by distance, wifi_id " +
                " limit " + wifiNearInfo.getLimit();

        return zeroBaseJdbcTemplate.executeSelectQuery(selectNearWifiQuery, wifiRowMapper());
    }

    @Override
    public int saveAll(List<Wifi> wifiList) {
        String insertQuery = "insert into wifi (" +
                " mgr_no," +
                " wrdofc," +
                " main_name," +
                " address1," +
                " address2," +
                " instl_floor," +
                " instl_ty," +
                " instl_mby," +
                " svc_se," +
                " cmcwr," +
                " cnstc_year," +
                " inout_door," +
                " remars3," +
                " lat," +
                " lnt," +
                " work_datetime) " +
                " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        return zeroBaseJdbcTemplate.executeBatchSaveQuery(insertQuery, getBatchWifiStrategy(), wifiList);
    }

    @Override
    public int deleteAll() {
        return zeroBaseJdbcTemplate.executeDeleteQuery("delete from wifi");
    }

    private RowMapper<Wifi> wifiRowMapper() {
        return (rs, rowNum1) -> Wifi.builder()
                .id(rs.getLong("wifi_id"))
                .distance(rs.getString("distance"))
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
                .build();
    }

    private static JdbcSaveStrategy getBatchWifiStrategy() {
        return new JdbcSaveStrategy() {
            @Override
            public <T> void setPreparedStatement(PreparedStatement pstmt, T item) throws SQLException {
                Wifi wifi = (Wifi) item;
                pstmt.setString(1, wifi.getMgrNo());
                pstmt.setString(2, wifi.getWrdoFc());
                pstmt.setString(3, wifi.getMainName());
                pstmt.setString(4, wifi.getAddress1());
                pstmt.setString(5, wifi.getAddress2());
                pstmt.setString(6, wifi.getInstlFloor());
                pstmt.setString(7, wifi.getInstlTy());
                pstmt.setString(8, wifi.getInstlMby());
                pstmt.setString(9, wifi.getSvcSE());
                pstmt.setString(10, wifi.getCmcwr());
                pstmt.setString(11, wifi.getCnstcYear());
                pstmt.setString(12, wifi.getInoutDoor());
                pstmt.setString(13, wifi.getRemars3());
                pstmt.setDouble(14, wifi.getLat());
                pstmt.setDouble(15, wifi.getLnt());
                pstmt.setTimestamp(16, Timestamp.valueOf(wifi.getWorkDateTime()));
            }
        };
    }
}
