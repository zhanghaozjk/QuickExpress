package express.service.location;

import express.service.location.LocationService;
import express.service.location.impl.LocationServiceImpl;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

public class LocationServiceImplTest {

    @Test

    public void codingLocation() throws IOException {
        LocationService locationService = new LocationServiceImpl("浙江省杭州市西湖区留和路号");
        String ret = locationService.codingLocation();
        System.out.println(ret);
        JSONObject jsonObject = new JSONObject(ret);
    }

    @Test
    public void call() throws Exception {
        LocationService locationService = new LocationServiceImpl("浙江省杭州市西湖区留和路号");
        String ret = ((LocationServiceImpl) locationService).call();
        System.out.println(ret);
    }
}