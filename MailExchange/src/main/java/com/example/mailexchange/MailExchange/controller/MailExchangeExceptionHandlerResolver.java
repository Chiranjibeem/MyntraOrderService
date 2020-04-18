package com.example.mailexchange.MailExchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class MailExchangeExceptionHandlerResolver {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {

        return new ModelAndView("error");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleMaxSizeException(
            MaxUploadSizeExceededException exc,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {

        String servletPath = request.getServletPath();
        model.addAttribute("error","File Size Excedded");
        return new ModelAndView(servletPath);
    }
}

