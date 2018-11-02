package fuzzy;


public class Coordenada {
    public int linha; 
    public int coluna;

    public Coordenada(int linha, int coluna)
    {   
        this.linha = linha;
        this.coluna = coluna;
    }

    public Coordenada ToCoordenada()
    {
        return new Coordenada(linha, coluna);
    }

    public String toString()
    {
        return "" + linha + ","+ coluna; 
    }    
}
