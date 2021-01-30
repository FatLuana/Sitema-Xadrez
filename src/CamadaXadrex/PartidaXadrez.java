package CamadaXadrex;

import pecas.xadrez.Rei;
import pecas.xadrez.Torre;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public class PartidaXadrez{
	
	private Tabuleiro tabuleiro; 
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		configuracaoInicial();
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
	
	public void configuracaoInicial() {
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(5,5));
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(4,2));
		tabuleiro.colocarPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(2,6));
		tabuleiro.colocarPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(1,3));
		
	}
	

}
