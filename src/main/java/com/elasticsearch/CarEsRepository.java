package com.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CarEsRepository extends ElasticsearchRepository<Car, Long> {

	Iterable<Car> findByMakeIgnoringCase(String make);

}
