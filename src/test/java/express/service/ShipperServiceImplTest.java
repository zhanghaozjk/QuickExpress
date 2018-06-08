package express.service;

import express.entity.Ship;
import express.service.ship.ShipperService;
import express.service.ship.impl.ShipperServiceImpl;
import org.junit.Test;

public class ShipperServiceImplTest {

    @Test
    public void getShip() throws Exception {
        ShipperService shipperService = new ShipperServiceImpl();
        Ship ship = shipperService.getShip("486755428029");
        System.out.println(ship);
    }
}