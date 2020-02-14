package br.com.unisinos.trabga;

import java.io.File;
import java.io.IOException;

public class MapaTest {

	public static void main(String[] args) {
		
		IMapa mapa = new Mapa();
		
		try {
			mapa.carregar(new File("C:\\Users\\mauriciojochims\\Documents\\Mapas\\mapa1.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mapa.exibirMatriz();
		mapa.reconhecer();
		mapa.exibirElementos();

	}

}
