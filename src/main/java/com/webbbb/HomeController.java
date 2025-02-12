package com.webbbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index.html";  // Redirect ไปหน้าเริ่มต้น
    }
}
