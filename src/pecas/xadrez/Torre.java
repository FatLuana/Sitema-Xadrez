package pecas.xadrez;

import CamadaXadrex.Cor;
import CamadaXadrex.PecaXadrez;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] matriz = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];
		
		Posicao p = new Posicao(0, 0);
		
		// torre movendo para cima 
		
		p.setarValores(posicao.getLinha() - 1, posicao.getColuna());
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
			p.setLinha(p.getLinha() - 1);
		}
		
		if(getTabuleiro().posicaoExistente(p) && ExistePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
		}
		
		// torre movendo para esquerda 
		
		p.setarValores(posicao.getLinha(), posicao.getColuna() - 1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
			p.setColuna(p.getColuna() - 1);
		}
		
		if(getTabuleiro().posicaoExistente(p) && ExistePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
		}
		
		// torree para direita direita 
		
		p.setarValores(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
			p.setColuna(p.getColuna() + 1);
		}
		
		if(getTabuleiro().posicaoExistente(p) && ExistePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
		}
		
		// torre movendo para baixo 
		
		p.setarValores(posicao.getLinha() + 1, posicao.getColuna());
		while(getTabuleiro().posicaoExistente(p) && !getTabuleiro().haUmaPeca(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
			p.setLinha(p.getLinha() + 1);
		}
		
		if(getTabuleiro().posicaoExistente(p) && ExistePecaOponente(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
		}
	
		return matriz;	
		
	}
	
}
