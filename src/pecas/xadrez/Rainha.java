package pecas.xadrez;

import CamadaXadrex.Cor;
import CamadaXadrex.PecaXadrez;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public class Rainha extends PecaXadrez{

	public Rainha(Tabuleiro board, Cor color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "�";
	}
	
	
	@Override
	public boolean[][] possivelMovimentos() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		Posicao p = new Posicao(0, 0);
		
		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		while (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// noroeste
				p.setValues(position.getRow() - 1, position.getColumn() - 1);
				while (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() - 1, p.getColumn() - 1);
				}
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				
				// nordeste
				p.setValues(position.getRow() - 1, position.getColumn() + 1);
				while (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() - 1, p.getColumn() + 1);
				}
					if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
							mat[p.getRow()][p.getColumn()] = true;
					}
				// sudeste
				p.setValues(position.getRow() + 1, position.getColumn() + 1);
				while (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() + 1, p.getColumn() + 1);
				}
					if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
							mat[p.getRow()][p.getColumn()] = true;
						}
			// sudoeste
				p.setValues(position.getRow() + 1, position.getColumn() - 1);
				while (getBoard().positionExists(p) && !getBoard().haUmaPeca(p)) {
					mat[p.getRow()][p.getColumn()] = true;
					p.setValues(p.getRow() + 1, p.getColumn() - 1);
				}
					if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
							mat[p.getRow()][p.getColumn()] = true;
					}
				
			
		
		return mat;
	}
	
	

}
