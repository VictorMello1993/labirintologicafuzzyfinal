package fuzzy;

import labirinto.Componente;
import labirinto.Cor;
import labirinto.Labirinto;


public class Porta extends Componente {

    public Porta(Labirinto lab, Cor cor) {
        super(lab, cor);
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[lab.linhas][lab.colunas];
        return mat;
    }
    
}
