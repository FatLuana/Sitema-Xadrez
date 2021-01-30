package CamadaXadrex;

import pecas.xadrez.Rei;
import pecas.xadrez.Torre;
import tabuleiroGame.Peca;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public class PartidaXadrez{
	
	private int turn; 
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro; 
	
	
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Cor getJogadorAtual() {
		return jogadorAtual;
	}

	public void setJogadorAtual(Cor jogadorAtual) {
		this.jogadorAtual = jogadorAtual;
	}

	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		turn = 1; 
		jogadorAtual = Cor.BRANCO;
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
	
	public boolean[][] possivelMovimentos(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaXadrez movimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		ValidarPosicaoDestino(origem, destino);
		Peca capturarPeca = fazerMover(origem, destino);
		mudarTurno();
		return (PecaXadrez)capturarPeca;
	}
		
	private Peca fazerMover(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.revoverPeca(origem);
		Peca capturada = tabuleiro.revoverPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		return capturada;
	}
	
	private void validarPosicaoOrigem(Posicao posicao) {
		
		if(!tabuleiro.haUmaPeca(posicao)) {
			throw new XadrezExcecao("Não existe peça na posição de origem!");
		}
		
		if(jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcecao("A peça escolhida não é sua!");
		}
		
		if(!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new XadrezExcecao("Não existe movimentos possiveis para a peça escolhida!");
		}
	}
	
	private void ValidarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new XadrezExcecao("A peça de origem não pode se mover para posição de destino!");
			}
		}
		
	public void novaPeca(char coluna, int linha, PecaXadrez p) {
		tabuleiro.colocarPeca(p, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	private void mudarTurno() {
		turn++; 
		// se for banco, muda para preto. Tocando jogadores. 
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	public void configuracaoInicial() {
		novaPeca('a', 8, new Rei(tabuleiro, Cor.BRANCO));
		novaPeca('b', 8, new Rei(tabuleiro, Cor.BRANCO));
		novaPeca('c', 8, new Torre(tabuleiro, Cor.BRANCO));
		novaPeca('d', 8, new Torre(tabuleiro, Cor.BRANCO));
		novaPeca('e', 8, new Torre(tabuleiro, Cor.BRANCO));
		
		
		novaPeca('a', 1, new Torre(tabuleiro, Cor.PRETO));
		novaPeca('b', 1, new Rei(tabuleiro, Cor.PRETO));
		novaPeca('c', 1, new Rei(tabuleiro, Cor.PRETO));
		novaPeca('d', 1, new Rei(tabuleiro, Cor.PRETO));
		novaPeca('e', 1, new Rei(tabuleiro, Cor.PRETO));
		novaPeca('f', 1, new Rei(tabuleiro, Cor.PRETO));
		
		
	}


}
