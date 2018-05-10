package qexpress.http;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {

//    public static void main(String[] args) throws IOException {
//        String url = "http://api.map.baidu.com/geocoder/v2/";
//        String param = "address=浙江工业大学&output=json&ak=1a23ZobdgoHbAszhYr4lowx6vMcruajS&callback=showLocation";
//        String ret = sendGet(url, param);
//        System.out.println(ret);
//        JSONObject jsonObject = new JSONObject();
//        String nret[] = ret.split("\"location\":");
//
//        String s = "{  \"LogisticCode\" : \"486506326096\",  \"ShipperCode\" : \"ZTO\",  \"Traces\" : [ {    \"AcceptStation\" : \"[深圳市]  [深圳公明] 的 客户刘畅 (15364270818) 已收件\",    \"AcceptTime\" : \"2018-04-02 19:04:17\"  }, {    \"AcceptStation\" : \"[深圳市]  快件离开 [深圳公明] 发往 [杭州中转部]\",    \"AcceptTime\" : \"2018-04-03 01:46:01\"  }, {    \"AcceptStation\" : \"[深圳市]  快件到达 [深圳中心]\",    \"AcceptTime\" : \"2018-04-03 03:48:21\"  }, {    \"AcceptStation\" : \"[深圳市]  快件离开 [深圳中心] 发往 [杭州中转部]\",    \"AcceptTime\" : \"2018-04-03 03:50:05\"  }, {    \"AcceptStation\" : \"[嘉兴市]  快件到达 [杭州中转部]\",    \"AcceptTime\" : \"2018-04-04 07:34:33\"  }, {    \"AcceptStation\" : \"[嘉兴市]  快件离开 [杭州中转部] 发往 [杭州留下区]\",    \"AcceptTime\" : \"2018-04-04 10:16:58\"  }, {    \"AcceptStation\" : \"[杭州市]  快件已到达 [杭州留下区],业务员 朱丹工大西六菜鸟驿站(13396531079) 正在第2次派件, 请保持电话畅通,并耐心等待\",    \"AcceptTime\" : \"2018-04-04 15:41:44\"  }, {    \"AcceptStation\" : \"[杭州市]  快件已在 [杭州留下区] 签收,签收人: 拍照签收, 感谢使用中通快递,期待再次为您服务!\",    \"AcceptTime\" : \"2018-04-04 15:49:05\"  } ],  \"State\" : \"3\",  \"EBusinessID\" : \"1327265\",  \"Success\" : true}";
//
//        JSONObject pp =  jsonObject.fromString(s);
//        System.out.println(pp.get("LogisticCode"));
//        String m = pp.toString();
//    }

    /**
     * 向指定URL发送GET方法的请求
     */
    public static String sendGet(String url, String param) throws UnsupportedEncodingException, IOException {
        return sendGet(url, param, null);
    }
    public static String sendGet(String url, String param, Map<String, String> header) throws UnsupportedEncodingException, IOException {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url + "?" + param;
        URL realUrl = new URL(urlNameString);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        //设置超时时间
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(15000);
        // 设置通用的请求属性
        if (header!=null) {
            Iterator<Entry<String, String>> it =header.entrySet().iterator();
            while(it.hasNext()){
                Entry<String, String> entry = it.next();
                System.out.println(entry.getKey()+":::"+entry.getValue());
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
//        for (String key : map.keySet()) {
//            System.out.println(key + "--->" + map.get(key));
//        }
        // 定义 BufferedReader输入流来读取URL的响应，设置utf8防止中文乱码
        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        if (in != null) {
            in.close();
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     */
    public static String sendPost(String url, String param) throws UnsupportedEncodingException, IOException {
        return sendPost(url, param, null);
    }

    public static String sendPost(String url, String param, Map<String, String> header) throws UnsupportedEncodingException, IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        URL realUrl = new URL(url);
        // 打开和URL之间的连接
        URLConnection conn = realUrl.openConnection();
        //设置超时时间
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(15000);
        // 设置通用的请求属性
        if (header!=null) {
            for (Entry<String, String> entry : header.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 发送POST请求必须设置如下两行
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        out = new PrintWriter(conn.getOutputStream());
        // 发送请求参数
        out.print(param);
        // flush输出流的缓冲
        out.flush();
        // 定义BufferedReader输入流来读取URL的响应
        in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        if(out!=null){
            out.close();
        }
        if(in!=null){
            in.close();
        }
        return result;
    }
}