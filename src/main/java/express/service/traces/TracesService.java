package express.service.traces;

import express.entity.Ship;

public interface TracesService{
    public Ship getShipConfig(String expCode) throws Exception;
}
