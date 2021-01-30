package aplicacao;

import java.util.Scanner;

import CamadaXadrex.PartidaXadrez;
import CamadaXadrex.PecaXadrez;
import CamadaXadrex.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		PartidaXadrez p = new PartidaXadrez();
		
		while(true) {
		UI.imprimirTabuleiro(p.pecas());
		System.out.println();
		System.out.println("Origem: ");
		PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
		
		System.out.println();
		System.out.println("Destino: ");
		PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
		
		PecaXadrez px = p.movimentoXadrez(origem, destino);
		}
	}

}
