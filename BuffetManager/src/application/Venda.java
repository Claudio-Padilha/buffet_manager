package application;

import java.time.LocalDateTime;

public class Venda {
	
	private String itens;
	private Double valor;
	private LocalDateTime data;
	
	public Venda (String itens, Double valor) {
		this.itens = itens;
		this.valor = valor;
		this.data = java.time.LocalDateTime.now();
	}
	
	public String getItens() {
		return this.itens;
	}
	
	public Double getValor() {
		return this.valor;
	}
	
	public LocalDateTime getData() {
		return this.data;
	}
}
