package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_cadastro")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column (name = "idade")
    private Integer idade;

    @Column (unique = true)
    private Long CPF;

    @Column (unique = true)
    private Long RG;

    @Column (unique = true)
    private String email;

    @Column (name = "numeroTelefone")
    private Long numeroTelefone;







}
