package br.com.letscode.java.helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello-word")
@RestController
public class HelloWordRestController {

    @GetMapping
    public String helloWord(){
        return "Hello word - I love Spring S2";
    }

}
