package com.dgu.wantToGraduate.domain.brand.controller;

import com.dgu.wantToGraduate.domain.brand.service.BrandService;
import com.dgu.wantToGraduate.domain.category.BrandCategory;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Slf4j
public class BrandController {

    private final BrandService brandService;
    @GetMapping("/list/all")
    public ResponseEntity<?> getBrandList(@RequestParam("category") String category){

        return ResponseEntity.ok(brandService.searchAll(category));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getBrand(@RequestParam("search") String brandName){

        return ResponseEntity.ok(brandService.search(brandName));
    }

}
