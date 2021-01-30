package CamadaXadrex;

import tabuleiroGame.Peca;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor; 
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	protected boolean ExistePecaOponente(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
	

}
