package com.waracle.cakemgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static java.lang.String.format;

@RestController
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class CakeRestController {

    @Autowired
    private CakeRepository cakeRepository;

    @RequestMapping(value = "/cakes", method = RequestMethod.GET, produces = "application/json")
    Iterable<CakeDTO> getCakes() {
        return cakeRepository.findAll();
    }

    @RequestMapping(value = "/cakes", method = RequestMethod.POST, produces = "application/json")
    ResponseEntity<?> newCake(@RequestBody CakeDTO cake) {
        CakeDTO saved = cakeRepository.save(cake);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.LOCATION, format("/cake/%d", saved.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cake/{id}", method = RequestMethod.GET, produces = "application/json")
    Optional<CakeDTO> getCake(@PathVariable Integer id) {
        return cakeRepository.findById(id);
    }
}
