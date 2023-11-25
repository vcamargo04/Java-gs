package br.com.fiap.rapidmed.model.entity;

import br.com.fiap.rapidmed.model.excecao.DadosInvalidosException;

public class Consulta {

private long Id;
private long IdPaciente;
private long IdMedico;
private String dataHoraConsulta;
private String numeroConsultorio;

public Consulta(long id, long idPaciente, long idMedico, String dataHoraConsulta, String numeroConsultorio)throws DadosInvalidosException  {
	
	if(id <= 1) {
		throw new DadosInvalidosException("ID CONSULTA NÃO INFORMADO");
	}
	if(idPaciente <= 1) {
		throw new DadosInvalidosException("ID PACIENTE NÃO INFORMADO");
	}
	if(idMedico <= 1) {
		throw new DadosInvalidosException("ID MEDICO NÃO INFORMADO");
	}
	if(dataHoraConsulta == null || dataHoraConsulta.isBlank()) {
		throw new DadosInvalidosException("DATA NÃO INFORMADA");
	}
	if(numeroConsultorio == null || numeroConsultorio.isBlank()) {
		throw new DadosInvalidosException("NUMERO DO CONSULTORIO NÃO INFORMADO");
	}
	
	Id = id;
	IdPaciente = idPaciente;
	IdMedico = idMedico;
	this.dataHoraConsulta = dataHoraConsulta;
	this.numeroConsultorio = numeroConsultorio;
}
public Consulta() {
	// TODO Auto-generated constructor stub
}
public long getId() {
	return Id;
}
public void setId(long id) {
	Id = id;
}
public long getIdPaciente() {
	return IdPaciente;
}
public void setIdPaciente(long idPaciente) {
	IdPaciente = idPaciente;
}
public long getIdMedico() {
	return IdMedico;
}
public void setIdMedico(long idMedico) {
	IdMedico = idMedico;
}
public String getDataHoraConsulta() {
	return dataHoraConsulta;
}
public void setDataHoraConsulta(String dataHoraConsulta) {
	this.dataHoraConsulta = dataHoraConsulta;
}
public String getNumeroConsultorio() {
	return numeroConsultorio;
}
public void setNumeroConsultorio(String numeroConsultorio) {
	this.numeroConsultorio = numeroConsultorio;
}

}
