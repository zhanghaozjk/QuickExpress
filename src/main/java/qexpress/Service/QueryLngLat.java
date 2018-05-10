package qexpress.Service;

import net.sf.json.JSONObject;
import qexpress.beans.Location;

public class QueryLngLat {
    public Location queryLL(String trace) {
        Location location = new Location();
        JSONObject object = new JSONObject();
        String temp = trace;
        try {
            int x = temp.indexOf("location") + 10;
            int y = temp.indexOf("precise") - 2;
            temp = temp.substring(x, y);
            object = JSONObject.fromObject(temp);
            location.setLng((Double) object.get("lng"));
            location.setLat((Double) object.get("lat"));
            location.setF(true);
        } catch (Exception e) {
            System.out.println(e);
            location.setF(false);
        }
        return location;
    }
}
