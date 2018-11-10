package fuzzy;

import labirinto.Componente;
import labirinto.Cor;
import labirinto.Labirinto;
import labirinto.Posicao;

public class Robo  extends Componente {
	public Robo(Labirinto lab, Cor cor)
    {
		super(lab, cor);
    }

    @Override
    public String toString()
    {
        return "R";
    }

    private boolean podeMover(Posicao pos)
    {
    	boolean teste = false;
        Componente p = lab.componente(pos);

        if (!lab.barreira(pos))
        {
            if (p == null || p.cor == Cor.VERMELHO)
            {
                teste = true;
            }
            else
            if (p != null && p.cor == Cor.AMARELO)
            {
                teste = false;
            }
            else
            if (p != null && p.cor == Cor.AZULCLARO)
            {
                teste = true;
            }
        }
        else teste = false;

        return teste;
    }

    @Override
    public boolean[][] movimentosPossiveis()
    {
    	boolean[][] mat = new boolean[lab.linhas][lab.colunas];
        Posicao pos = new Posicao(0, 0);

        //Sul
        for (int i = 1; i < alcance; i++)
        {
            pos.definirValores(posicao.linha + i, posicao.coluna);

            if (marca(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }
            else break;
        }

        //Sudeste
        for (int i = 1; i < alcance; i++)
        {
            pos.definirValores(posicao.linha + i, posicao.coluna + i);
            
        }

        //Leste
        for (int i = 1; i < alcance; i++)
        {
            pos.definirValores(posicao.linha, posicao.coluna + i);

            if (marca(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }
            else break;
        }

        //Nordeste
        for (int i = 1; i < alcance; i++)
        {
            pos.definirValores(posicao.linha - i, posicao.coluna + i);

            if (marca(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }
            else break;
        }

        //Norte
        for (int i = 1; i < alcance; i++)
        {
            pos.definirValores(posicao.linha - i, posicao.coluna);

            if (marca(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }
            else break;
        }

        //Noroeste
        for (int i = 1; i < alcance; i++)
        {
            pos.definirValores(posicao.linha - i, posicao.coluna - i);

            if (marca(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }
            else break;
        }

        //Oeste
        for (int i = 1; i < alcance; i++)
        {
            pos.definirValores(posicao.linha, posicao.coluna - i);

            if (marca(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }
            else break;
        }

        //Sudoeste
        for (int i = 1; i < alcance; i++)
        {
            pos.definirValores(posicao.linha + i, posicao.coluna - i);

            if (marca(pos))
            {
                mat[pos.linha][pos.coluna] = true;
            }
            else break;
        }

        return mat;
    }

    private boolean marca(Posicao pos)
    {
    	boolean teste = false;

        if (!lab.barreira(pos))
        {
            if (podeMover(pos))
            {
                if (pos.linha >= lab.linhas) pos.definirValores(lab.linhas - 1, posicao.coluna);
                if (pos.coluna >= lab.colunas) pos.definirValores(lab.linhas, posicao.coluna - 1);
                teste = true;
            }
            else
            {
                if (lab.componente(pos).cor == Cor.AZULCLARO)
                {
                    if (pos.linha >= lab.linhas) pos.definirValores(lab.linhas - 1, posicao.coluna);
                    if (pos.coluna >= lab.colunas) pos.definirValores(lab.linhas, posicao.coluna - 1);
                    teste = true;
                }
            }
        }

        return teste;
    }
}
