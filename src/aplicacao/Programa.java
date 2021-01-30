package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import CamadaXadrex.PartidaXadrez;
import CamadaXadrex.PecaXadrez;
import CamadaXadrex.PosicaoXadrez;
import CamadaXadrex.XadrezExcecao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		PartidaXadrez p = new PartidaXadrez();
		
		while(true) {
		try {
			UI.clearScreen();
			UI.imprimirPartidaXadrez(p);
			System.out.println();
			System.out.println("Origem: ");
			PosicaoXadrez origem = UI.lerPosicaoXadrez(sc);
			
			boolean[][] movimentosPossiveis = p.possivelMovimentos(origem);
			UI.clearScreen();
			UI.imprimirTabuleiro(p.pecas(), movimentosPossiveis);
			
			System.out.println();
			System.out.println("Destino: ");
			PosicaoXadrez destino = UI.lerPosicaoXadrez(sc);
			
			PecaXadrez px = p.movimentoXadrez(origem, destino);
			} 
			catch(XadrezExcecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			sc.nextLine();	
			}
		}
	}

}
