package com.zerobase.servlet.mvc;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Model {
    private Map<String, Object> objectMap = new HashMap<>();

    public void addAttribute(String key, Object object) {
        objectMap.put(key, object);
    }
}
