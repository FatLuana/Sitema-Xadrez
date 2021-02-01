package CamadaXadrex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pecas.xadrez.Rei;
import pecas.xadrez.Torre;
import tabuleiroGame.Peca;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;


public class PartidaXadrez {

	private int turn;
	private Cor jogadorAtual;
	private Tabuleiro board; // tabuleiro
	private boolean check;
	private boolean checkMate;
	private PecaXadrez enPassantVulnerable;
	private PecaXadrez promoted;
	
	private List<Peca> pecaNoTabuleiro = new ArrayList<>();
	private List<Peca> capturarPecas = new ArrayList<>();
	
	public PartidaXadrez() {
		board = new Tabuleiro(8, 8);
		turn = 1;
		jogadorAtual = Cor.BRANCO;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Cor getCurrentPlayer() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public PecaXadrez getEnPassantVulnerable() {
		return enPassantVulnerable;
	}
	
	public PecaXadrez getPromoted() {
		return promoted;
	}
	
	public PecaXadrez[][] getPieces() {
		PecaXadrez[][] mat = new PecaXadrez[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (PecaXadrez) board.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possivelMovimentos(PosicaoXadrez sourcePosition) {
		Posicao position = sourcePosition.toPosition();
		validarPosicaoOrigem(position);
		return board.peca(position).possivelMovimentos();
	}
	
	public PecaXadrez executarMovimentoXadrez(PosicaoXadrez sourcePosition, PosicaoXadrez targetPosition) {
		Posicao origem = sourcePosition.toPosition();
		Posicao destino = targetPosition.toPosition();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca capturarPeca = fazerMover(origem, destino);
		
		if (testCheck(jogadorAtual)) {
			desfazerMovimento(origem, destino, capturarPeca);
			throw new XadrezExcecao("Voce nao pode se colocar em cheque!");
		}
		
		/*ChessPiece movedPiece = (ChessPiece)board.piece(target);
		
		// #specialmove promotion
		promoted = null;
		if (movedPiece instanceof Pawn) {
			if ((movedPiece.getColor() == Color.WHITE && target.getRow() == 0) || (movedPiece.getColor() == Color.BLACK && target.getRow() == 7)) {
				promoted = (ChessPiece)board.piece(target);
				promoted = replacePromotedPiece("Q");
			}
		}*/
		
		check = (testCheck(oponente(jogadorAtual))) ? true : false;

		if (testCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			nextTurn();
		}
		
		// #specialmove en passant
	/*	if (movedPiece instanceof Pawn && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
			enPassantVulnerable = movedPiece;
		}
		else {
			enPassantVulnerable = null;
		}*/
		
		return (PecaXadrez)capturarPeca;
	}

	/*public PecaXadrez replacePromotedPiece(String type) {
		if (promoted == null) {
			throw new IllegalStateException("There is no piece to be promoted");
		}
		if (!type.equals("B") && !type.equals("N") && !type.equals("R") & !type.equals("Q")) {
			return promoted;
		}
		
		Posicao pos = promoted.getChessPosition().toPosition();
		Peca p = board.removePiece(pos);
		piecesOnTheBoard.remove(p);
		
		PecaXadrez newPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);
		
		return newPiece;
	}
	
	private PecaXadrez newPiece(String type, Color color) {
		if (type.equals("B")) return new Bishop(board, color);
		if (type.equals("N")) return new Knight(board, color);
		if (type.equals("Q")) return new Queen(board, color);
		return new Rook(board, color);
	}*/ 
	
	private Peca fazerMover(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez)board.removerPeca(origem);
	//	p.increaseMoveCount();
		Peca capturarPeca = board.removerPeca(destino);
		board.colocarPeca(p, destino);
		
		if (capturarPeca != null) {
			pecaNoTabuleiro.remove(capturarPeca);
			capturarPecas.add(capturarPeca);
		}
		
	/*	// #specialmove castling kingside rook
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
			board.placePiece(rook, targetT);
			rook.increaseMoveCount();
		}

		// #specialmove castling queenside rook
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
			board.placePiece(rook, targetT);
			rook.increaseMoveCount();
		}		
		
		// #specialmove en passant
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == null) {
				Position pawnPosition;
				if (p.getColor() == Color.WHITE) {
					pawnPosition = new Position(target.getRow() + 1, target.getColumn());
				}
				else {
					pawnPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}*/
		
		return capturarPeca;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca capturarPeca) {
		PecaXadrez p = (PecaXadrez)board.removerPeca(destino);
	//	p.decreaseMoveCount();
		board.colocarPeca(p, origem);
		
		if (capturarPeca != null) {
			board.colocarPeca(capturarPeca, destino);
			capturarPecas.remove(capturarPeca);
			pecaNoTabuleiro.add(capturarPeca);
		}

	/*	// #specialmove castling kingside rook
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() + 3);
			Position targetT = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece)board.removePiece(targetT);
			board.placePiece(rook, sourceT);
			rook.decreaseMoveCount();
		}

		// #specialmove castling queenside rook
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			Position sourceT = new Position(source.getRow(), source.getColumn() - 4);
			Position targetT = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece)board.removePiece(targetT);
			board.placePiece(rook, sourceT);
			rook.decreaseMoveCount();
		}
		
		// #specialmove en passant
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				ChessPiece pawn = (ChessPiece)board.removePiece(target);
				Position pawnPosition;
				if (p.getColor() == Color.WHITE) {
					pawnPosition = new Position(3, target.getColumn());
				}
				else {
					pawnPosition = new Position(4, target.getColumn());
				}
				board.placePiece(pawn, pawnPosition);
			}
		}*/
	}
	
	private void validarPosicaoOrigem(Posicao position) {
		if (!board.haUmaPeca(position)) {
			throw new XadrezExcecao("Nao ha peça na posicao da fonte");
		}
		if (jogadorAtual != ((PecaXadrez)board.peca(position)).getColor()) {
			throw new XadrezExcecao("A peca escolhida nao e sua");
		}
		if (!board.peca(position).isThereAnyPossibleMove()) {
			throw new XadrezExcecao("Nao ha movimentos possiveis para a peca escolhida");
		}
	}
	
	private void validarPosicaoDestino(Posicao source, Posicao target) {
		if (!board.peca(source).possibleMove(target)) {
			throw new XadrezExcecao("The chosen piece can't move to target position");
		}
	}
	
	private void nextTurn() {
		turn++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PecaXadrez king(Cor color) {
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private boolean testCheck(Cor color) {
		Posicao kingPosition = king(color).getChessPosition().toPosition();
		List<Peca> pecaDoOponente = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == oponente(color)).collect(Collectors.toList());
		for (Peca p : pecaDoOponente) {
			boolean[][] mat = p.possivelMovimentos();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Cor color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getColor() == color).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.possivelMovimentos();
			for (int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColumns(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getChessPosition().toPosition();
						Posicao destino = new Posicao(i, j);
						Peca capturedPiece = fazerMover(origem, destino);
						boolean testCheck = testCheck(color);
						desfazerMovimento(origem, destino, capturedPiece);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	private void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		board.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosition());
		pecaNoTabuleiro.add(peca);
	}
	
	private void initialSetup() {
    
		colocarNovaPeca('a', 8, new Rei(board, Cor.PRETO));
		colocarNovaPeca('b', 8, new Torre(board, Cor.PRETO));
	//	colocarNovaPeca('h', 7, new Torre(board, Cor.PRETO));
	//	colocarNovaPeca('e', 2, new Torre(board, Cor.PRETO));
        
		colocarNovaPeca('e', 1, new Rei(board, Cor.BRANCO));
		colocarNovaPeca('h', 7, new Torre(board, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Torre(board, Cor.BRANCO));
	//	colocarNovaPeca('e', 7, new Torre(board, Cor.BRANCO));
        
	}
}