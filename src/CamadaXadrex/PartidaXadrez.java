package CamadaXadrex;

import pecas.xadrez.Rei;
import pecas.xadrez.Torre;
import tabuleiroGame.Peca;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public class PartidaXadrez{
	
	private Tabuleiro tabuleiro; 
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
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
	
	public PecaXadrez movimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		Peca capturarPeca = fazerMover(origem, destino);
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
	}
	
	public void novaPeca(char coluna, int linha, PecaXadrez p) {
		tabuleiro.colocarPeca(p, new PosicaoXadrez(coluna, linha).toPosicao());
	}
	
	
	public void configuracaoInicial() {
		novaPeca('b', 5, new Rei(tabuleiro, Cor.BRANCO));
		novaPeca('c', 2, new Torre(tabuleiro, Cor.PRETO));
		novaPeca('c', 3, new Rei(tabuleiro, Cor.BRANCO));
		novaPeca('d', 1, new Torre(tabuleiro, Cor.BRANCO));
		novaPeca('e', 7, new Rei(tabuleiro, Cor.PRETO));
	}


}
