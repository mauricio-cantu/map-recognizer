package br.com.unisinos.trabga;

public class Elemento {

	protected char caractere;
	protected String nome;
	protected int unidades;
	
	public Elemento(char caractere, String nome, int unidades) {
		this.caractere = caractere;
		this.nome = nome;
		this.unidades = unidades;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elemento other = (Elemento) obj;
		if (caractere != other.caractere)
			return false;
		return true;
	}






	@Override
	public String toString() {
		return nome + " (" + caractere + ") que tem " + unidades + " unidades(s).";
	}
	
	
	
	
	
	
}
