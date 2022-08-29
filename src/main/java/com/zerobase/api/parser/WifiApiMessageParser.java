package com.zerobase.api.parser;

import com.zerobase.api.dto.WifiApiResponse;
import com.zerobase.api.dto.WifiLoadRequestFileType;

public interface WifiApiMessageParser {
    WifiApiResponse parsing(String message, WifiLoadRequestFileType fileType);
}
