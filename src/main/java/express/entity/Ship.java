package express.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HC
 * 快递实体类
 */
public class Ship {
//    快递单号
    private String shipperCode;
//    快递路径
    private List<Trace> traces = new ArrayList<Trace>();
//    快递状态
    private String state;

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public List<Trace> getTraces() {
        return traces;
    }

    public void setTraces(List<Trace> traces) {
        this.traces = traces;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipperCode='" + shipperCode + '\'' +
                ", traces=" + traces +
                ", state='" + state + '\'' +
                '}';
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("shipperCode", shipperCode);
        jsonObject.put("state", state);

        JSONArray jsonArray = new JSONArray();
        for (Trace trace : traces) {
            JSONObject jsonTrace = new JSONObject();
            jsonTrace.put("time", trace.getTime());
            jsonTrace.put("location", trace.getLocation());
            jsonTrace.put("lat", trace.getLat());
            jsonTrace.put("lng", trace.getLng());
            jsonTrace.put("sort", trace.getSort());
            jsonArray.put(jsonTrace);
        }
        jsonObject.put("traces", jsonArray);

        return jsonObject;
    }
}

