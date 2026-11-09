package cl.duoc.nutrivida_backend.service;

import cl.duoc.nutrivida_backend.model.Cliente;
import cl.duoc.nutrivida_backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCliente(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Integer id, Cliente cliente) {
        Optional<Cliente> existe = getCliente(id);

        if (existe.isEmpty()) {
            throw new RuntimeException("No encontrado");
        } else {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        }
    }

    public void deleteCliente(Integer id) {
        if (clienteRepository.existsById(id))
            clienteRepository.deleteById(id);
        else
            throw new RuntimeException("No encontrado");
    }
}