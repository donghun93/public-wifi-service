package com.zerobase.publicwifiservice.dto;

import com.zerobase.publicwifiservice.domain.Wifi;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WifiNearDetailInfoResponse {
    private Long id;
    private String km;
    private String mgrNo;
    private String wrdoFc;
    private String mainName;
    private String address1;
    private String address2;
    private String instlFloor;
    private String instlTy;
    private String instlMby;
    private String svcSE;
    private String cmcwr;
    private String cnstcYear;
    private String inoutDoor;
    private String remars3;
    private String lat;
    private String lnt;
    private String workDateTime;

    @Builder
    private WifiNearDetailInfoResponse(Long id, String km, String mgrNo, String wrdoFc, String mainName, String address1, String address2, String instlFloor, String instlTy, String instlMby, String svcSE, String cmcwr, String cnstcYear, String inoutDoor, String remars3, String lat, String lnt, String workDateTime) {
        this.id = id;
        this.km = km;
        this.mgrNo = mgrNo;
        this.wrdoFc = wrdoFc;
        this.mainName = mainName;
        this.address1 = address1;
        this.address2 = address2;
        this.instlFloor = instlFloor;
        this.instlTy = instlTy;
        this.instlMby = instlMby;
        this.svcSE = svcSE;
        this.cmcwr = cmcwr;
        this.cnstcYear = cnstcYear;
        this.inoutDoor = inoutDoor;
        this.remars3 = remars3;
        this.lat = lat;
        this.lnt = lnt;
        this.workDateTime = workDateTime;
    }

    public static WifiNearDetailInfoResponse toResponse(Wifi wifi) {
        return WifiNearDetailInfoResponse.builder()
                .id(wifi.getId())
                .km(wifi.getDistance())
                .mgrNo(wifi.getMgrNo())
                .wrdoFc(wifi.getWrdoFc())
                .mainName(wifi.getMainName())
                .address1(wifi.getAddress1())
                .address2(wifi.getAddress2())
                .instlFloor(wifi.getInstlFloor())
                .instlTy(wifi.getInstlTy())
                .instlMby(wifi.getInstlMby())
                .svcSE(wifi.getSvcSE())
                .cmcwr(wifi.getCmcwr())
                .cnstcYear(wifi.getCnstcYear())
                .inoutDoor(wifi.getInoutDoor())
                .remars3(wifi.getRemars3())
                .lat(String.valueOf(wifi.getLat()))
                .lnt(String.valueOf(wifi.getLnt()))
                .workDateTime(wifi.getWorkDateTime())
                .build();
    }
}
