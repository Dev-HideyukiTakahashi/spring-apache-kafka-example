package com.example.consumer.mensageria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.mensageria.model.BoletoEntity;

@Repository
public interface BoletoRepository extends JpaRepository<BoletoEntity, Long> {

}
