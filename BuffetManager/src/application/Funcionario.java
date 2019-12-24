package application;

import java.sql.Date;
import java.time.LocalDate;

public class Funcionario {
	
	private Integer id;
	private String nome;
	private java.sql.Date dataContratacao;
	private String tel;
	private Double salario;
	
	public Funcionario(String nome, String tel, Double salario) {
		this.setNome(nome);
		this.setTel(tel);
		this.setSalario(salario);
		this.dataContratacao = java.sql.Date.valueOf(java.time.LocalDate.now());
	}
	
	public Funcionario() {
		this.dataContratacao = java.sql.Date.valueOf(java.time.LocalDate.now());
	}

	public Integer getId () {
		return this.id;
	}
	
	public void setId (Integer id) {
		this.id = id;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	public String getNome () {
		return this.nome;
	}
	
	public void setData (java.sql.Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	public java.sql.Date getData () {
		return this.dataContratacao;
	}
	
	public void setTel (String tel) {
		this.tel = tel;
	}
	public String getTel () {
		return this.tel;
	}
	
	public void setSalario (Double salario) {
		this.salario = salario;
	}
	public Double getSalario () {
		return this.salario;
	}
}
