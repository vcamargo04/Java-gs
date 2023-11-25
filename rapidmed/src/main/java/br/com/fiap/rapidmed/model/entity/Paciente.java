package br.com.fiap.rapidmed.model.entity;

import br.com.fiap.rapidmed.model.excecao.DadosInvalidosException;

public class Paciente {

private long Id;
private long cpf;
private String nomeDoPaciente;
private long idLogin;
private String username;
private String senha;





public long getId() {
	return Id;
}


public void setId(long id) {
	Id = id;
}


public long getCpf() {
	return cpf;
}


public void setCpf(long cpf) {
	this.cpf = cpf;
}


public String getNomeDoPaciente() {
	return nomeDoPaciente;
}


public void setNomeDoPaciente(String nomeDoPaciente) {
	this.nomeDoPaciente = nomeDoPaciente;
}


public long getIdLogin() {
	return idLogin;
}


public void setIdLogin(long idLogin) {
	this.idLogin = idLogin;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public String getSenha() {
	return senha;
}


public void setSenha(String senha) {
	this.senha = senha;
}





public Paciente(long id, long cpf, String nomeDoPaciente, long idLogin, String username, String senha) {
	
	if(id <=1) {
		throw new DadosInvalidosException("ID DO PACIENTE NÃO INFORMADO");
	}
	if(cpf <0 || equals(null)) {
		throw new DadosInvalidosException("CPF NÃO INFORMADO");
		
	}
	if(nomeDoPaciente == null || nomeDoPaciente.isBlank()) {
		throw new DadosInvalidosException("NOME NÃO INFORMADO");
	}
	if(username == null || username.isBlank()) {
		throw new DadosInvalidosException("NOME DE USUARIO NÃO INFORMADO");
	}
	if(senha == null || senha.isBlank()) {
		throw new DadosInvalidosException("SENHA NÃO INFORMADA");
	}
	if(idLogin <=1) {
		throw new DadosInvalidosException("ID DO LOGIN NÃO INFORMADO");
	}
	
	Id = id;
	this.cpf = cpf;
	this.nomeDoPaciente = nomeDoPaciente;
	this.idLogin = idLogin;
	this.username = username;
	this.senha = senha;
}


public Paciente() {
	super();
}


}

