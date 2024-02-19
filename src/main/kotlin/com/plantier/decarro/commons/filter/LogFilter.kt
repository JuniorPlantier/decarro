package com.plantier.decarro.commons.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.stereotype.Component


@Component
class LogFilter : Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse?, chain: FilterChain) {

        // adicionar um header
        val httpRequest = request as HttpServletRequest
        val headerNames = httpRequest.headerNames
        while(headerNames.hasMoreElements()) {
            System.out.println("Header: " + httpRequest.getHeader(headerNames.nextElement()));
        }

        val requestWrapper = HttpServletRequestWrapper(httpRequest)
        

        chain.doFilter(request, response)
    }
}