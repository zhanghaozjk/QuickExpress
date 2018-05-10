package qexpress.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import qexpress.beans.Express;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryRecodeService {

    public Express query(String status) throws IOException {
        Express express = new Express();
        JSONObject base = JSONObject.fromString(status);
        express.setExpressNo((String) base.get("LogisticCode"));
        express.setShipperCode((String) base.get("ShipperCode"));

//        System.out.println(status);

//        Traces
        JSONArray jtraces = JSONArray.fromObject(base.get("Traces"));

        Iterator<Object> it = jtraces.iterator();
        List<String> straces = new ArrayList<String>();
        //String straces[];
        while (it.hasNext()) {
            JSONObject ob = (JSONObject) it.next();
            String curr = ob.getString("AcceptStation");
            curr = curr.replaceAll(" ", "");
            straces.add(curr);
        }
        express.setTraces((String[]) straces.toArray(new String[0]));

        return express;
    }
}
