package CamadaXadrex;

import tabuleiroGame.Peca;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public abstract class PecaXadrez extends Peca {

	private Cor color;
	private int moveCount;

	public PecaXadrez(Tabuleiro board, Cor color) {
		super(board);
		this.color = color;
	}

	public Cor getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}

	public void decreaseMoveCount() {
		moveCount--;
	}

	public PosicaoXadrez getChessPosition() {
		return PosicaoXadrez.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Posicao position) {
		PecaXadrez p = (PecaXadrez)getBoard().peca(position);
		return p != null && p.getColor() != color;
	}
}
