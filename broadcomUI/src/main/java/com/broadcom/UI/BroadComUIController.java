package com.broadcom.UI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BroadComUIController {

    //@RequestMapping(value = "/**/{[path:[^\\.]*}")
    @RequestMapping(value = {"/ui/**", "/**/{path:[^\\.]*}"})
    public ModelAndView home() {
        ModelAndView mav=new ModelAndView("index");
        return mav;
    }
    @GetMapping("/broadcomui")
    public  String broadComUI(){
        return  "Broad com ui is up";
    }
}
