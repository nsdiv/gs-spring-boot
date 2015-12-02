package hello.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Intended to be wired up early in the chain to set incoming request info on
 * a request-scoped bean.
 */
public class RequestContextFilter extends OncePerRequestFilter implements Ordered {

    private final static Logger logger = LoggerFactory.getLogger(RequestContextFilter.class);

    /**
     * We want to be first in line, almost
     */
    private int order = Ordered.HIGHEST_PRECEDENCE - 1;

    /**
     * This should vary by request
     */
    @Autowired
    private RequestContext requestContext;

    @Override
    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String ipAddress = request.getRemoteAddr();
            if (logger.isDebugEnabled())
                logger.debug("Setting incoming ip address on request scope bean; ipAddress={}", ipAddress);
            requestContext.setOriginatingIp(ipAddress);

        } catch (Exception e) {
            logger.error("Unable to extract IP Address from request", e);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return request.getRequestURI().startsWith("/resources/");
    }

}
