package com.example.demo.controller;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
@Controller
public class CustomErrorController implements ErrorController{
	@RequestMapping("/error")
    public String loginErrorPage(HttpServletRequest request, Model model) {
        //Status Code,
        //Error Message
        Integer statusCode = (Integer) request
        		.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage = "";
        switch (statusCode) {
            case 400 -> errorMessage = "Bad Request";
            case 401 -> errorMessage = "Unauthorized";
            case 403 -> errorMessage = "Forbidden";
            case 404 -> errorMessage = "Not Found";
            case 405 -> errorMessage = "Method Not Allowed";
            case 500 -> errorMessage = "Internal Server Error";
        }
        model.addAttribute("statusCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);
        return "error-page";
    }

}
