package application;

public class Fornecedor {

	private String nome;
	private String cnpj;
	private String tel;
	private String email;
	
	public Fornecedor(String nome, String cnpj, String tel, String email) {
		this.setNome(nome);
		this.setTel(tel);
		this.setEmail(email);
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	public String getNome () {
		return this.nome;
	}
	
	public void setCnpj (String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCnpj () {
		return this.cnpj;
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
}
