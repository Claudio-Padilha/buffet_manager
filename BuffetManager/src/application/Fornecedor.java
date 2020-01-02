package application;

public class Fornecedor implements Comparable<Fornecedor>{
	
	private Integer id;
	private String nome;
	private String cnpj;
	private String endereco;
	private String tel;
	private String email;
	private String infoProdutos;
	
	public Fornecedor(String nome, String cnpj, String endereco, String tel, String email, String infoProdutos) {
		this.setNome(nome);
		this.setCnpj(cnpj);
		this.setEndereco(endereco);
		this.setTel(tel);
		this.setEmail(email);
		this.setInfoProdutos(infoProdutos);
	}
	
	public void setId (Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return this.id;
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
	
	public void setEndereco (String endereco) {
		this.endereco = endereco;
	}
	public String getEndereco () {
		return this.endereco;
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
	
	public void setInfoProdutos (String InfoProdutos) {
		this.infoProdutos = InfoProdutos;
	}
	public String getInfoProdutos () {
		return this.infoProdutos;
	}

	@Override
	public int compareTo(Fornecedor f) {
		return this.getNome().compareToIgnoreCase(f.getNome());
		
	}
}
