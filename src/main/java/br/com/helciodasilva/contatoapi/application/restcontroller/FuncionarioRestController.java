package br.com.helciodasilva.contatoapi.application.restcontroller;

import static org.springframework.util.StringUtils.isEmpty;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.helciodasilva.contatoapi.domain.model.Funcionario;
import br.com.helciodasilva.contatoapi.infra.persistence.repository.FuncionarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/funcionario")
@Api(tags = "Funcionario")
public class FuncionarioRestController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@ApiOperation(value = "Lista os contatos")
	@GetMapping
	public ResponseEntity<List<Funcionario>> find(@RequestParam(value = "nome", required = false) String nome) {
		List<Funcionario> funcionarios;

		if (!isEmpty(nome)) {
			funcionarios = funcionarioRepository.findByNomeIgnoreCaseContaining(nome);
		} else {
			funcionarios = funcionarioRepository.findAll();
		}

		return ResponseEntity.status(HttpStatus.OK).body(funcionarios);

	}
	
	@ApiOperation(value = "Exibe informações sobre um funcionário")
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> findOne(@PathVariable(value = "id") Long id) {
		Funcionario funcionario = funcionarioRepository.findOne(id);
		
		if (!funcionarioRepository.exists(id)) {
			return ResponseEntity.notFound().build();
		}
		
	    return ResponseEntity.status(HttpStatus.OK).body(funcionario);
	}
	
	@ApiOperation(value = "Salva um novo funcionário")
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody Funcionario funcionario) {
		funcionario = funcionarioRepository.save(funcionario);
		URI location = createLocation(funcionario);
		return ResponseEntity.created(location).build();
	}
	
	@ApiOperation(value = "Edita um funcionário")
	@PutMapping("{id}")
	public ResponseEntity<Void> save(@PathVariable(value = "id") Long id, @Valid @RequestBody Funcionario funcionario) {
		funcionario.setId(id);
		boolean exists = funcionarioRepository.exists(id);
		funcionarioRepository.save(funcionario);
		URI location = createLocation(funcionario);

		if (exists) {
			return ResponseEntity.ok().location(location).build();
		} else {
			return ResponseEntity.created(location).build();
		}

	}

	@ApiOperation(value = "Remove um funcnionário")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {

		if (!funcionarioRepository.exists(id)) {
			return ResponseEntity.notFound().build();
		}

		funcionarioRepository.delete(id);
		return ResponseEntity.noContent().build();
	}

	private URI createLocation(Funcionario funcionario) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				  						  .path("/{id}")
				  						  .buildAndExpand(funcionario.getId())
				  						  .toUri();
	}
	
}
