package pecas.xadrez;

import CamadaXadrex.Cor;
import CamadaXadrex.PecaXadrez;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro board, Cor color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean podeMover(Posicao position) {
		PecaXadrez p = (PecaXadrez)getBoard().peca(position);
		return p == null || p.getColor() != getColor();
	}
	
	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Posicao p = new Posicao(0, 0);
		

		p.setValues(position.getRow() - 1, position.getColumn() -2);
		if (getBoard().positionExists(p) && podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() -2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}


		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// nw
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ne
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// sw
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// se
		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && podeMover(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}

}
