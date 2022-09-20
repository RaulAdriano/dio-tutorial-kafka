package ramos.adriano.raul.tutorial.microservico.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ramos.adriano.raul.tutorial.microservico.kafka.data.PedidoData;

@Log4j2
@Service
public class SalvarPedidoService {

    @KafkaListener(topics = "SalvarPedido", groupId = "MicroServicoSalvarPedido")
    private void executar(ConsumerRecord<String,String> record){
        log.info("Key = {}", record.key());
        log.info("Cabeçalho = {}", record.headers());
        log.info("Partição = {}", record.partition());

        String dados = record.value();
        ObjectMapper mapper  = new ObjectMapper();
        PedidoData  pedido = null;

        try {
            pedido = mapper.readValue(dados , PedidoData.class);
        } catch (JsonProcessingException e) {
            log.error("Falha ao converter evento [ dados={}]", dados, e );
            return;
        }

        log.info("Evento recebido  = {}", pedido);

        //gravar no BD
        //responder para a fila que o pedido foi salvo
    }
}
