package com.example.consumer.mensageria.model;

import java.time.LocalDateTime;

import com.example.consumer.mensageria.model.enums.SituacaoBoleto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoletoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String codigoBarras;
  private SituacaoBoleto situacaoBoleto;
  private LocalDateTime dataCriacao;
  private LocalDateTime dataAtualizacao;
}
