package com.zerobase.api.dto;

import com.google.gson.annotations.SerializedName;
import com.zerobase.publicwifiservice.domain.Wifi;
import lombok.Getter;

@Getter
public class WifiApiIntoDetailResponse {
    @SerializedName(value = "X_SWIFI_MGR_NO")
    private String mgrNo;
    @SerializedName(value = "X_SWIFI_WRDOFC")
    private String wrdoFc;
    @SerializedName(value = "X_SWIFI_MAIN_NM")
    private String mainName;
    @SerializedName(value = "X_SWIFI_ADRES1")
    private String address1;
    @SerializedName(value = "X_SWIFI_ADRES2")
    private String address2;
    @SerializedName(value = "X_SWIFI_INSTL_FLOOR")
    private String instlFloor;
    @SerializedName(value = "X_SWIFI_INSTL_TY")
    private String instlTy;
    @SerializedName(value = "X_SWIFI_INSTL_MBY")
    private String instlMby;
    @SerializedName(value = "X_SWIFI_SVC_SE")
    private String svcSe;
    @SerializedName(value = "X_SWIFI_CMCWR")
    private String cmcWr;
    @SerializedName(value = "X_SWIFI_CNSTC_YEAR")
    private String cnstcYear;
    @SerializedName(value = "X_SWIFI_INOUT_DOOR")
    private String inoutDoor;
    @SerializedName(value = "X_SWIFI_REMARS3")
    private String remars3;
    //@SerializedName(value = "LAT")
    @SerializedName(value = "LNT")
    private Double lat;
    //@SerializedName(value = "LNT")
    @SerializedName(value = "LAT")
    private Double lnt;
    @SerializedName(value = "WORK_DTTM")
    private String workDateTime;

    public Wifi toEntity() {
        return Wifi.builder()
                .mgrNo(this.mgrNo)
                .wrdoFc(this.wrdoFc)
                .mainName(this.mainName)
                .address1(this.address1)
                .address2(this.address2)
                .instlFloor(this.instlFloor)
                .instlTy(this.instlTy)
                .instlMby(this.instlMby)
                .svcSE(this.svcSe)
                .cmcwr(this.cmcWr)
                .cnstcYear(this.cnstcYear)
                .inoutDoor(this.inoutDoor)
                .remars3(this.remars3)
                .lat(this.lat)
                .lnt(this.lnt)
                .workDateTime(this.workDateTime)
                .build();
    }
}
