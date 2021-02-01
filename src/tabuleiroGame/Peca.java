package tabuleiroGame;

public abstract class Peca {

	protected Posicao position;
	private Tabuleiro board;
	
	public Peca(Tabuleiro board) {
		this.board = board;
		position = null;
	}

	protected Tabuleiro getBoard() {
		return board;
	}
	
	public abstract boolean[][] possivelMovimentos();
	
	public boolean possibleMove(Posicao position) {
		return possivelMovimentos()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possivelMovimentos();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}