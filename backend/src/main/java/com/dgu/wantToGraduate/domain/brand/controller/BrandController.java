package com.dgu.wantToGraduate.domain.brand.controller;

import com.dgu.wantToGraduate.domain.brand.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Slf4j
public class BrandController {

    private final BrandService brandService;

    @CrossOrigin(origins = "*", exposedHeaders = "Authorization")
    @GetMapping("/list/all")
    public ResponseEntity<?> getBrandList(@RequestParam("category") String category){
        log.info("getBrandList 진입");
        return ResponseEntity.ok(brandService.searchAll(category));
    }

    @CrossOrigin(origins = "*", exposedHeaders = "Authorization")
    @GetMapping("/list")
    public ResponseEntity<?> getBrand(@RequestParam("search") String brandName){

        return ResponseEntity.ok(brandService.search(brandName));
    }

}
