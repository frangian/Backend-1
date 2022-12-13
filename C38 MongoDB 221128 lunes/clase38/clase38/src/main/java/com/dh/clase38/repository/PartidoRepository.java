package com.dh.clase38.repository;

import com.dh.clase38.entity.Partido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PartidoRepository extends MongoRepository<Partido,Integer> {
}
