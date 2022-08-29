package com.zerobase.servlet;

import com.zerobase.servlet.manager.HandlerAdaptersManager;
import com.zerobase.servlet.manager.HandlerMappingsManager;
import com.zerobase.servlet.manager.PropertiesManager;
import com.zerobase.servlet.mvc.ModelAndView;
import com.zerobase.servlet.mvc.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("*.do")
public class ZeroDispatcherServlet extends HttpServlet {

    private Map<String, Object> handlerMappings = null;
    private List<ZeroBaseHandlerAdapter> handlerAdapters = null;


    @Override
    public void init() throws ServletException {
        initProperties();
        initHandlerMappings();
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);
        if (handler != null) {
            ZeroBaseHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
            if (handlerAdapter != null) {
                ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);
                if (modelAndView != null) {
                    View view = viewResolver(modelAndView.getViewName());
                    view.render(request, response, modelAndView.getModel());
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private Object getHandler(HttpServletRequest request) {
        String uri = String.valueOf(request.getRequestURI());
        return handlerMappings.getOrDefault(uri, null);
    }

    private ZeroBaseHandlerAdapter getHandlerAdapter(Object handler) {
        for (ZeroBaseHandlerAdapter adapter : this.handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        return null;
    }

    private View viewResolver(String viewName) {
        if (viewName.equals("index")) {
            return new View(viewName + ".jsp");
        } else {
            return new View("WEB-INF/" + viewName + ".jsp");
        }
    }
    
    private void initProperties() {
        PropertiesManager propertiesManager = new PropertiesManager();
        propertiesManager.init();
    }
    
    private void initHandlerMappings() {
        HandlerMappingsManager mappingsManager = new HandlerMappingsManager();
        handlerMappings = mappingsManager.createHandlerMappings();
    }

    private void initHandlerAdapters() {
        HandlerAdaptersManager adaptersManager = new HandlerAdaptersManager();
        handlerAdapters = adaptersManager.createHandlerAdapter();
    }
}
