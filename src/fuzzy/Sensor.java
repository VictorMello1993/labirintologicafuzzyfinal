package fuzzy;

import labirinto.Componente;
import labirinto.Cor;
import labirinto.Labirinto;

public class Sensor extends Componente {
	
	public Sensor(Labirinto lab, Cor cor) 
    {
	super(lab, cor);
    }

	@Override
	public String toString()
    {
        return "s";
    }

    @Override
    public boolean[][] movimentosPossiveis()
    {
        boolean[][] mat = new boolean[lab.linhas][lab.colunas];
        return mat;
    }
    
}
