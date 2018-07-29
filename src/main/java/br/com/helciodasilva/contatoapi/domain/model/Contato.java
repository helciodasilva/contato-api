package br.com.helciodasilva.contatoapi.domain.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "CONTATO")
@Data
public class Contato implements Serializable{

	@Id
	@SequenceGenerator(name = "seqContato", sequenceName = "SEQ_CONTATO", allocationSize = 1)
	@GeneratedValue(generator = "seqContato", strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Enumerated
	@Column(name = "TIPO_CONTATO")
	private TipoContatoEnum tipoContato;

	@NotBlank
	@Size(max = 50)
	@Basic(optional = false)
	@Column(name = "DESCRICAO", unique = true)
	private String descricao;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ID_FUNCIONARIO", referencedColumnName = "ID")
	private Funcionario funcionario;

}
