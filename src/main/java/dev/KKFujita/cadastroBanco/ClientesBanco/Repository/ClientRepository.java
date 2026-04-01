package dev.KKFujita.cadastroBanco.ClientesBanco.Repository;

import dev.KKFujita.cadastroBanco.ClientesBanco.Controller.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    boolean existsByCPF(String cpf);
    boolean existsByRG(String rg);
    boolean existsByEmail(String email);

    // Seu método de busca por nome continua aqui
    List<ClientModel> findByNomeContainingIgnoreCase(String nome);
}


