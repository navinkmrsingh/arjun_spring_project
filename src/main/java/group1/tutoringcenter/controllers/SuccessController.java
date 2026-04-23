package group1.tutoringcenter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SuccessController {

    @GetMapping("/add/success/{entityName}")
    public String showSuccess(@PathVariable String entityName, Model model) {
        model.addAttribute("entity", entityName);
        return "success";
    }
}