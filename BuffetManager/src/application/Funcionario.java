package application;

import java.time.LocalDate;

public class Funcionario {
	
	private String nome;
	private LocalDate dataContratacao;
	private String tel;
	private Double salario;
	
	public Funcionario(String nome, String tel, Double salario) {
		this.setNome(nome);
		this.setTel(tel);
		this.setSalario(salario);
		this.dataContratacao = java.time.LocalDate.now();
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	public String getNome () {
		return this.nome;
	}
	
	public void setData (LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	public LocalDate getData () {
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
