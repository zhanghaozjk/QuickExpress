package qexpress.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = {"/qexpress", "/"}, produces = "text/html;charset=UTF-8")
public class HomeController {
    @RequestMapping(method = GET)
    public String home() {
        return "/index";
    }
}
