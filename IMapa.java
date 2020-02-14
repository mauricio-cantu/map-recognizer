package br.com.unisinos.trabga;

import java.io.File;
import java.io.IOException;

public interface IMapa {

	public void carregar(File file) throws IOException;
	public void reconhecer();
	public void exibirMatriz();
	public void exibirElementos();
	
	
}
