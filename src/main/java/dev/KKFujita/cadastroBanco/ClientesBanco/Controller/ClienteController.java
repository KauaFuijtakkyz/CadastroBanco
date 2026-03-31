package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ClienteController {

    @GetMapping("/boasvindas")
    public String BoasVindas() {
        return "Boas vindas.";
    }
}
