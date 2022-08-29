package com.zerobase.publicwifiservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Wifi {
    private final Long id;
    private final String distance;
    private final String mgrNo;
    private final String wrdoFc;
    private final String mainName;
    private final String address1;
    private final String address2;
    private final String instlFloor;
    private final String instlTy;
    private final String instlMby;
    private final String svcSE;
    private final String cmcwr;
    private final String cnstcYear;
    private final String inoutDoor;
    private final String remars3;
    private final Double lat;
    private final Double lnt;
    private final String workDateTime;

    @Builder
    private Wifi(Long id, String distance, String mgrNo, String wrdoFc, String mainName, String address1, String address2, String instlFloor, String instlTy, String instlMby, String svcSE, String cmcwr, String cnstcYear, String inoutDoor, String remars3, Double lat, Double lnt, String workDateTime) {
        this.id = id;
        this.distance = distance;
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
}
