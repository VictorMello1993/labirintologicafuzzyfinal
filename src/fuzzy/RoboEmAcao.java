package fuzzy;

import labirinto.Componente;
import labirinto.Cor;
import labirinto.Labirinto;
import labirinto.Posicao;

public class RoboEmAcao {
    public Labirinto lab;
    public boolean fim;
    public boolean saidaEncontrada;
    public int sensor;
    public int sensorAtual;
    public int sensorAcance;

    public RoboEmAcao(Labirinto lab, boolean fim, boolean saidaEncontrada, int sensor, int sensorAtual, int sensorAcance) {
        lab = new Labirinto(50,50);
        Componente pc = new Sensor(lab, Cor.VERMELHO);
        colocarPecas();
        saidaEncontrada = false;
        sensor = 0;
        this.sensorAcance = pc.alcance;
       
    }

    private void colocarPecas() {
        int lin, col;

        for (int i = 0; i < 5; i++)
        {
            lin = (int)(Math.random() * (24 + 1)); //(0, 24);
            col = (int)(Math.random() * (24 + 1)); //(0, 24);
            instancieL(lin, col);

            lin = (int)(25 + Math.random() * (45 - 25 + 1)); //(25, 45);
            col = (int)(25 + Math.random() * (45 - 25 + 1)); //(25, 45);
            instancieL(lin, col);

            lin = (int)(Math.random() * (24 + 1)); //(0, 24);
            col = (int)(25 + Math.random() * (45 - 25 + 1)); //(25, 45);
            instancieL(lin, col);

            lin = (int)(25 + Math.random() * (45 - 25 + 1)); //(24, 45);
            col = (int)(Math.random() * (24 + 1)); //(0, 24);
            instancieL(lin, col);
        }

        lab.colocarComponente(new Robo(lab, Cor.VERDE), new Posicao(0, 0));
        col = (int)(Math.random() * (lab.colunas)); //(0, lab.colunas-1);
        lab.colocarComponente(new Porta(lab, Cor.AZULCLARO), new Posicao(lab.linhas-1, col));
    }   

    private void instancieL(int lin, int col) {
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col + 1));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col + 2));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col + 3));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col + 4));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin + 1, col));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin + 2, col));
    }
    
    private boolean instancieS(Posicao pos){
    if (!lab.barreira(pos))
    {
        if (!(lab.componente(pos) != null && lab.componente(pos).cor == Cor.AZULCLARO))
        {
            lab.colocarComponente(new Sensor(lab, Cor.VERMELHO), pos);
            return true;
        }
    }
    return false;
    }
    
    private void ligaSensor(Posicao origem)
    {
        Posicao pos;

        switch (sensor)
        {
            case 0:
                desligaSensor(origem);
                break;
            case 1:
                for (int i = 1; i < sensorAcance; i++) //S
                {
                    pos = new Posicao(origem.linha + i, origem.coluna);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 1; i < sensorAcance; i++) //SE
                {
                    pos = new Posicao(origem.linha + i, origem.coluna + i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 1; i < sensorAcance; i++)// E
                {
                    pos = new Posicao(origem.linha, origem.coluna + i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 1; i < sensorAcance; i++)//NE
                {
                    pos = new Posicao(origem.linha - i, origem.coluna + i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 5:
                for (int i = 1; i < sensorAcance; i++)//N
                {
                    pos = new Posicao(origem.linha - i, origem.coluna);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 6:
                for (int i = 1; i < sensorAcance; i++)//NO
                {
                    pos = new Posicao(origem.linha - i, origem.coluna - i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 7:
                for (int i = 1; i < sensorAcance; i++)//O
                {
                    pos = new Posicao(origem.linha, origem.coluna - i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 8:
                for (int i = 1; i < sensorAcance; i++)
                {
                    pos = new Posicao(origem.linha + i, origem.coluna - i); //SO
                   
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
        }
    }

    
    private void destruirS(int lin, int col)
    {
        Posicao pos = new Posicao(lin, col);

        if (!lab.barreira(pos))
        {
            if (!(lab.componente(pos) != null && lab.componente(pos).cor == Cor.AZULCLARO))
            {
                lab.retirarComponente(pos);
            }
        }
    }
    
       private void desligaSensor(Posicao origem)
    {
        switch (sensor)
        {
            case 1:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha + i, origem.coluna); //S
                }
                break;
            case 2:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha + i, origem.coluna + i); //SE
                }
                break;
            case 3:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha, origem.coluna + i); // E
                }
                break;
            case 4:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha - i, origem.coluna + i); //NE
                }
                break;
            case 5:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha - i, origem.coluna); //N
                }
                break;
            case 6:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha - i, origem.coluna - i); //NO
                }
                break;
            case 7:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha, origem.coluna - i); //O
                }
                break;
            case 8:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha + i, origem.coluna - i); //SE
                }
                break;
        }
    }
}
