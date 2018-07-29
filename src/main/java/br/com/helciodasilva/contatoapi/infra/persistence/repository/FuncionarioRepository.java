package br.com.helciodasilva.contatoapi.infra.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.helciodasilva.contatoapi.domain.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	List<Funcionario> findByNomeIgnoreCaseContaining(String nome);

}
