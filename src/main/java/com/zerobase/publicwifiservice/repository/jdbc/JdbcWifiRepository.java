package com.zerobase.publicwifiservice.repository.jdbc;

import com.zerobase.publicwifiservice.config.ConnectionInfo;
import com.zerobase.publicwifiservice.domain.Wifi;
import com.zerobase.publicwifiservice.domain.WifiNearInfo;
import com.zerobase.publicwifiservice.repository.WifiRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcWifiRepository extends JdbcBase implements WifiRepository {

    @Override
    public List<Wifi> findNearWifiInfo(WifiNearInfo wifiNearInfo) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = getFindNearWifiInfo(wifiNearInfo);
        List<Wifi> wifiList = new ArrayList<>();

        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                wifiList.add(createWifi(rs));
            }
            return wifiList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResultSet(rs);
            closeStatement(stmt);
            closeConnection(con);
        }
    }

    private String getFindNearWifiInfo(WifiNearInfo wifiNearInfo) {
        return "select *" +
                ", format((6371 * acos(cos(radians(" + wifiNearInfo.getLat() + ")) * cos(radians(lat)) * cos(radians(lnt) - radians(" + wifiNearInfo.getLnt() + ")) " +
                "+ sin(radians(" + wifiNearInfo.getLat() + ")) * sin(radians(lat)))), 4) as distance " +
                " from wifi " +
                " order by distance, wifi_id " +
                " limit " + wifiNearInfo.getLimit();
    }
    private Wifi createWifi(ResultSet rs) throws SQLException {
        return Wifi.builder()
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

    @Override
    public void saveAll(List<Wifi> wifiList) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            pstmt = conn.prepareStatement(getWifiInsertQuery());
            int count = 0;
            for (Wifi wifi : wifiList) {
                setWifiInsertQueryData(pstmt, wifi);
                pstmt.addBatch();

                count++;
                if (count % info.getBatchSize() == 0) {
                    pstmt.executeBatch();
                }
            }

            if (count > 0) {
                pstmt.executeBatch();
            }

            conn.commit(); // 트랜잭션 종료
        } catch (SQLException e) {
            rollBack(conn);
            throw new RuntimeException(e);
        } finally {
            closeStatement(pstmt);
            closeConnection(conn);
        }
    }

    private String getWifiInsertQuery() {
        return "insert into wifi (" +
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
    }

    private void setWifiInsertQueryData(PreparedStatement pstmt, Wifi wifi) throws SQLException {
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

    @Override
    public void deleteAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String deleteQuery = getDeleteAllQuery();

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(deleteQuery);
            int resultCnt = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollBack(conn);
            throw new RuntimeException(e);
        } finally {
            closeStatement(pstmt);
            closeConnection(conn);
        }
    }

    private String getDeleteAllQuery() {
        return "delete from wifi";
    }
}
