package com.waracle.cakemgr;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CakeRepository extends PagingAndSortingRepository<CakeDTO, Integer> {
}
