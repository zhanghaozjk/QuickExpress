package express.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public interface HttpUtil {
    public String sendGet(String url, String param) throws UnsupportedEncodingException, IOException;
    public String sendPost(String url, String param) throws UnsupportedEncodingException, IOException;
}
