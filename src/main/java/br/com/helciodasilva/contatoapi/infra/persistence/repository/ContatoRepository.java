package br.com.helciodasilva.contatoapi.infra.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.helciodasilva.contatoapi.domain.model.Contato;

@RepositoryRestResource(collectionResourceRel = "contato", path = "contato")
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
