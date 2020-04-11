package com.covid19.india.Covid19India.controller;

import com.covid19.india.Covid19India.model.ErrorStatus;
import com.covid19.india.Covid19India.repository.CovidErrorStatusReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.InetAddress;

@ControllerAdvice
public class CovidExceptionHandlerResolver {

    @Autowired
    CovidErrorStatusReposiory errorStatusReposiory;


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex) {
        try {
            ErrorStatus errorStatus = new ErrorStatus();
            InetAddress address = InetAddress.getLoopbackAddress();
            String hostIP = address.toString();
            String[] hostIpAddress = hostIP.split("/");
            errorStatus.setUserHost(hostIpAddress[0]);
            errorStatus.setIpAddress(hostIpAddress[1]);
            errorStatus.setAccessURL(ex.getRequestURL());
            errorStatus.setErrorMessgae("Message:The URL you have reached is not in service at this time (404)");
            errorStatusReposiory.saveAndFlush(errorStatus);

        } catch (Exception e) {

        }
        return new ModelAndView("error");
    }
}

