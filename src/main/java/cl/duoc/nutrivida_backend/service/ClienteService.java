package cl.duoc.nutrivida_backend.service;

import cl.duoc.nutrivida_backend.model.Cliente;
import cl.duoc.nutrivida_backend.repository.ClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Inyección del repositorio por constructor
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Obtener todos los registros
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    // Buscar por ID (devuelve null si no existe)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // Guardar / crear nuevo registro
    public Cliente guardar(Cliente cliente) {
        cliente.setId(null); // asegura que se cree uno nuevo
        return clienteRepository.save(cliente);
    }

    // Actualizar registro existente (devuelve null si no existe)
    public Cliente actualizar(Long id, Cliente datos) {
        Cliente existente = buscarPorId(id);
        if (existente == null) {
            return null;
        }
        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setRut(datos.getRut());
        existente.setFechaNacimiento(datos.getFechaNacimiento());
        existente.setTelefono(datos.getTelefono());
        existente.setEmail(datos.getEmail());
        existente.setPeso(datos.getPeso());
        existente.setEstatura(datos.getEstatura());
        existente.setObjetivo(datos.getObjetivo());
        return clienteRepository.save(existente);
    }

    // Eliminar por ID (devuelve false si no existe)
    public boolean eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            return false;
        }
        clienteRepository.deleteById(id);
        return true;
    }
}