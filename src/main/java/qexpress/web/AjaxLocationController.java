package qexpress.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import qexpress.Service.QueryLngLat;
import qexpress.Service.QueryLocation;
import qexpress.beans.Express;
import qexpress.beans.Location;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/ajaxLocation", produces = "application/json;charset=UTF-8")
public class AjaxLocationController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<String> getLocations(HttpSession session) throws IOException {
        Express express = (Express) session.getAttribute("express");
        String[] traces = express.getTraces();
        List<String> locations = new ArrayList<String>();
        List<String> locationJson = new ArrayList<String>();
        Location location = new Location();
        for (String s : traces) {
            String temp = new QueryLocation().query(s);
            locations.add(temp);
            location = new QueryLngLat().queryLL(temp);
            if (location.isF() != false) {
                locationJson.add(location.toString());
            }

        }
        return locationJson;
    }
}

