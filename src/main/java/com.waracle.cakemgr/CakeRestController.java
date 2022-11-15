package com.waracle.cakemgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static java.lang.String.format;

@RestController
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class CakeRestController {

    @Autowired
    private CakeRepository cakeRepository;

    @RequestMapping(value = "/cakes", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<Map<String, Iterable<CakeDTO>>> getCake() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("cakes",cakeRepository.findAll()));
    }

    @RequestMapping(value = "/cakes", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<?> newCake(@RequestBody CakeDTO cake) {
        CakeDTO saved = cakeRepository.save(cake);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, format("/cake/%d", saved.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
