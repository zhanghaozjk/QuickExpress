package qexpress.Service;

import qexpress.http.HttpUtil;

import java.io.IOException;

public class QueryLocation {
    public String query(String address) throws IOException {
        //address.se
        String url = "http://api.map.baidu.com/geocoder/v2/";
        String param = "&output=json&ak=1a23ZobdgoHbAszhYr4lowx6vMcruajS&callback=showLocation";
        param = "address=" + address + param;

//        System.out.println(param);
        String ret = HttpUtil.sendGet(url, param);
        return ret;
    }
}
