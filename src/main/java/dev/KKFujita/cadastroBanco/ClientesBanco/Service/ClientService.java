package dev.KKFujita.cadastroBanco.ClientesBanco.Service;

import dev.KKFujita.cadastroBanco.ClientesBanco.Controller.ClientDTO;
import dev.KKFujita.cadastroBanco.ClientesBanco.Controller.ClientMapper;
import dev.KKFujita.cadastroBanco.ClientesBanco.Controller.ClientModel;
import dev.KKFujita.cadastroBanco.ClientesBanco.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDTO> listaclientes(){
        List<ClientModel> clientes = clientRepository.findAll();
        return clientes.stream()
                .map(clientMapper::map)
                .collect(Collectors.toList());
    }

    public ClientDTO listarclientesid(Long id){
        Optional<ClientModel> clientid = clientRepository.findById(id);
        return clientid.map(clientMapper::map).orElse(null);
    }

    public ClientDTO adicionarcliente (ClientDTO clientDTO) {
        ClientModel ninja = clientMapper.map(clientDTO);
        ninja = clientRepository.save(ninja);
        return clientMapper.map(ninja);
    }

    public ClientDTO atualizarcliente(Long id, ClientDTO clientDTO) {
        // 1. Buscamos o cliente que já está no banco
        Optional<ClientModel> clienteExistente = clientRepository.findById(id);

        if (clienteExistente.isPresent()) {
            ClientModel clienteBanco = clienteExistente.get();

            // 2. Só atualizamos o que não veio nulo no JSON
            if (clientDTO.getNome() != null) {
                clienteBanco.setNome(clientDTO.getNome());
            }
            if (clientDTO.getEmail() != null) {
                clienteBanco.setEmail(clientDTO.getEmail());
            }
            if (clientDTO.getIdade() != null) {
                clienteBanco.setIdade(clientDTO.getIdade());
            }
            if (clientDTO.getCPF() != null) {
                clienteBanco.setCPF(clientDTO.getCPF());
            }
            if (clientDTO.getRG() != null) {
                clienteBanco.setRG(clientDTO.getRG());
            }
            if (clientDTO.getNumeroTelefone() != null) {
                clienteBanco.setNumeroTelefone(clientDTO.getNumeroTelefone());
            }

            // 3. Salvamos o objeto do banco (com os dados antigos preservados)
            ClientModel ninjasalvo = clientRepository.save(clienteBanco);
            return clientMapper.map(ninjasalvo);
        }

        return null; // Se não encontrar o ID
    }

    public void deletarclienteporid(Long id){
        clientRepository.deleteById(id);
    }

}
