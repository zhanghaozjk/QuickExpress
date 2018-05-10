package qexpress.beans;

import org.springframework.context.annotation.Configuration;

//@Configuration
public class Location {
    Double lng;
    Double lat;
    // truth
    boolean f;

    public boolean isF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "{\"lng\":\"" + lng + "\",\"lat\":\""+lat + "\"}";
    }
}
