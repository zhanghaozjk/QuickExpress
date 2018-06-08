package express.api;

import express.api.impl.ExpressApiImpl;
import express.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ExpressApiImplTest {

    @Autowired
    ExpressApiImpl expressApiImpl;

    public ExpressApiImpl getExpressApiImpl() {
        return expressApiImpl;
    }

    @Test
    public void getOrderShipper() throws Exception {
        String code = expressApiImpl.getOrderShipper("486755428029");
        System.out.println(code);

    }

    @Test
    public void getOrderTracesByJson(){
        try {
            String traces = expressApiImpl.getOrderTracesByJson("ZTO", "486755428029");
            System.out.println(traces);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}