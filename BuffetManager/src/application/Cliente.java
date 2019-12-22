package application;

public class Cliente {
	
	private String nome;
	private String tel;
	private String email;
	
	public Cliente(String nome, String tel, String email) {
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
