package pecas.xadrez;

import CamadaXadrex.Cor;
import CamadaXadrex.PecaXadrez;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public class Peao extends PecaXadrez{
	
	@Override
	public String toString() {
		return "P";
	}
	

	public Peao(Tabuleiro board, Cor color) {
		super(board, color);
		
	}

	@Override
	public boolean[][] possivelMovimentos() {
		
   boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Posicao p = new Posicao(0, 0);
		
		if (getColor() == Cor.BRANCO) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 2, position.getColumn());
			Posicao p2 = new Posicao(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().haUmaPeca(p) && getBoard().positionExists(p2) && !getBoard().haUmaPeca(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}	
		}
		else {
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 2, position.getColumn());
			Posicao p2 = new Posicao(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().haUmaPeca(p) && getBoard().positionExists(p2) && !getBoard().haUmaPeca(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
					
		}
		return mat;
	}
	
	
	
}
