package com.dmitrybondarev.shop.web.controller.rest;

import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import com.dmitrybondarev.shop.service.api.StatisticService;
import com.dmitrybondarev.shop.util.logging.Loggable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticControllerRest {

    private StatisticService statisticService;

    public StatisticControllerRest(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Loggable
    @GetMapping("/api/product/list")
//    @Secured("ROLE_ADMIN")
    public List<ProductDtoRest> getTopFiveProductsDtoRest() {
        return statisticService.getTopFiveProductsDTORestByCashFlow();
    }
}
