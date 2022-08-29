package com.zerobase.servlet.manager;

import com.zerobase.servlet.ZeroBaseHandlerAdapter;
import com.zerobase.servlet.adapter.WifiHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HandlerAdaptersManager {
    public List<ZeroBaseHandlerAdapter> createHandlerAdapter() {
        List<ZeroBaseHandlerAdapter> adapters = new ArrayList<>();
        adapters.add(new WifiHandlerAdapter());
        return adapters;
    }
}
