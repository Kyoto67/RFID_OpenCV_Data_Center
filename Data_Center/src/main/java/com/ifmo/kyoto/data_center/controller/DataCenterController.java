package com.ifmo.kyoto.data_center.controller;

import com.ifmo.kyoto.data_center.entity.Card;
import com.ifmo.kyoto.data_center.service.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dc")
public class DataCenterController {

    @Autowired
    private CardsService cardsService;

    @PostMapping("card_check")
    public @ResponseBody ResponseEntity cardCheck(@RequestBody Card card) {
        if (cardsService.checkCardInWhiteList(card)) {
            return new ResponseEntity<>("{\"true\"}", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("{\"false\"}", HttpStatus.OK);
        }
    }
}
