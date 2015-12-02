package hello.web;

/**
 * Intended to be wired up as a RequestScope bean
 * EG: @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
 *
 * Can be used for holding headers, locale, whatever could be useful down the stack.
 */
public interface RequestContext {

    String getOriginatingIp();

    void setOriginatingIp(String originatingIp);
}
