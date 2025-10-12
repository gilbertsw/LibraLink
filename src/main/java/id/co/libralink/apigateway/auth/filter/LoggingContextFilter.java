package id.co.libralink.apigateway.auth.filter;

import id.co.libralink.common.constant.HeaderConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import id.co.libralink.common.enums.MDCKey;
import id.co.libralink.common.util.RequestUtil;
import id.co.libralink.common.util.StringUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public class LoggingContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        addMDCValue(request);
        filterChain.doFilter(request, response);
    }

    private void addMDCValue(HttpServletRequest request) {
        String requestId = request.getHeader(HeaderConstant.REQUEST_ID);
        if (StringUtil.isBlank(requestId)) {
            requestId = UUID.randomUUID().toString();
        }

        MDC.put(MDCKey.REQUEST_ID.name(), requestId);
        MDC.put(MDCKey.REQUEST_USER_AGENT.name(), request.getHeader(HeaderConstant.USER_AGENT));
        MDC.put(MDCKey.REQUEST_PATH.name(), RequestUtil.getRequestPath(request));
        MDC.put(MDCKey.REQUEST_URL.name(), RequestUtil.getRequestUrl(request));
    }

}
