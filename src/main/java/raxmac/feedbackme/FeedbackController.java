package raxmac.feedbackme;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackController {

    @RequestMapping("hello")
    public String sayHello() {
        return "Hello";
    }
}
