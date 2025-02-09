package com.SpringBootApplicationTemplate.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Generate unique ID for this request
        String requestId = UUID.randomUUID().toString();

        // Wrap request and response to cache their content
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {
            // Log the request
            logger.info("[{}] Request: {} {} {}",
                    requestId,
                    request.getMethod(),
                    request.getRequestURI(),
                    request.getQueryString() != null ? "?" + request.getQueryString() : "");

            // Process the request
            filterChain.doFilter(requestWrapper, responseWrapper);

            // Log the response
            long duration = System.currentTimeMillis() - startTime;
            logger.info("[{}] Response: Status={} Duration={}ms",
                    requestId,
                    responseWrapper.getStatus(),
                    duration);

            // Only log request/response body for non-binary content types
            String contentType = request.getContentType();
            if (contentType != null && contentType.startsWith("application/json")) {
                String requestBody = new String(requestWrapper.getContentAsByteArray());
                if (!requestBody.isEmpty()) {
                    logger.debug("[{}] Request body: {}", requestId, requestBody);
                }

                String responseBody = new String(responseWrapper.getContentAsByteArray());
                if (!responseBody.isEmpty()) {
                    logger.debug("[{}] Response body: {}", requestId, responseBody);
                }
            }

        } catch (Exception e) {
            logger.error("[{}] Error processing request", requestId, e);
            throw e;
        } finally {
            // Copy content to the original response
            responseWrapper.copyBodyToResponse();
        }
    }
}