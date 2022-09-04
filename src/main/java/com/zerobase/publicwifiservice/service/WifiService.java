package com.zerobase.publicwifiservice.service;

import com.zerobase.api.WifiApiService;
import com.zerobase.api.dto.*;
import com.zerobase.publicwifiservice.domain.Wifi;
import com.zerobase.publicwifiservice.dto.WifiNearDetailInfoResponse;
import com.zerobase.publicwifiservice.dto.WifiNearInfoRequest;
import com.zerobase.publicwifiservice.repository.HistoryRepository;
import com.zerobase.publicwifiservice.repository.WifiRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.zerobase.api.dto.WifiLoadRequestFileType.JSON;

@RequiredArgsConstructor
public class WifiService {
    private final WifiApiService wifiApiService;
    private final WifiRepository wifiRepository;
    private final HistoryRepository historyRepository;

    public int loadWifiAndSave() {
        WifiApiPagingInfo wifiTotalCountAndQueryCount = wifiApiService.getWifiTotalCountAndQueryCount();
        wifiLoadAndSaveInternal(wifiTotalCountAndQueryCount);
        return wifiTotalCountAndQueryCount.getTotalCount();
    }

    public  List<WifiNearDetailInfoResponse> getWifiSearchNearInfo(WifiNearInfoRequest nearInfoRequest) {
        historyRepository.save(nearInfoRequest.toHistoryEntity());
        int MAX_REQUEST_COUNT = 20;
        List<Wifi> nearWifiInfo = wifiRepository.findNearWifiInfo(nearInfoRequest.toWifiNearInfo(MAX_REQUEST_COUNT));
        return nearWifiInfo.stream().map(WifiNearDetailInfoResponse::toResponse).collect(Collectors.toList());
    }

    private void wifiLoadAndSaveInternal(WifiApiPagingInfo pagingInfo) {
        wifiRepository.deleteAll();
        WifiRequestPaging paging = createPaging(pagingInfo.getMaxRequestCount());

        for (int i = 0; i < pagingInfo.getRequestCount(); i++) {
            System.out.println(paging);
            loadAndSave(paging);
            paging.updatePagingNumber();
        }
    }

    private void loadAndSave(WifiRequestPaging paging) {
        WifiApiRequest apiRequest = createApiRequest(paging);
        WifiApiResponse wifiApiResponse = wifiApiService.getWifi(apiRequest);
        wifiRepository.saveAll(wifiApiResponse.getDetails().stream()
                .map(WifiApiIntoDetailResponse::toEntity)
                .collect(Collectors.toList()));
    }

    private WifiRequestPaging createPaging(int maxQueryCount) {
        return WifiRequestPaging.builder()
                .start(0)
                .maxRequestCount(maxQueryCount)
                .build();
    }

    private WifiApiRequest createApiRequest(WifiRequestPaging paging) {
        return WifiApiRequest.builder()
                .start(paging.start)
                .end(paging.end)
                .requestFileType(JSON)
                .build();
    }


    static class WifiRequestPaging {
        private int start;
        private int end;
        private final int maxRequestCount;

        @Builder
        private WifiRequestPaging(int start, int maxRequestCount) {
            this.start = start;
            this.end = maxRequestCount - 1;
            this.maxRequestCount = maxRequestCount;
        }

        private void updatePagingNumber() {
            this.start += maxRequestCount;
            this.end += maxRequestCount;
        }

        @Override
        public String toString() {
            return "WifiRequestPaging{" +
                    "start=" + start +
                    ", end=" + end +
                    ", maxRequestCount=" + maxRequestCount +
                    '}';
        }
    }
}