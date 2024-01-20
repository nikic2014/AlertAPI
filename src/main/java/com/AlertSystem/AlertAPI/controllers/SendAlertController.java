package com.AlertSystem.AlertAPI.controllers;


import com.AlertSystem.AlertAPI.alerUtil.Alerter;
import com.AlertSystem.AlertAPI.alerUtil.EmailAlert;
import com.AlertSystem.AlertAPI.dto.AlertDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/alert")
public class SendAlertController {
    private final Alerter alerter;

    public SendAlertController(Alerter alerter) {
        this.alerter = alerter;
    }

    @PostMapping("/byRole/{role}")
    public ResponseEntity byRole(@PathVariable String role,
                                 @RequestBody AlertDTO testDTO) {
        try {
            alerter.sendAlert(testDTO.getIdProject(),
                    testDTO.getAlertType(),
                    testDTO.getMessage(),
                    testDTO.getAlertPath(),
                    role);
            return ResponseEntity.badRequest().body(Map.of("status", "success"));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(Map.of("status", "Bad request"));
        }
    }

    @PostMapping("/byId/{id}")
    public ResponseEntity byId(@PathVariable int id,
                                 @RequestBody AlertDTO testDTO) {
        try {
            alerter.sendAlert(testDTO.getIdProject(),
                    testDTO.getAlertType(),
                    testDTO.getMessage(),
                    testDTO.getAlertPath(),
                    id);
            return ResponseEntity.ok(Map.of("status", "success"));
        }
        catch (Exception exception){
            return ResponseEntity.badRequest().body(Map.of("status", "Bad request"));
        }
    }

//    @PostMapping("/firstTest")
//    public ResponseEntity testRequest(@RequestBody AlertDTO testDTO) {
//        System.out.println(testDTO.getMessage());
//        System.out.println(testDTO.getIdProject());
//        System.out.println(testDTO.getAlertType());
//
//        return ResponseEntity.ok("success");
//    }
//
//
//    @GetMapping("/secondTest")
//    public ResponseEntity testRequest2() throws Exception {
//        try {
//            smsAlert.sendSms("79085561046", "тестовый запрос");
//
//            return ResponseEntity.ok("success");
//        }
//        catch (Exception exception){
//            System.out.println(exception.getMessage());
//            return ResponseEntity.badRequest().build();
//        }
//    }
//
//    @PostMapping("/telegramTest")
//    public ResponseEntity testTelegram(@RequestBody AlertDTO testDTO) throws Exception {
//        if (telegramAlert.sendMessage(testDTO.getIdProject(),
//                testDTO.getMessage()))
//            return ResponseEntity.ok().body("Success");
//        else
//            return ResponseEntity.badRequest().body("Ошибка отправки сообщения");
//    }
//
//    @PostMapping("/emailTest")
//    public ResponseEntity testEmail(@RequestBody AlertDTO testDTO) throws Exception {
//        emailAlert.sendEmail("niki.c2000@yandex.ru", "Test subject", "RABIIIT");
//
//        return ResponseEntity.ok().body("success");
//    }
}
