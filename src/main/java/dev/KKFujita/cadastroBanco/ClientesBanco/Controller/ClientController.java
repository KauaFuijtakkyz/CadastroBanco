package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import dev.KKFujita.cadastroBanco.ClientesBanco.Service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

private final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasvindas(){
        return "Boas vindas";
    }
    @PostMapping("/adicionar")
    public ResponseEntity<String> Adicionarcliente(@RequestBody ClientDTO cliente) {
        ClientDTO clienteAdd = clientService.adicionarcliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente adicionado com sucesso: " + clienteAdd.getNome() + " (ID): " + clienteAdd.getId());
    }
    @GetMapping("/listar")
    public ResponseEntity<List<ClientDTO>> listaclientes() {
        List<ClientDTO> listarclientes = clientService.listaclientes();
        return ResponseEntity.ok(listarclientes);}

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarclientesid(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.listarclientesid(id);
        if (clientDTO != null){
            return ResponseEntity.ok(clientDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("não existe cliente com esse ID");
        }
    }
    @PatchMapping("/alterar/{id}")
    public ResponseEntity<String> alterarclienteporid(@PathVariable Long id,@RequestBody ClientDTO  clienteatualizado) {
        if (clientService.listarclientesid(id) != null){
            clientService.atualizarcliente(id, clienteatualizado);
            return ResponseEntity.ok("Cliente com id: " + id + " Alterado com sucesso");
        }else {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("o cliente com esse ID não existe");}
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletarclienteporid(@PathVariable Long id) {
        if (clientService.listarclientesid(id) != null) {
            clientService.deletarclienteporid(id);
            return ResponseEntity.ok("Cliente com ID: " + id + " Deletado com sucesso");
        }else {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("o cliente com esse ID não existe");
        }
    }
}
