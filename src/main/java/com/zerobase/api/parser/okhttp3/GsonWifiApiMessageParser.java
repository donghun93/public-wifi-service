package com.zerobase.api.parser.okhttp3;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.zerobase.api.config.WifiApiConfig;
import com.zerobase.api.dto.WifiApiIntoDetailResponse;
import com.zerobase.api.dto.WifiApiResponse;
import com.zerobase.api.dto.WifiApiResultResponse;
import com.zerobase.api.dto.WifiLoadRequestFileType;
import com.zerobase.api.parser.WifiApiMessageParser;

import java.util.List;

public class GsonWifiApiMessageParser implements WifiApiMessageParser {

    private final Gson gson = new Gson();
    @Override
    public WifiApiResponse parsing(String message, WifiLoadRequestFileType fileType) {
        if(message.isEmpty()) {
            throw new IllegalArgumentException("message is empty!");
        }

        JsonElement root = JsonParser.parseString(message).getAsJsonObject()
                .get(WifiApiConfig.getInstance().getInfo().getServiceName());

        WifiApiResultResponse result = getResult(root);
        validateResponse(result);

        // 통과하면
        return WifiApiResponse.builder()
                .totalCount(getTotalCount(root))
                .result(result)
                .details(getDetailList(root))
                .build();
    }

    private WifiApiResultResponse getResult(JsonElement element) {
        return gson.fromJson(element.getAsJsonObject().get("RESULT"), WifiApiResultResponse.class);
    }

    private Long getTotalCount(JsonElement element) {
        return gson.fromJson(element.getAsJsonObject().get("list_total_count"), Long.class);
    }

    private List<WifiApiIntoDetailResponse> getDetailList(JsonElement element) {
        JsonArray row = element.getAsJsonObject().get("row").getAsJsonArray();
        return gson.fromJson(row, new TypeToken<List<WifiApiIntoDetailResponse>>(){}.getType());
    }

    private void validateResponse(WifiApiResultResponse wifiApiResultResponse) {
        if(!wifiApiResultResponse.getCode().equals("INFO-000")) {
            String errorMessage = wifiApiResultResponse.getCode() + ", "
                    + wifiApiResultResponse.getMessage();
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
