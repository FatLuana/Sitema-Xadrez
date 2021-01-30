package pecas.xadrez;

import CamadaXadrex.Cor;
import CamadaXadrex.PecaXadrez;
import tabuleiroGame.Posicao;
import tabuleiroGame.Tabuleiro;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor(); 
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean [][] matriz = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];
	
		Posicao p = new Posicao(0,0); 
	
		// movendo o rei para cima 
		p.setarValores(posicao.getLinha() - 1, posicao.getColuna());
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
			matriz[p.getLinha()][p.getColuna()] = true; 
		}
		// movendo o rei para baixo
		p.setarValores(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		matriz[p.getLinha()][p.getColuna()] = true; 
		}
		
		// movendo o rei para esquerda  
		p.setarValores(posicao.getLinha(), posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		matriz[p.getLinha()][p.getColuna()] = true; 
		}
		// movendo o rei para direita
		p.setarValores(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		matriz[p.getLinha()][p.getColuna()] = true; 
		}
		
		// movendo o rei para diagonal esquerda a cima
		p.setarValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		matriz[p.getLinha()][p.getColuna()] = true; 
		}
		
		// movendo o rei para diagonal direita a cima
		p.setarValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		matriz[p.getLinha()][p.getColuna()] = true; 
		}
		
		// movendo o rei para diagonal esquerda a baixo
		p.setarValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		matriz[p.getLinha()][p.getColuna()] = true; 
		}
			
		// movendo o rei para diagonal direita a baixo
		p.setarValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if(getTabuleiro().posicaoExistente(p) && podeMover(p)) {
		matriz[p.getLinha()][p.getColuna()] = true; 
		}
			
				
		
					
		
		
		
				
		
		
		
		
		return matriz;
	}
	
}
