package express.service.traces.impl;

import express.entity.Ship;
import express.service.traces.impl.TracesServiceImpl;
import org.junit.Test;

public class TracesServiceImplTest {

    @Test
    public void getShipConfig() {
        TracesServiceImpl tracesService = new TracesServiceImpl();
        Ship ship = tracesService.getShipConfig("486755428029");
        System.out.println(ship);
    }
}