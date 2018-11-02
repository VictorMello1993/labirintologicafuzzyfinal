package labirinto;

public class Labirinto {
    public int linhas;
    public int colunas; 
    private Componente[][] componentes;

    public Labirinto(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        componentes = new Componente[linhas][colunas];
    }
    
     public Componente componente(int linha, int coluna)
    {
        return componentes[linha][coluna];
    }

    public Componente componente(Posicao pos)
    {
        if (pos.linha >= linhas) pos.linha = linhas - 1;
        if (pos.coluna >= colunas) pos.coluna = colunas - 1;
        if (pos.linha < 0) pos.linha = 0;
        if (pos.coluna < 0) pos.coluna = 0;
        
        return componentes[pos.linha][pos.coluna];
    }

    public boolean barreira(Posicao pos)
    {
    	boolean teste = false;

        if (limite(pos))
        {
            //throw new LabirintoException("Já existe componente na posição!");
            teste = true;
        }
        else
        if (componente(pos) != null)
        {
            teste = (componente(pos).cor != Cor.VERMELHO) && (componente(pos).cor != Cor.AZULCLARO);
        }

        return teste;
    }

    public void colocarComponente(Componente p, Posicao pos)
    {
        if (barreira(pos))
        {
            // throw new LabirintoException("Já existe componente nessa posição!");
        }
        else
        {
            if (componente(pos) != null && componente(pos).cor == Cor.AZULCLARO)
            {

            }
            else
            {
                componentes[pos.linha][pos.coluna] = p;
                p.posicao = pos;
            }
        }

    }

    public void moveRoboDestino(Componente p, Posicao pos)
    {
        if (barreira(pos))
        {
            // throw new LabirintoException("Já existe um componente nessa posição!");
        }
        else
        {
            if (componente(pos) != null && componente(pos).cor == Cor.AZULCLARO)
            {
                
            }
            else
            {
                componentes[pos.linha][pos.coluna] = p;
                p.posicao = pos;
            }
        }
    }

    public Componente retirarComponente(Posicao pos)
    {
        if (componente(pos)==null)
        {
            return null;
        }

        Componente aux = componente(pos);
        aux.posicao = null;

        if (aux.cor == Cor.VERMELHO)
        {
            componentes[pos.linha][pos.coluna] = null;
        }

        return aux;
    }

    public Componente moveRoboOrigem(Posicao pos)
    {
        if (componente(pos) == null)
        {
            return null;
        }

        Componente aux = componente(pos);
        aux.posicao = null;
        componentes[pos.linha][pos.coluna] = null;
        
        return aux;
    }

    public boolean limite(Posicao pos)
    {
        return (pos.linha < 0 || pos.linha >= linhas || pos.coluna < 0 || pos.coluna >= colunas)? true : false;
    }

    public boolean validarPosicao(Posicao pos)
    {
    	boolean valida;

        if (!barreira(pos))
        {
            valida = false;
        }
        else valida = true;

        return valida;
    }
}
