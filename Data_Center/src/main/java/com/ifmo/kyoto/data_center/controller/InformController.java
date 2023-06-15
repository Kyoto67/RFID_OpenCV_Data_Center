package com.ifmo.kyoto.data_center.controller;

import com.ifmo.kyoto.data_center.service.PeopleCounterService;
import com.ifmo.kyoto.data_center.service.UnsanctionedAccessChecker;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody String getPeoplesInfo() {
        UnsanctionedAccessChecker unsanctionedAccessChecker = peopleCounterService.getUnsanctionedAccessChecker();
        while (!unsanctionedAccessChecker.isUpdatedInfo()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "{\n" +
                "\t\"counted\" : " + unsanctionedAccessChecker.getCountedPeoples() + ",\n" +
                "\t\"passed\" : " + unsanctionedAccessChecker.getApprovedPeoples() + "\n" +
                "}";
    }

    @GetMapping("get_alert")
    public @ResponseBody boolean getAlert() {
        UnsanctionedAccessChecker unsanctionedAccessChecker = peopleCounterService.getUnsanctionedAccessChecker();
        while (!unsanctionedAccessChecker.isAlert()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return unsanctionedAccessChecker.getAlertandClear();
    }
}
