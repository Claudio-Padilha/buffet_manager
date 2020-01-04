package application;

public class Produto implements Comparable<Produto> {
	
	Integer id;
	String nome;
	Double preco;
	String categoria;
	
	public Produto (String nome, Double preco, String categoria) {
		setNome(nome);
		setPreco(preco);
		setCategoria(categoria);
	}
	
	public Produto() {

	}

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getPreco() {
		return this.preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public int compareTo(Produto p) {
		return this.getNome().compareToIgnoreCase(p.getNome());
	}
}
