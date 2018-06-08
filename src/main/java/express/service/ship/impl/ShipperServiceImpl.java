package express.service.ship.impl;

import express.api.ExpressApi;
import express.api.impl.ExpressApiImpl;
import express.entity.Ship;
import express.entity.Trace;
import express.service.ship.ShipperService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("shipperService")
@Configuration
@Scope("prototype")
public class ShipperServiceImpl implements ShipperService {

    /**
     *
     * @author HC
     * @param expressNo 快递单号
     * @return 整理好的快递数据
     * @throws Exception
     */
    public Ship getShip(String expressNo) throws Exception {
        Ship ship = new Ship();
        ship.setShipperCode(expressNo);
        ExpressApi expressApi = new ExpressApiImpl();
//        快递公司的编号
        String expCode = expressApi.getOrderShipper(expressNo);
//        快递的当前全部信息
        String status = expressApi.getOrderTracesByJson(expCode, expressNo);

        JSONObject jsonObject = new JSONObject(status);
        try {
            if (jsonObject.getBoolean("Success")) {
                ship.setState(jsonObject.getString("State"));
                JSONArray traces = jsonObject.getJSONArray("Traces");
                int tracesSize = traces.length();
                for (int i = 0; i < tracesSize; i++) {
//                    有可能获取不到内容
                    JSONObject trace = traces.getJSONObject(i);
                    Trace entityTrace = new Trace();
                    entityTrace.setTime(trace.getString("AcceptTime"));
                    entityTrace.setSort(i);
                    String acceptStation = trace.getString("AcceptStation");
                    acceptStation = acceptStation.replaceAll(" ", "");
                    entityTrace.setLocation(acceptStation);
                    ship.getTraces().add(entityTrace);
                }
            } else {
                ship.setState("-1");
                Trace entityTrace = new Trace();
                Date sqlDate = new java.sql.Date(new Date().getTime());
                entityTrace.setTime(sqlDate.toString());
                entityTrace.setSort(0);
                entityTrace.setLocation("发生了错误，无法查询到此快递的路径，请稍后再试");
                ship.getTraces().add(entityTrace);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ship.setState("-1");
            Trace entityTrace = new Trace();
            Date sqlDate = new java.sql.Date(new Date().getTime());
            entityTrace.setTime(sqlDate.toString());
            entityTrace.setSort(0);
            entityTrace.setLocation("发生了错误，无法查询到此快递的路径，请稍后再试");
            ship.getTraces().add(entityTrace);
        }

        return ship;
    }
}
