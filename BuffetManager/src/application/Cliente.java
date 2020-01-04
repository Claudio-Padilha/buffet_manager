package application;

public class Cliente implements  Comparable<Cliente>{
	
	private Integer id;
	private String nome;
	private String tel;
	private String email;
	private String endereco;
	
	public Cliente(String nome, String tel, String email, String endereco) {
		this.setNome(nome);
		this.setTel(tel);
		this.setEmail(email);
		this.setEndereco(endereco);
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
	
	public void setTel (String tel) {
		this.tel = tel;
	}
	public String getTel () {
		return this.tel;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	public String getEmail () {
		return this.email;
	}
	
	public void setEndereco (String endereco) {
		this.endereco = endereco;
	}
	public String getEndereco () {
		return this.endereco;
	}

	@Override
	public int compareTo(Cliente c) {
		return this.getNome().compareToIgnoreCase(c.getNome());
	}
}
