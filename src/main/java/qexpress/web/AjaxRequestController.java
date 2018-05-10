package qexpress.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qexpress.Service.QueryRecodeService;
import qexpress.beans.Express;
import qexpress.kdniaoAPI.ExpressExtender;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
//@EnableWebMvc
@RequestMapping(value = "/ajaxrequest", produces = "application/json;charset=UTF-8")
public class AjaxRequestController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<String> ajaxRequest(@RequestParam("kdno") String kdno, HttpSession session) throws Exception {

        ExpressExtender expressExtender = new ExpressExtender();
        String expressNo = kdno;

        expressExtender.getExpress().setExpressNo(expressNo);
        String status = expressExtender.getExpressStatus();

        QueryRecodeService service = new QueryRecodeService();
        Express express = new Express();
        express = service.query(status);

        session.setAttribute("express", express);
        List<String> list = new ArrayList<String>();
        for (String trace : express.getTraces()) {
            System.out.println(trace);
            list.add(trace);
        }
        return list;
    }
}
