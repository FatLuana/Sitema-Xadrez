package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import CamadaXadrex.Cor;
import CamadaXadrex.PartidaXadrez;
import CamadaXadrex.PecaXadrez;
import CamadaXadrex.PosicaoXadrez;

public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	/* Lipando a tela */ 
	public static void clearScreen() {
	 System.out.print("\033[H\033[2J");
	 System.out.flush();
	} 
	
	public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
		
		try {
		String s = sc.nextLine(); 
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		return new PosicaoXadrez(coluna, linha);
		
		} catch(RuntimeException e ) {
			throw new InputMismatchException("Erro lendo a posi��o do xadrez: Valores v�lidos de a1 at� h8");
		}
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] peca) {
		for(int i = 0; i < peca.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j = 0; j < peca.length; j++) {
				 imprimirPeca(peca[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");	
	}
	
	public static void imprimirPartidaXadrez(PartidaXadrez partida, List<PecaXadrez> captura) {
		imprimirTabuleiro(partida.pecas());
		System.out.println();
		printCapturarPecas(captura);
		System.out.println();
		System.out.println("Turn: " + partida.getTurn());
		System.out.println("Esperando jogar: " + partida.getJogadorAtual());
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] peca, boolean[][] movimentosPossiveis) {
		for(int i = 0; i < peca.length; i++) {
			System.out.print((8 - i) + " ");
			for(int j = 0; j < peca.length; j++) {
				 imprimirPeca(peca[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	
	/* Imprime uma Pe�a no Tabuleiro */
	private static void imprimirPeca(PecaXadrez peca, boolean fundo) {
		if(fundo) {
			System.out.print(ANSI_GREEN_BACKGROUND);
		}
		
		if(peca == null) {
		System.out.print("-" + ANSI_RESET);	
		}
		else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void printCapturarPecas(List<PecaXadrez> captura) {
		List<PecaXadrez> branco =  captura.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
		List<PecaXadrez> preta =  captura.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		
		System.out.println("Pe�as capturadas: ");
		System.out.println();
		System.out.print("Pe�as brancas: ");
		System.out.print(ANSI_WHITE);
		//imprimir lista de arrray 
		System.out.println(Arrays.toString(branco.toArray()));
		System.out.print(ANSI_RESET);
		
		System.out.println();
		System.out.print("Pe�as pretas: ");
		System.out.print(ANSI_YELLOW);
		//imprimir lista de arrray 
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.print(ANSI_RESET);
		
		
	}
}
