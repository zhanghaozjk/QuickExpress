package qexpress.kdniaoAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import qexpress.beans.Express;

@Configuration
public class ExpressExtender{
//    ExpressApi expressApi = new ExpressApi();
//    Express express = new Express();


    ExpressApi expressApi = new ExpressApi();


    Express express = new Express();

    String EBusinessID="1327265";
    String AppKey="ee379807-431f-42b7-8eec-619de2831a52";
    String ReqURL="http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx";

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }

//    @Bean
    public String getExpressStatus() throws Exception {
        String status;
        // 有快递公司的情况查询
        if (express.getShipperCode() != null ) {
            status = expressApi.getOrderTracesByJson(express.getShipperCode(), express.getExpressNo());
        } else if (express.getExpressNo() != null) {
            // 仅有快递单号的查询
            express.setShipperCode(expressApi.getOrderTracesByJson(express.getExpressNo()));
            status = expressApi.getOrderTracesByJson(express.getShipperCode(), express.getExpressNo());
        } else {
            status = "";
        }
//        System.out.println(status);
//        System.out.println(System.getProperty("file.encoding"));
        return status;
    }

}
