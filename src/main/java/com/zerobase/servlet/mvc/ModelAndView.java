package com.zerobase.servlet.mvc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModelAndView {
    private String viewName;
    private Model model;
}
