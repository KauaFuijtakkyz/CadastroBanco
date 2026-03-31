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

    @Column (name = "nome")
    private String nome;

    @Column (name = "idade")
    private int idade;

    @Column (unique = true)
    private int CPF;

    @Column (unique = true)
    private int RG;

    @Column (unique = true)
    private String email;

    @Column (name = "numeroTefelone")
    private int numeroTelefone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;





}
