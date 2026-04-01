package dev.KKFujita.cadastroBanco.ClientesBanco.Controller;

import dev.KKFujita.cadastroBanco.ClientesBanco.Controller.ClientModel;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientModel map(ClientDTO clientDTO) {
        ClientModel clientModel = new ClientModel();
        clientModel.setId(clientDTO.getId());
        clientModel.setNome(clientDTO.getNome());
        clientModel.setRG(clientDTO.getRG());
        clientModel.setCPF(clientDTO.getCPF());
        clientModel.setEmail(clientDTO.getEmail());
        clientModel.setIdade(clientDTO.getIdade());
        clientModel.setNumeroTelefone(clientDTO.getNumeroTelefone());

        return clientModel;
    }

    public ClientDTO map(ClientModel clientModel) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(clientModel.getId());
        clientDTO.setNome(clientModel.getNome());
        clientDTO.setRG(clientModel.getRG());
        clientDTO.setCPF(clientModel.getCPF());
        clientDTO.setEmail(clientModel.getEmail());
        clientDTO.setIdade(clientModel.getIdade());
        clientDTO.setNumeroTelefone(clientModel.getNumeroTelefone());

        return clientDTO;
    }
}
