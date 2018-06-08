package express.service.ship;

import express.entity.Ship;

public interface ShipperService {
    public Ship getShip(String expressNo) throws Exception;
}
