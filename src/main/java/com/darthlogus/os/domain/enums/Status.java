package com.darthlogus.os.domain.enums;

public enum Status {
	ABERTO (0, "ABERTO"),
	ANDAMENTO (1, "EM ANDAMENTO"),
	ENCERRADO (2, "ENCERRADO");
	
	private Integer cod;
	private String descricao;
	
	private Status(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return this.cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Status toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválido!" + cod);
	}

	public static Status StringToEnum(String descricao){
		if (descricao == null){
			return null;
		}

		for (Status x : Status.values()){
			if (descricao.equals(x.getDescricao())){
				return x;
			}
		}

		throw new IllegalArgumentException("Status inválido!: " + descricao);
	}
}
