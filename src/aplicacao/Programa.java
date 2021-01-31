package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import CamadaXadrex.PartidaXadrez;
import CamadaXadrex.PecaXadrez;
import CamadaXadrex.PosicaoXadrez;
import CamadaXadrex.XadrezExcecao;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		PartidaXadrez p = new PartidaXadrez();
		List<PecaXadrez> captura = new ArrayList<>();
		
		while(true) {
		try {
			UI.clearScreen();
			UI.imprimirPartidaXadrez(p, captura);
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
			
			if(px != null) {
				captura.add(px);
			}
			
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
