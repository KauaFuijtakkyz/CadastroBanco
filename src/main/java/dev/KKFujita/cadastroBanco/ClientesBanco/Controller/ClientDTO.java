package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {

    private Long id;
    private String nome;
    private Integer idade;
    private String email;
    private Long RG;
    private Long CPF;
    private Long numeroTelefone;
}
