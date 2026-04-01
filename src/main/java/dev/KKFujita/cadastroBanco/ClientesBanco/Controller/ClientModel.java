package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_cadastro")
@ToString
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column (name = "idade")
    private Integer idade;

    @Column (unique = true, nullable = false)
    private String CPF;

    @Column (unique = true, nullable = false)
    private String RG;

    @Column (unique = true, nullable = false)
    private String email;

    @Column (name = "numeroTelefone")
    private String numeroTelefone;







}
