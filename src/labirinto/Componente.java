package labirinto;

public abstract class Componente {
    public Posicao posicao;
    public Cor cor; 
    public Labirinto lab;
    public int alcance;

    public Componente(Labirinto lab, Cor cor)
    {
        this.posicao = null;
        this.lab = lab;
        this.cor = cor;
        this.alcance = 5;
    }

    public abstract String toString();
    
    public abstract boolean[][] movimentosPossiveis();
}
