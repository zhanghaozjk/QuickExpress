import org.springframework.beans.factory.annotation.Autowired;
import qexpress.kdniaoAPI.ExpressExtender;

public class Main {

    @Autowired
    ExpressExtender expressExtender;

    public void r() throws Exception {
        expressExtender = new ExpressExtender();
        String expressNo = "486755428029";
        expressExtender.getExpress().setExpressNo(expressNo);
        String status = expressExtender.getExpressStatus();
        System.out.println(status);
    }



    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.r();
    }
}
