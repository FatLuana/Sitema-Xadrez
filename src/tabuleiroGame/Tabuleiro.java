package tabuleiroGame;

public class Tabuleiro {

	private int linha; 
	private int coluna; 
	private Peca[][] pecas;
	
	
	public Tabuleiro(int linha, int coluna) {
		
		if(linha < 1 || coluna < 1) {
			throw new TabuleiroExcecao("Erro na criação do tabuleiro: É nescessário que aja pelo menos 1 linha e 1 coluna ");
		}
		
		this.linha = linha;
		this.coluna = coluna;
		pecas = new Peca[linha][coluna];
	}


	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	public Peca peca(int linha, int coluna) {
		if(!posicaoExistente(linha, coluna)) {
			throw new TabuleiroExcecao("Posicão invalida!");
		}
		return pecas[linha][coluna];
	}
	
	public Peca peca(Posicao posicao) {
		if(!posicaoExistente(posicao)) {
			throw new TabuleiroExcecao("Posição não existe no tabuleiro!!!");
		}
		
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void colocarPeca(Peca peca, Posicao posicao) {
		if(haUmaPeca(posicao)) {
			throw new TabuleiroExcecao("Existe uma peça nessa posição!" + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao; 
	}
	
	public boolean posicaoExistente(int linhas, int colunas) {
		return linhas >= 0 && linhas < linha && colunas >= 0 && colunas < coluna;
		 
	}
	
	public boolean posicaoExistente(Posicao posicao) {
		return posicaoExistente(posicao.getLinha(), posicao.getColuna());
	}
		
	public boolean haUmaPeca(Posicao posicao) {
		
		if(!posicaoExistente(posicao)) {
			throw new TabuleiroExcecao("Posição não existe no tabuleiro!!!");
		}
		return peca(posicao) != null;
	}
	
}
