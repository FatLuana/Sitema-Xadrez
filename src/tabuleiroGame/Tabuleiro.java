package tabuleiroGame;

public class Tabuleiro {

	private int rows;
	private int columns;
	private Peca[][] pieces;
	
	public Tabuleiro(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new TabuleiroExcecao("Erro ao criar tabuleiro: deve haver pelo menos 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Peca[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Peca peca(int row, int column) {
		if (!positionExists(row, column)) {
			throw new TabuleiroExcecao("A posicao nao esta no quadro");
		}
		return pieces[row][column];
	}
	
	public Peca peca(Posicao position) {
		if (!positionExists(position)) {
			throw new TabuleiroExcecao("A posicao nao esta no quadro");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void colocarPeca(Peca piece, Posicao position) {
		if (haUmaPeca(position)) {
			throw new TabuleiroExcecao("Ja existe uma peca na posicao" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Peca removerPeca(Posicao position) {
		if (!positionExists(position)) {
			throw new TabuleiroExcecao("A posicao nao esta no quadro");
		}
		if (peca(position) == null) {
			return null;
		}
		Peca aux = peca(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Posicao position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean haUmaPeca(Posicao position) {
		if (!positionExists(position)) {
			throw new TabuleiroExcecao("A posicao nao esta no tabuleiro");
		}
		return peca(position) != null;
	}
}