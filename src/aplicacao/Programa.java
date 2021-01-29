package aplicacao;

import CamadaXadrex.PartidaXadrez;

public class Programa {

	public static void main(String[] args) {

		PartidaXadrez p = new PartidaXadrez();
		UI.imprimirTabuleiro(p.pecas());
		
	}

}
