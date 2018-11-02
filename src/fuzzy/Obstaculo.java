package fuzzy;

import labirinto.Componente;
import labirinto.Cor;
import labirinto.Labirinto;

public class Obstaculo extends Componente{

    public Obstaculo(Labirinto lab, Cor cor) {
        super(lab, cor);
    }

    @Override
    public String toString()
    {
        return "L";
    }

    @Override
    public boolean[][] movimentosPossiveis()
    {
        boolean[][] mat = new boolean[lab.linhas][lab.colunas];
        return mat;
    }
}
