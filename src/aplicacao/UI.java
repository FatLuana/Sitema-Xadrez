package aplicacao;

import CamadaXadrex.PecaXadrez;

public class UI {

	public static void imprimirTabuleiro(PecaXadrez[][] peca) {
		for(int i = 0; i < peca.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j = 0; j < peca.length; j++) {
				 imprimirPeca(peca[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("  a b c d e f g h");
		
	}
	
	
	/* Imprime uma Peça no Tabuleiro */
	private static void imprimirPeca(PecaXadrez peca) {
		if(peca == null) {
		System.out.print("-");	
		}
		else {
			System.out.print(peca);
		}
		System.out.print(" ");
	}
}
