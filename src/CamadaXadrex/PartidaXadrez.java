package CamadaXadrex;

import tabuleiroGame.Tabuleiro;

public class PartidaXadrez{
	
	private Tabuleiro tabuleiro; 
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
	}

	public PecaXadrez[][] pecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColuna()];
		for(int i = 0; i < tabuleiro.getLinha(); i++) {
			for(int j = 0; j < tabuleiro.getColuna(); j++) {
				mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	

}
