package shop.mtcoding.min.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/")
    public String main() {
        return "board/main";
    }

    // @GetMapping("/detail")
    // public String detail() {
    // return "";
    // }

    @GetMapping("/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/updateForm")
    public String updateForm() {
        return "board/updateForm";
    }

}
