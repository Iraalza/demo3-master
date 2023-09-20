package com.example.demo3.controllers;

import com.example.demo3.models.Change;
import com.example.demo3.models.Risk;
import com.example.demo3.repositories.RiskRepository;
import com.example.demo3.service.RiskService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class RiskController {
    private final RiskRepository repository;
    private final RiskService riskService;
    public RiskController(RiskRepository repository, RiskService riskService){
        this.repository = repository;
        this.riskService = riskService;
    }
    @GetMapping("/risks")
    Iterable<Risk> all(){return repository.findAll();}
    @GetMapping("/risk/{id}")
    public Risk getRisk (@PathVariable int id){
        return riskService.getRisk(id);
    }
    @PostMapping("/risk/createupdate")
    Risk newRisk(@RequestBody Risk newRisk){
        return riskService.createRisk(newRisk);
    }

    @PutMapping("/risk/naocenkyianaliz/{id}")
    public Object naOcenkyIAnaliz(@PathVariable int id){
        Risk res = riskService.getRisk(id);
        String status = res.getRisk_status();
        if (Objects.equals(status, "1. Новый") || Objects.equals(status, "4. На контроле" )){
            riskService.naOcenkyIAnaliz(id);
            return riskService.getRisk(id);
        }
        else{
            riskService.getRisk(id);
            return riskService.error();
        }
    }
    @PutMapping("/risk/minimizatiya/{id}")
    public Object minimizatiya(@PathVariable int id){
        Risk res = riskService.getRisk(id);
        String status = res.getRisk_status();
        if (Objects.equals(status, "2. На оценку и анализ")){
            riskService.minimizatiya(id);
            return riskService.getRisk(id);
        }
        else{
            riskService.getRisk(id);
            return riskService.error();
        }
    }
    @PutMapping("/risk/naKontrole/{id}")
    public Object naKontrole(@PathVariable int id){
        Risk res = riskService.getRisk(id);
        String status = res.getRisk_status();
        if (Objects.equals(status, "2. На оценку и анализ") || Objects.equals(status, "3. Минимизация" )){
            riskService.naKontrole(id);
            return riskService.getRisk(id);
        }
        else{
            riskService.getRisk(id);
            return riskService.error();
        }
    }
    @PutMapping("/risk/zakryt/{id}")
    public Object zakryt(@PathVariable int id){
        Risk res = riskService.getRisk(id);
        String status = res.getRisk_status();
        if (Objects.equals(status, "1. Новый") || Objects.equals(status, "4. На контроле" )){
            riskService.zakryt(id);
            return riskService.getRisk(id);
        }
        else{
            riskService.getRisk(id);
            return riskService.error();
        }
    }

//    @PostMapping("/test")
//    Risk newnewRisk(@RequestBody Risk risk){
//        risk.getRisk_tipe();
//    }
}
