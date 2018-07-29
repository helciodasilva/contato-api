package br.com.helciodasilva.contatoapi.infra.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helciodasilva.contatoapi.domain.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

	boolean existsByFuncionarioId(Long id);
	
	List<Contato> findByFuncionarioId(Long id);

	List<Contato> findByDescricaoIgnoreCaseContaining(String descricao);

}
