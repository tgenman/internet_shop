package com.dmitrybondarev.shop.service.api;

import com.dmitrybondarev.shop.model.dto.ProductDto;
import com.dmitrybondarev.shop.model.dto.rest.ProductDtoRest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticService {

    List<ProductDto> getTopFiveProductsDTOByCashFlow();

    List<ProductDtoRest> getTopFiveProductsDTORestByCashFlow();
}
