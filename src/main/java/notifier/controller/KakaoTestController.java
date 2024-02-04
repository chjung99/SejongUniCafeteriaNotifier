package notifier.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class KakaoTestController {
    @PostMapping
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
