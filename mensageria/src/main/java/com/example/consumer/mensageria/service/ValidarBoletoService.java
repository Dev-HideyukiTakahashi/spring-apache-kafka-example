package com.example.consumer.mensageria.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.consumer.mensageria.model.BoletoEntity;
import com.example.consumer.mensageria.model.enums.SituacaoBoleto;
import com.example.consumer.mensageria.repository.BoletoRepository;

@Service
public class ValidarBoletoService {

  private final BoletoRepository boletoRepository;

  public ValidarBoletoService(BoletoRepository boletoRepository) {
    this.boletoRepository = boletoRepository;
  }

  public void validar(BoletoEntity boletoEntity) {
    var codigo = Integer.parseInt(boletoEntity.getCodigoBarras().substring(0, 1));
    if (codigo % 2 == 0) {
      complementarBoletoErro(boletoEntity);
      boletoRepository.save(boletoEntity);
      // notificando
    } else {
      complementarBoletoSucesso(boletoEntity);
      boletoRepository.save(boletoEntity);
      // notificando

      // seguindo com pagamento
    }
  }

  private void complementarBoletoErro(BoletoEntity boleto) {
    boleto.setDataCriacao(LocalDateTime.now());
    boleto.setDataAtualizacao(LocalDateTime.now());
    boleto.setSituacaoBoleto(SituacaoBoleto.ERRO_VALIDACAO);
  }

  private void complementarBoletoSucesso(BoletoEntity boleto) {
    boleto.setDataCriacao(LocalDateTime.now());
    boleto.setDataAtualizacao(LocalDateTime.now());
    boleto.setSituacaoBoleto(SituacaoBoleto.VALIDADO);
  }
}
