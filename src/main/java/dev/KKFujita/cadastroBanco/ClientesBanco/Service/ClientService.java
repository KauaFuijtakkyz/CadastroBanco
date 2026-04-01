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

    // Este é o método que o seu formulário usa!
    public void salvar(ClientDTO dto) {
        // 1. Converte DTO para Model (Entidade do banco)
        ClientModel cliente = clientMapper.map(dto);
        // 2. Salva no banco de dados através do Repository
        clientRepository.save(cliente);
    }

    // Método usado para APIs ou chamadas internas
    public ClientDTO adicionarcliente (ClientDTO clientDTO) {
        ClientModel cliente = clientMapper.map(clientDTO);
        cliente = clientRepository.save(cliente);
        return clientMapper.map(cliente);
    }

    public ClientDTO atualizarcliente(Long id, ClientDTO clientDTO) {
        Optional<ClientModel> clienteExistente = clientRepository.findById(id);

        if (clienteExistente.isPresent()) {
            ClientModel clienteBanco = clienteExistente.get();

            if (clientDTO.getNome() != null) clienteBanco.setNome(clientDTO.getNome());
            if (clientDTO.getEmail() != null) clienteBanco.setEmail(clientDTO.getEmail());
            if (clientDTO.getIdade() != null) clienteBanco.setIdade(clientDTO.getIdade());
            if (clientDTO.getCPF() != null) clienteBanco.setCPF(clientDTO.getCPF());
            if (clientDTO.getRG() != null) clienteBanco.setRG(clientDTO.getRG());
            if (clientDTO.getNumeroTelefone() != null) clienteBanco.setNumeroTelefone(clientDTO.getNumeroTelefone());

            ClientModel ninjasalvo = clientRepository.save(clienteBanco);
            return clientMapper.map(ninjasalvo);
        }
        return null;
    }

    public void deletarclienteporid(Long id){
        clientRepository.deleteById(id);
    }
}
