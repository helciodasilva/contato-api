package br.com.helciodasilva.contatoapi.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.helciodasilva.contatoapi.domain.model.Funcionario;

@RepositoryRestResource(collectionResourceRel = "funcionario", path = "funcionario")
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
