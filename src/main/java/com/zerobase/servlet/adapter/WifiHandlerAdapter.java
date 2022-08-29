package com.zerobase.servlet.adapter;

import com.mysql.cj.util.StringUtils;
import com.zerobase.servlet.ZeroBaseHandlerAdapter;
import com.zerobase.servlet.manager.WifiUriMapper;
import com.zerobase.servlet.mvc.ModelAndView;
import com.zerobase.publicwifiservice.controller.WifiController;
import com.zerobase.publicwifiservice.dto.WifiNearInfoRequest;
import com.zerobase.servlet.exception.WifiException;
import com.zerobase.servlet.mvc.Model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WifiHandlerAdapter implements ZeroBaseHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof WifiController);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Model model = new Model();
        String viewName = null;
        try {
            WifiController controller = (WifiController) handler;
            viewName = process(request, controller, model);
        } catch (WifiException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("errorCode", e.getErrorCode());
            viewName = "error";
        } catch (Exception e) {
            e.getStackTrace();
            model.addAttribute("errorMessage", "잘못된 요청입니다");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }

    private String process(HttpServletRequest request, WifiController handler, Model model) {
        String uri = String.valueOf(request.getRequestURI());

        if (uri.equals(WifiUriMapper.getWifiHistoryURI())) {
            return handler.getWifiHistory(model);
        } else if (uri.equals(WifiUriMapper.getWifiLoadURI())) {
            return handler.wifiLoadAndSave(model);
        } else if (uri.equals(WifiUriMapper.getWifiNearURI())) {
            wifiNearRequestParameterValidate(request);
            return handler.wifiSearchNearInfo(model, WifiNearInfoRequest.builder()
                    .lat(Double.valueOf(request.getParameter("lat")))
                    .lnt(Double.valueOf(request.getParameter("lnt")))
                    .build());
        } else if (uri.equals(WifiUriMapper.getDeleteHistory())) {
            return handler.deleteHistory(Long.valueOf(request.getParameter("deleteId")));
        }
        return null;
    }

    private void wifiNearRequestParameterValidate(HttpServletRequest request) {
        if(StringUtils.isNullOrEmpty(request.getParameter("lat")) ||
                StringUtils.isNullOrEmpty(request.getParameter("lat"))) {
            throw new WifiException("404", "좌표 정보를 확인해주세요");
        }
    }
}