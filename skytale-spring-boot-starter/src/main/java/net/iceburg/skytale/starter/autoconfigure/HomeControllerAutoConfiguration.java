package net.iceburg.skytale.starter.autoconfigure;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControllerAutoConfiguration {

    @RequestMapping("/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}