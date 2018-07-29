package br.com.helciodasilva.contatoapi.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "FUNCIONARIO")
@Data
public class Funcionario implements Serializable {

	@Id
	@SequenceGenerator(name = "seqFuncionario", sequenceName = "SEQ_FUNCIONARIO", allocationSize = 1)
	@GeneratedValue(generator = "seqFuncionario", strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Size(max = 100)
	@NotBlank
	@Column(name = "NOME")
	private String nome;

}
