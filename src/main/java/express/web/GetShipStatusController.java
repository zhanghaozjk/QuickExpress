package express.web;

import express.entity.Ship;
import express.service.traces.TracesService;
import express.service.traces.impl.TracesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/GetShipStatus", produces = "application/json;charset=UTF-8")
public class GetShipStatusController {
    private Ship ship;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String getShipStatus(@RequestParam("expCode") String expCode) {
        TracesService tracesService = new TracesServiceImpl();
        try {
            ship = tracesService.getShipConfig(expCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ship.toJSONObject().toString();
    }
}
