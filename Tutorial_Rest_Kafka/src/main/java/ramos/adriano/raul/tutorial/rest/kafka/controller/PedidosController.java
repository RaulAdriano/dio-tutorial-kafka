package ramos.adriano.raul.tutorial.rest.kafka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ramos.adriano.raul.tutorial.rest.kafka.data.PedidoData;
import ramos.adriano.raul.tutorial.rest.kafka.service.RegistraEventoService;

@RestController
@RequiredArgsConstructor
public class PedidosController {

    private final RegistraEventoService registraEventoService;

    @PostMapping(path = "/api/salva-pedido")
    public ResponseEntity<String> salvarPedido(@RequestBody PedidoData pedido){
        registraEventoService.adicionarEvento("SalvarPedido", pedido);
        return ResponseEntity.ok("Sucesso");
    }
}
