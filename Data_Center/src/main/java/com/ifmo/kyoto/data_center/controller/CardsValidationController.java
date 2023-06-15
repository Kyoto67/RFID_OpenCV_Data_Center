package com.ifmo.kyoto.data_center.controller;

import com.ifmo.kyoto.data_center.entity.Card;
import com.ifmo.kyoto.data_center.entity.Log;
import com.ifmo.kyoto.data_center.service.CardsService;
import com.ifmo.kyoto.data_center.service.LogsService;
import com.ifmo.kyoto.data_center.service.PeopleCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@RestController
@RequestMapping("dc")
public class CardsValidationController {

    @Autowired
    private CardsService cardsService;

    @Autowired
    private LogsService logsService;
    
    @Autowired
    private PeopleCounterService peopleCounterService;


    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

    @PostMapping("card_check")
    public @ResponseBody ResponseEntity cardCheck(@RequestBody Card card) {
        if (cardsService.checkCardInWhiteList(card)) {
            logsService.addLog(new Log(card.getHash()));
            peopleCounterService.startPeopleCount();
            return new ResponseEntity<>("{\"whilelist\": true}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{\"whilelist\": false}", HttpStatus.OK);
        }
    }
}
