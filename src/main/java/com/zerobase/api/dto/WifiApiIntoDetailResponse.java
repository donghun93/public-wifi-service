package com.zerobase.api.dto;

import com.google.gson.annotations.SerializedName;
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
}
