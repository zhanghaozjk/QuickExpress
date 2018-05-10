package qexpress.beans;


import org.springframework.context.annotation.Configuration;

@Configuration
public class Express {
    String shipperCode;
    String expressNo;
    String traces[];


    public Express(String shipperCode, String expressNo, String traces[]) {
        this.shipperCode = shipperCode;
        this.expressNo = expressNo;
        this.traces = traces;
    }

    public Express() {}

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String[] getTraces() {
        return traces;
    }

    public void setTraces(String traces[]) {
        this.traces = traces;
    }
}
