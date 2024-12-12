package com.example.messageria.boleto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.messageria.boleto.model.BoletoEntity;

@Repository
public interface BoletoRepository extends JpaRepository<BoletoEntity, Long> {

  Optional<BoletoEntity> findByCodigoBarras(String codigoBarras);
}
