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

import br.com.helciodasilva.contatoapi.domain.model.Contato;
import br.com.helciodasilva.contatoapi.domain.model.FuncionarioExistsDTO;
import br.com.helciodasilva.contatoapi.infra.persistence.repository.ContatoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/contato")
@Api(tags = "Contato")
public class ContatoRestController {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@ApiOperation(value = "Lista os contatos")
	@GetMapping
	public ResponseEntity<List<Contato>> find(@RequestParam(value = "descricao", required = false) String descricao) {

		List<Contato> contatos; 
		if (!isEmpty(descricao)) {
			contatos = contatoRepository.findByDescricaoIgnoreCaseContaining(descricao);	
		} else {
			contatos = contatoRepository.findAll();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(contatos);
	}

	@ApiOperation(value = "Exibe informações sobre um contato")
	@GetMapping("/{id}")
	public ResponseEntity<Contato> findOne(@PathVariable(value = "id") Long id) {
		Contato contato = contatoRepository.findOne(id);

		if (!contatoRepository.exists(id)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.OK).body(contato);
	}

	@ApiOperation(value = "Lista os contatos de um funcionário")
	@GetMapping("/funcionario/{id}")
	public ResponseEntity<List<Contato>> findByFuncionario(@PathVariable(value = "id") Long id) {
		List<Contato> contatos = contatoRepository.findByFuncionarioId(id);
		return ResponseEntity.status(HttpStatus.OK).body(contatos);
	}

	@ApiOperation(value = "Informa se um funcionário contém contato")
	@GetMapping("/funcionario/{id}/exists")
	public ResponseEntity<FuncionarioExistsDTO> existsByFuncionario(@PathVariable(value = "id") Long id) {
		boolean exists = contatoRepository.existsByFuncionarioId(id);
		return ResponseEntity.status(HttpStatus.OK).body(new FuncionarioExistsDTO(exists));
	}

	@ApiOperation(value = "Salva um novo contato")
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody Contato contato) {
		contato = contatoRepository.save(contato);
		URI location = createLocation(contato);
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Edita um contato")
	@PutMapping("{id}")
	public ResponseEntity<Void> save(@PathVariable(value = "id") Long id, @Valid @RequestBody Contato contato) {
		contato.setId(id);
		boolean exists = contatoRepository.exists(id);
		contatoRepository.save(contato);
		URI location = createLocation(contato);

		if (exists) {
			return ResponseEntity.ok().location(location).build();
		} else {
			return ResponseEntity.created(location).build();
		}

	}

	@ApiOperation(value = "Remove um contato")
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {

		if (!contatoRepository.exists(id)) {
			return ResponseEntity.notFound().build();
		}

		contatoRepository.delete(id);
		return ResponseEntity.noContent().build();
	}

	private URI createLocation(Contato contato) {
		return ServletUriComponentsBuilder.fromCurrentRequest()
				  						  .path("/{id}")
				  						  .buildAndExpand(contato.getId())
				  						  .toUri();
	}
	
}
