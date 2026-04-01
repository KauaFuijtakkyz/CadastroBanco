package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import dev.KKFujita.cadastroBanco.ClientesBanco.Service.ClientService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clientes/ui")
@AllArgsConstructor
public class ClientControllerUI {
    private final ClientService clientService;


    @GetMapping("/listar")
    public String listaclientes(Model model) {
        List<ClientDTO> listaclientes = clientService.listaclientes();
        model.addAttribute("cliente", listaclientes);
        return "listarclientes";
    }
    // 1. Método para mostrar o formulário
    @GetMapping("/cadastrar")
    public String exibirFormulario(Model model) {
        // É obrigatório enviar um objeto vazio para o th:object do HTML não dar erro
        model.addAttribute("clientDTO", new ClientDTO());
        return "cadastrarcliente";
    }

    // 2. Método para salvar (POST)
    @PostMapping("/salvar")
    public String salvarCliente(@ModelAttribute("clientDTO") ClientDTO dto, RedirectAttributes attributes) {
        // Aqui você chama o seu service para salvar no banco
        clientService.salvar(dto);

        // Mensagem de sucesso que aparecerá na lista
        attributes.addFlashAttribute("mensagem", "Cliente " + dto.getNome() + " cadastrado com sucesso!");

        return "redirect:/clientes/listar";
    }

}
