package group1.tutoringcenter.controllers;

import group1.tutoringcenter.services.TutoringCenterService;
import group1.tutoringcenter.models.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {

    private final TutoringCenterService service;

    public RequestController(TutoringCenterService service) {
        this.service = service;
    }

    @GetMapping("/request")
    public String showRequestsPage(Model model) {
        model.addAttribute("requests", service.getRequests());
        return "request";
    }

    @GetMapping("/request/add")
    public String showAddRequestPage() {
        return "request_add";
    }

    @PostMapping("/request/add")
    public String addRequest(@RequestParam String studentName,
                             @RequestParam String status) {

        int newId = service.getRequests().size() + 1;
        Request newRequest = new Request(newId, studentName, status);
        service.addRequest(newRequest);

        return "redirect:/add/success/request";
    }
}