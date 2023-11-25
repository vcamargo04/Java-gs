package br.com.fiap.rapidmed.model.entity;

import br.com.fiap.rapidmed.model.excecao.DadosInvalidosException;

public class Medico {

private long Id;
private String nomeMedico;
private String numeroCrm;
private String especialidade;

public Medico(long id, String nomeMedico, String numeroCrm, String especialidade)throws DadosInvalidosException {
	
	if(id <1) {
		throw new DadosInvalidosException("ID DO MEDICO NÃO INFORMADO");
	}
	if(nomeMedico == null || nomeMedico.isBlank()) {
		throw new DadosInvalidosException("NOME DO MEDICO NÃO INFORMADO");
	}
	if(numeroCrm == null || numeroCrm.isBlank()) {
		throw new DadosInvalidosException("NUMERO DE CRM NÃO INFORMADO");
	}
	if(especialidade == null || especialidade.isBlank()) {
		throw new DadosInvalidosException("ESPECIALIDADE NÃO INFORMADO");
	}
	
	Id = id;
	this.nomeMedico = nomeMedico;
	this.numeroCrm = numeroCrm;
	this.especialidade = especialidade;
}

public Medico() {
	// TODO Auto-generated constructor stub
}
public long getId() {
	return Id;
}
public void setId(long id) {
	Id = id;
}
public String getNomeMedico() {
	return nomeMedico;
}
public void setNomeMedico(String nomeMedico) {
	this.nomeMedico = nomeMedico;
}
public String getNumeroCrm() {
	return numeroCrm;
}
public void setNumeroCrm(String numeroCrm) {
	this.numeroCrm = numeroCrm;
}
public String getEspecialidade() {
	return especialidade;
}
public void setEspecialidade(String especialidade) {
	this.especialidade = especialidade;
}
}
