package express.api;

public interface ExpressApi {
    public String getOrderShipper(String expNo) throws Exception;
    public String getOrderTracesByJson(String expCode, String expNo) throws Exception;
}
