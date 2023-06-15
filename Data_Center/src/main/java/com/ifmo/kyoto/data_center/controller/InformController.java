package com.ifmo.kyoto.data_center.controller;

import com.ifmo.kyoto.data_center.service.PeopleCounterService;
import com.ifmo.kyoto.data_center.service.UnsanctionedAccessChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
public class InformController {

    @Autowired
    private PeopleCounterService peopleCounterService;


    @GetMapping("get_info")
    public @ResponseBody ResponseEntity getPeoplesInfo() {
        System.out.println("трахатб");
        UnsanctionedAccessChecker unsanctionedAccessChecker = peopleCounterService.getUnsanctionedAccessChecker();
        while (!unsanctionedAccessChecker.isUpdatedInfo()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>("{\"counted\" : " + unsanctionedAccessChecker.getCountedPeoples() + ", " + "\"passed\" : " + unsanctionedAccessChecker.getApprovedPeoples() +"}", HttpStatus.OK);
    }

    @GetMapping("get_alert")
    public @ResponseBody ResponseEntity getAlert() {
        System.out.println("сосихуй");
        UnsanctionedAccessChecker unsanctionedAccessChecker = peopleCounterService.getUnsanctionedAccessChecker();
        while (!unsanctionedAccessChecker.isAlert()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(unsanctionedAccessChecker.getAlertandClear(), HttpStatus.OK);
    }
}
