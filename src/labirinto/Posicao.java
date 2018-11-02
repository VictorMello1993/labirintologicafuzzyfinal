package labirinto;

public class Posicao {
    public int linha;
    public int coluna;

    public Posicao(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
    }

    public void definirValores(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
    }

    public String toString()
    {
        return linha + ", " + coluna;
    }   
}
