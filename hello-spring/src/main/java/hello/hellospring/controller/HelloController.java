package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // resources->templates->hello를 찾음
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("akk") String name, Model model) {
        model.addAttribute("akk", name);
        return "hello-template";
    }

    @GetMapping("Hello-string")
    @ResponseBody // HTTP 통신의 BODY에 return 값을 직접 넣어준다. ( html의 th:text ${name} 이런 식의 구애를 받지 않음)
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
