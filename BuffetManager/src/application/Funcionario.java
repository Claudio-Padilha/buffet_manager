package application;

public class Funcionario {
	
	private Integer id;
	private String nome;
	private String cpf;
	private String rg;
	private String endereco;
	private String tel;
	private String email;
	private String cargo;
	// private java.sql.Date dataContratacao;
	private Double salario;
	
	public Funcionario(String nome, String cpf, String rg, String endereco,
					   String tel, String email, String cargo, Double salario) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setRg(rg);
		this.setEndereco(endereco);
		this.setTel(tel);
		this.setEmail(email);
		this.setCargo(cargo);
		this.setSalario(salario);
		// this.dataContratacao = java.sql.Date.valueOf(java.time.LocalDate.now());
	}
	
	public Funcionario() {
		// this.dataContratacao = java.sql.Date.valueOf(java.time.LocalDate.now());
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
	
	public void setCpf (String cpf) {
		this.cpf = cpf;
	}
	
	public String getCpf () {
		return this.cpf;
	}
	
	public void setRg (String rg) {
		this.rg = rg;
	}
	
	public String getRg () {
		return this.rg;
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
	
	public void setCargo (String cargo) {
		this.cargo = cargo;
	}
	
	public String getCargo () {
		return this.cargo;
	}
	
	/* public void setData (java.sql.Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	} */
	
	/* public java.sql.Date getData () {
		return this.dataContratacao;
	} */
	
	
	
	public void setSalario (Double salario) {
		this.salario = salario;
	}
	
	public Double getSalario () {
		return this.salario;
	}
}