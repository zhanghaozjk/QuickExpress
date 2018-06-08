package express.service.location.impl;

import express.api.HttpUtil;
import express.api.impl.ExpressApiImpl;
import express.api.impl.HttpUtilImpl;
import express.service.location.LocationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.Callable;

@Service
@Configuration
@Scope("prototype")
public class LocationServiceImpl implements LocationService, Callable<String> {

//    百度地图地理位置解析请求url
    private static String url;
//    百度地图地理位置请求固定参数
    private static String param;
    private static final String INPUT_PROPERTIES = "config.properties";
    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = ExpressApiImpl.class.getClassLoader().getResourceAsStream(INPUT_PROPERTIES);
            properties.load(inputStream);
            url = properties.getProperty("BaiduUrl");
            param = properties.getProperty("param");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String address;
    private String returns;

    public String getReturns() {
        return returns;
    }

    public LocationServiceImpl(String address) {
        this.address = address;
    }
    /**
     *
     * 获得百度地图的地址编码
     * @return
     * @throws IOException
     */
    @Override
    public String codingLocation() throws IOException {
        String allParams = "address=" + address + param;
        HttpUtil util = new HttpUtilImpl();
        String ret = util.sendGet(url, allParams);
//        切割成符合json格式的字符串
        int startPos = ret.indexOf("(") + 1;
        int endPos = ret.length() - 1;
        ret = ret.substring(startPos, endPos);
        returns = ret;
        return ret;
    }

    @Override
    public String call() throws Exception {
        return codingLocation();
    }
}
