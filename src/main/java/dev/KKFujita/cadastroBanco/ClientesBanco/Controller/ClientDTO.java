package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private Long id;
    private String nome;
    private int idade;
    private String email;
    private int RG;
    private int CPF;
    private int numeroTelefone;
}
