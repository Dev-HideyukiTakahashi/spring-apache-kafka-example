# Projeto do curso de mensageria

Instrutor: Luan Rocha.

### Tecnologias utilizadas

- Java
- Spring Boot
- Docker
- Apache Kafka
- Apache Avro
- Control Center
- Schema Registry
- Banco de dados H2

### Arquitetura do projeto

O projeto consiste em aplicações responsáveis por recepcionar, validar e pagar um boleto,
onde a comunicação entre sistema acontece por mensageria.
![Alt Text](./imagem/arquitetura.gif)

---

Casos de uso - Mensageria

Usos mais comuns:

- **Integração entre sistemas de software:** diferentes fornecedores ou plataformas, isso permite sistemas que não são diretamente compatíveis se comuniquem.
- **Notificações:** por exemplo, notificar usuários sobre novos e-mails, atualizações de status ou eventos importantes.
- **Processamento de pedidos:** por exemplo, comércio eletrônico e aplicativos de gerenciamento de pedidos, a mensageria é utilizada para transmitir pedidos, atualizações de status e informações de estoques de diferentes partes do sistema.
- **Fila de tarefas:** pode ser usada para criar filas de tarefas em que as mensagens representam unidades de trabalho a serem processadas, isso é útil em aplicativos de processamento em lote e na distribuição de cargas de trabalho.
- **Sistemas de pagamento:** em sistemas financeiros, a mensageria é usada para transmitir informações, como autorizações de pagamento e registro de transações.
