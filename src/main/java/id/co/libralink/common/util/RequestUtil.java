package id.co.libralink.common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public final class RequestUtil {

	private RequestUtil() {
		// can't instantiate
	}

	public static String getClientIpAddress(HttpServletRequest request) {
		String[] HEADERS_TO_TRY = {
	            "X-Forwarded-For",
	            "Proxy-Client-IP",
	            "WL-Proxy-Client-IP",
	            "HTTP_X_FORWARDED_FOR",
	            "HTTP_X_FORWARDED",
	            "HTTP_X_CLUSTER_CLIENT_IP",
	            "HTTP_CLIENT_IP",
	            "HTTP_FORWARDED_FOR",
	            "HTTP_FORWARDED",
	            "HTTP_VIA",
	            "REMOTE_ADDR"
		};
		
	    for (String header : HEADERS_TO_TRY) {
	        String ip = request.getHeader(header);
	        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	    }

	    return request.getRemoteAddr();
	}

	public static String getRequestPath(HttpServletRequest request) {
		StringBuilder path = new StringBuilder()
				.append(request.getContextPath())
				.append(request.getServletPath());

		if (request.getPathInfo() != null) {
			path.append(request.getPathInfo());
		}

		return path.toString();
	}

	public static String getRequestUrl(HttpServletRequest request) {
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
		String queryString = request.getQueryString();

		if (queryString != null) {
			return requestURL.append('?').append(queryString).toString();
		}

		return requestURL.toString();
	}

	public static Map<String, Object> getHeaders(HttpServletRequest request) {
		return Collections.list(request.getHeaderNames()).stream()
				.collect(Collectors.toMap(Function.identity(), request::getHeaders));
	}

}
