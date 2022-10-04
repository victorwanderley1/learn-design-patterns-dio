package wanderley.victor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wanderley.victor.model.Cliente;
import wanderley.victor.model.Endereco;
import wanderley.victor.repository.ClienteRepository;
import wanderley.victor.repository.EnderecoRepository;
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Integer id, Cliente cliente) {
		if(clienteRepository.existsById(id)) {
			salvarClienteComCep(cliente);
		}
	}

	@Override
	public void deletar(Integer id) {
		clienteRepository.deleteById(id);
	}
	
	private void salvarClienteComCep(Cliente cliente) {
		cliente.setEndereco(enderecoRepository.findById(cliente.getEndereco().getCep()).orElseGet(() -> {
			return inserirEndereco(cliente.getEndereco().getCep());
		}));
		clienteRepository.save(cliente);
	}
	
	private Endereco inserirEndereco(String cep) {
		return enderecoRepository.save(viaCepService.consultarCep(cep));
	}

}
