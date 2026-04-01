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

    // LISTAR OU BUSCAR POR NOME
    public List<ClientDTO> listaclientes(String busca) {
        List<ClientModel> clientes;
        if (busca != null && !busca.isEmpty()) {
            clientes = clientRepository.findByNomeContainingIgnoreCase(busca);
        } else {
            clientes = clientRepository.findAll();
        }
        return clientes.stream()
                .map(clientMapper::map)
                .collect(Collectors.toList());
    }

    public ClientDTO listarclientesid(Long id) {
        Optional<ClientModel> clientid = clientRepository.findById(id);
        return clientid.map(clientMapper::map).orElse(null);
    }

    public void salvar(ClientDTO dto) {
        // Se for um NOVO cadastro (ID nulo), verificamos duplicatas
        if (dto.getId() == null) {
            if (clientRepository.existsByCPF(dto.getCPF())) {
                throw new RuntimeException("Este CPF já está cadastrado!");
            }
            if (clientRepository.existsByRG(dto.getRG())) {
                throw new RuntimeException("Este RG já está cadastrado!");
            }
            if (clientRepository.existsByEmail(dto.getEmail())) {
                throw new RuntimeException("Este E-mail já está em uso!");
            }
        }

        clientRepository.save(clientMapper.map(dto));
    }

    public void deletarclienteporid(Long id) {
        clientRepository.deleteById(id);
    }

}
