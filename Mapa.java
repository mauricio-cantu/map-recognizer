package br.com.unisinos.trabga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Mapa implements IMapa {

	private char[][] matriz;
	private boolean[][] visitados;
	private Elemento[] elementos;
	private int index = 0, contEl = 0;

	@Override
	public void carregar(File file) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			// inicializa matriz com as informacoes da primeira linha do arquivo
			String linha = br.readLine();
			String[] infos = linha.split(" ");
			matriz = new char[Integer.parseInt(infos[0])][Integer.parseInt(infos[1])];

			// insere cada caractere do mapa nas posicoes do array
			while ((linha = br.readLine()) != null)
				matriz[index++] = linha.toCharArray();

			// matriz de mesmo tamanho para identificar quais posições ja foram lidas
			visitados = new boolean[matriz.length][matriz[0].length];

			// lista para guardar os elementos encontrados no mapa
			elementos = new Elemento[matriz.length * matriz[0].length];
		}

	}

	@Override
	public void reconhecer() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				// garante que cada caractere do elemento seja lido apenas uma vez
				if (!visitados[i][j]) {
					// conta de forma recursiva os caracteres vizinhos iguais ao caractere da
					// iteracao atual para identificar o numero de caracteres que o elemento possui
					int quant = contaCaracteres(matriz[i][j], i, j);
					switch (matriz[i][j]) {
					case ' ':
						elementos[contEl++] = new Campo(matriz[i][j], quant);
						break;
					case '*':
						elementos[contEl++] = new Agua(matriz[i][j], quant);
						break;
					case '/':
						elementos[contEl++] = new Edificio(matriz[i][j], quant);
						break;
					case '+':
						elementos[contEl++] = new Arvore(matriz[i][j], quant);
						break;
					case '=':
						elementos[contEl++] = new Cerca(matriz[i][j], quant);
						break;
					}
				}
			}
		}
	}

	private int contaCaracteres(char el, int linha, int col) {
		if (linha < 0 || col < 0 || linha >= matriz.length || col >= matriz[0].length || visitados[linha][col]
				|| el != matriz[linha][col])
			return 0;
		visitados[linha][col] = true;
		return 1 + contaCaracteres(el, linha + 1, col) + contaCaracteres(el, linha, col + 1)
				+ contaCaracteres(el, linha - 1, col) + contaCaracteres(el, linha, col - 1);
	}

	@Override
	public void exibirMatriz() {
		for (int i = 0; i < matriz.length; i++)
			for (int j = 0; j < matriz[i].length; j++)
				System.out.print(matriz[i][j] + (j == matriz[i].length - 1 ? "\n" : ""));
	}

	@Override
	public void exibirElementos() {
		System.out.println("\nRelatorio do Reconhecimento\n============================");
		for (int i = 0; i < contEl; i++)
			System.out.println(elementos[i]);
	}

}
