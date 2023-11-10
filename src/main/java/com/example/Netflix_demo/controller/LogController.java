package com.example.Netflix_demo.controller;



import org.springframework.ui.Model;
import com.example.Netflix_demo.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;

@Controller
@RequestMapping("/activity")
public class LogController {

    @Autowired
    ActivityLogService activityLogService;
    //
    @RequestMapping("/logs")
    public String getAllUsers(Model model){
        //buscar todos los usuarios y agregar el modelo
        model.addAttribute("logs",activityLogService.getAllLogs().size());
        model.addAttribute("todayDate", new Date());
        model.addAttribute("totalLogs", "Total logs: " + activityLogService.getAllLogs().size());

        return "logs";
    }
}
