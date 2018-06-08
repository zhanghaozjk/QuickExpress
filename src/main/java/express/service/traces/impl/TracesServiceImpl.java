package express.service.traces.impl;

import express.entity.Ship;
import express.service.location.impl.LocationServiceImpl;
import express.service.ship.ShipperService;
import express.service.ship.impl.ShipperServiceImpl;
import express.service.traces.TracesService;
import org.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Scope("prototype")
@Configuration
public class TracesServiceImpl implements TracesService {
    private Ship ship;

    @Override
    public Ship getShipConfig(String expCode) {
        ShipperService shipperService = new ShipperServiceImpl();
        try {
            ship = shipperService.getShip(expCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int size = ship.getTraces().size();
//        多线程处理
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i = 0; i < size; i++) {
            String location = ship.getTraces().get(i).getLocation();
            futures.add(exec.submit(new LocationServiceImpl(location)));
        }
        int i = 0;
        for (Future<String> fs : futures) {
            try {
                JSONObject locate = new JSONObject(fs.get());
                if (locate.getInt("status") == 0){
                    JSONObject result = locate.getJSONObject("result");
                    JSONObject location = result.getJSONObject("location");
                    Double lng = location.getDouble("lng");
                    Double lat = location.getDouble("lat");
                    ship.getTraces().get(i).setLng(lng.toString());
                    ship.getTraces().get(i).setLat(lat.toString());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            i++;
        }
        return ship;
    }
}
