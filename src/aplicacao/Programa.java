package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import CamadaXadrex.PartidaXadrez;
import CamadaXadrex.PecaXadrez;
import CamadaXadrex.PosicaoXadrez;
import CamadaXadrex.XadrezExcecao;

public class Programa{

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez chessMatch = new PartidaXadrez();
		List<PecaXadrez> captured = new ArrayList<>();
		
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Source: ");
				PosicaoXadrez source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possivelMovimentos(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.print("Target: ");
				PosicaoXadrez target = UI.readChessPosition(sc);
				
				PecaXadrez capturedPiece = chessMatch.executarMovimentoXadrez(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
	/*			if (chessMatch.getPromoted() != null) {
					System.out.print("Enter piece for promotion (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
						System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}*/
			} 
			catch (XadrezExcecao e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	/*	UI.clearScreen();
		UI.printMatch(chessMatch, captured); */
	}
}