package CamadaXadrex;

import tabuleiroGame.Peca;
import tabuleiroGame.Tabuleiro;

public class PecaXadrez extends Peca{

	private Cor cor; 
	private int contagem_Movimentos;
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor, int contagem_Movimentos) {
		super(tabuleiro);
		this.cor = cor;
		this.contagem_Movimentos = contagem_Movimentos;
	}

	public Cor getCor() {
		return cor;
	}

	public int getContagem_Movimentos() {
		return contagem_Movimentos;
	}

	public void setContagem_Movimentos(int contagem_Movimentos) {
		this.contagem_Movimentos = contagem_Movimentos;
	}
	

}
