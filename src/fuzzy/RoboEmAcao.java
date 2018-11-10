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

    public RoboEmAcao() {
        lab = new Labirinto(50,50);
        Componente pc = new Sensor(lab, Cor.VERMELHO);
        colocarPecas();
        fim = false;
        saidaEncontrada = false;
        sensor = 0;
        this.sensorAcance = pc.alcance;
    }

    public void naPosicao(Posicao origem)
    {
        if (lab.componente(origem) != null && lab.componente(origem).cor == Cor.AZULCLARO)
        {
            fim = true;
        }
        else
        {
            saidaEncontrada = false;
        }
    }

    public Posicao portaSaida(Posicao origem, Posicao destino)
    {
        Posicao teste = new Posicao(0, 0);
        int len;

        if (origem.linha == destino.linha)
        {
            teste.linha = destino.linha;

            if (origem.coluna < destino.coluna)
            {
                len = (destino.coluna - origem.coluna) + 1;

                for (int i = 0; i < len; i++)
                {
                    teste.coluna = origem.coluna + i;

                    if (lab.componente(teste) != null)
                    {
                        if (lab.componente(teste).cor == Cor.AZULCLARO)
                        {
                            saidaEncontrada = true;
                            break;
                        }
                    }
                }
            }
            else
            {
                len = (origem.coluna - destino.coluna) + 1;

                for (int i = 0; i < len; i++)
                {
                    teste.coluna = origem.coluna - i;

                    if (lab.componente(teste) != null)
                    {
                        if (lab.componente(teste).cor == Cor.AZULCLARO)
                        {
                            saidaEncontrada = true;
                            break;
                        }
                    }
                }
            }
        }
        else
        {
            teste.coluna = destino.coluna;

            if (origem.linha < destino.linha)
            {
                len = (destino.linha - origem.linha) + 1;

                for (int i = 0; i < len; i++)
                {
                    teste.linha = origem.linha + i;

                    if (lab.componente(teste) != null)
                    {
                        if (lab.componente(teste).cor == Cor.AZULCLARO)
                        {
                            saidaEncontrada = true;
                            break;
                        }
                    }
                }
            }
            else
            {
                len = (origem.linha - destino.linha) + 1;

                for (int i = 0; i < len; i++)
                {
                    teste.linha = origem.linha - i;

                    if (lab.componente(teste) != null)
                    {
                        if (lab.componente(teste).cor == Cor.AZULCLARO)
                        {
                            saidaEncontrada = true;
                            break;
                        }
                    }
                }
            }
        }

        return teste;
    }

    public void moveRobo(Posicao origem, Posicao destino)
    {
        Componente p = lab.moveRoboOrigem(origem);
        lab.moveRoboDestino(p, destino);
        if(!saidaEncontrada) ligaSensor(destino);
    }

    public void moveSensor(Posicao coord)
    { 
        if(sensor != 0) desligaSensor(coord);
        sensor++;
        if (sensor == 9) sensor = 1; 
        
        ligaSensor(coord);
    }

    public void paraSensor(Posicao coord)
    {
        desligaSensor(coord);
        sensor = 0;
    }

    private void colocarPecas()
    {
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

    private void ligaSensor(Posicao origem)
    {
        Posicao pos;

        switch (sensor)
        {
            case 0:
                desligaSensor(origem);
                break;
            case 1:
                for (int i = 1; i < sensorAcance; i++) //Sul
                {
                    pos = new Posicao(origem.linha + i, origem.coluna);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 2:
                for (int i = 1; i < sensorAcance; i++) //Sudeste
                {
                    pos = new Posicao(origem.linha + i, origem.coluna + i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 3:
                for (int i = 1; i < sensorAcance; i++) //Leste
                {
                    pos = new Posicao(origem.linha, origem.coluna + i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 4:
                for (int i = 1; i < sensorAcance; i++) //Nordeste
                {
                    pos = new Posicao(origem.linha - i, origem.coluna + i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 5:
                for (int i = 1; i < sensorAcance; i++) //Norte
                {
                    pos = new Posicao(origem.linha - i, origem.coluna);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 6:
                for (int i = 1; i < sensorAcance; i++) //Noroeste
                {
                    pos = new Posicao(origem.linha - i, origem.coluna - i);
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
            case 7:
                for (int i = 1; i < sensorAcance; i++) //Oeste
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
                    pos = new Posicao(origem.linha + i, origem.coluna - i); //Sudeste
                   
                    if (!instancieS(pos))
                    {
                        break;
                    }
                }
                break;
        }
    }

    private void desligaSensor(Posicao origem)
    {
        switch (sensor)
        {
            case 1:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha + i, origem.coluna); //Sul
                }
                break;
            case 2:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha + i, origem.coluna + i); //Sudeste
                }
                break;
            case 3:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha, origem.coluna + i); //Leste
                }
                break;
            case 4:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha - i, origem.coluna + i); //Nordeste
                }
                break;
            case 5:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha - i, origem.coluna); //Norte
                }
                break;
            case 6:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha - i, origem.coluna - i); //Noroeste
                }
                break;
            case 7:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha, origem.coluna - i); //Oeste
                }
                break;
            case 8:
                for (int i = 1; i < sensorAcance; i++)
                {
                    destruirS(origem.linha + i, origem.coluna - i); //Sudoeste
                }
                break;
        }
    }

    private void instancieL(int lin, int col)
    {
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col + 1));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col + 2));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col + 3));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin, col + 4));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin + 1, col));
        lab.colocarComponente(new Obstaculo(lab, Cor.AMARELO), new Posicao(lin + 2, col));
    }

    private boolean instancieS(Posicao pos)
    {
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
}
