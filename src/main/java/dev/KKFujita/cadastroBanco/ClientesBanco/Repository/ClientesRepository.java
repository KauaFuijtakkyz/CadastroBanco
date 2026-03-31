package dev.KKFujita.cadastroBanco.ClientesBanco.Repository;

import dev.KKFujita.cadastroBanco.ClientesBanco.Controller.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<ClientModel, Long> {
}
