package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // view가 없음
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello string"
    }

    @GetMapping("hello-api")
    @ResponseBody
    // ViewResolver 대신 Http 응답에 그대로 반환함
    // HttpMessageConverter(JsonConverter : 객체 <-> StringConverter : 문자)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // Default : json 반환, xml방식은 무겁고 태그 두번 쓰는거에 비해 직관적이고 심플함
    }

    static class Hello {
        private String name;

        // property 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
