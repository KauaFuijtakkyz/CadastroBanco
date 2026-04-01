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

import jakarta.validation.Valid; // Importante!
import org.springframework.validation.BindingResult; // Importante!

@Controller
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/clientes/listar";
    }

    // LISTAR COM FILTRO DE BUSCA
    @GetMapping("/listar")
    public String listaclientes(@RequestParam(value = "busca", required = false) String busca, Model model) {
        List<ClientDTO> listarclientes = clientService.listaclientes(busca);
        model.addAttribute("cliente", listarclientes);
        model.addAttribute("buscaAtiva", busca); // Para manter o texto na barra de busca
        return "listarclientes";
    }

    @GetMapping("/cadastrar")
    public String exibirFormulario(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return "cadastrarcliente";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        ClientDTO clienteParaEditar = clientService.listarclientesid(id);
        if (clienteParaEditar != null) {
            model.addAttribute("clientDTO", clienteParaEditar);
            return "editarcliente";
        }
        return "redirect:/clientes/listar";
    }

    // SALVAR COM VALIDAÇÃO
    @PostMapping("/salvar")
    public String salvarCliente(@Valid @ModelAttribute("clientDTO") ClientDTO dto,
                                BindingResult result,
                                RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return (dto.getId() != null) ? "editarcliente" : "cadastrarcliente";
        }

        try {
            clientService.salvar(dto);
            attributes.addFlashAttribute("mensagem", "Operação realizada com sucesso!");
        } catch (RuntimeException e) {
            // Se o Service lançar o erro de duplicata, capturamos aqui
            attributes.addFlashAttribute("erroDuplicata", e.getMessage());
            return (dto.getId() != null) ? "redirect:/clientes/editar/" + dto.getId() : "redirect:/clientes/cadastrar";
        }

        return "redirect:/clientes/listar";
    }

    @GetMapping("/delete/{id}")
    public String deletarclienteporid(@PathVariable Long id, RedirectAttributes attributes) {
        if (clientService.listarclientesid(id) != null) {
            clientService.deletarclienteporid(id);
            attributes.addFlashAttribute("mensagem", "Cliente removido com sucesso!");
        }
        return "redirect:/clientes/listar";
    }
}


