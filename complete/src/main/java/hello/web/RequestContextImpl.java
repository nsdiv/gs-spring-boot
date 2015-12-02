package hello.web;

public class RequestContextImpl implements RequestContext {
    private static final String LOCALHOST = "127.0.0.1";
    private String originatingIp = LOCALHOST;

    public RequestContextImpl() {
    }

    @Override
    public String getOriginatingIp() {
        return originatingIp;
    }

    @Override
    public void setOriginatingIp(String originatingIp) {
        this.originatingIp = originatingIp;
    }
}
