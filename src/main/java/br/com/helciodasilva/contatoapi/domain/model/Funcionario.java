package br.com.helciodasilva.contatoapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "FUNCIONARIO")
@Data
public class Funcionario {

	@Id
	@SequenceGenerator(name = "seqContato", sequenceName = "SEQ_CONTATO", allocationSize = 1)
	@GeneratedValue(generator = "seqContato", strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@NotBlank
	@Column(name = "NOME")
	private String nome;

}
