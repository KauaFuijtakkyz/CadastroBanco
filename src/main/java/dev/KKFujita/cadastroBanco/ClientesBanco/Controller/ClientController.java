package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import dev.KKFujita.cadastroBanco.ClientesBanco.Service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Exibe a tabela de clientes
    @GetMapping("/listar")
    public String listaclientes(Model model) {
        List<ClientDTO> listarclientes = clientService.listaclientes();
        model.addAttribute("cliente", listarclientes);
        return "listarclientes";
    }

    // Exibe o formulário de cadastro
    @GetMapping("/cadastrar")
    public String exibirFormulario(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return "cadastrarcliente";
    }

    // Salva o cliente vindo do formulário
    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute("clientDTO") ClientDTO dto, RedirectAttributes attributes) {
        clientService.salvar(dto); // Agora este método funciona!
        attributes.addFlashAttribute("mensagem", "Cliente " + dto.getNome() + " cadastrado com sucesso!");
        return "redirect:/clientes/listar";
    }

    // Deleta o cliente e redireciona
    @GetMapping("/delete/{id}")
    public String deletarclienteporid(@PathVariable Long id, RedirectAttributes attributes) {
        if (clientService.listarclientesid(id) != null) {
            clientService.deletarclienteporid(id);
            attributes.addFlashAttribute("mensagem", "Cliente removido com sucesso!");
        }
        return "redirect:/clientes/listar";
    }

    // Mantido apenas para fins de teste/API se necessário
    @GetMapping("/listar/{id}")
    @ResponseBody // Adicionado para retornar JSON se você acessar essa URL direta
    public ClientDTO listarclientesid(@PathVariable Long id) {
        return clientService.listarclientesid(id);
    }
}
