package com.zerobase.api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class WifiApiResultResponse {
    @SerializedName("CODE")
    private String code;    // INFO-000
    @SerializedName("MESSAGE")
    private String message; // 정상 처리되었습니다.
}
