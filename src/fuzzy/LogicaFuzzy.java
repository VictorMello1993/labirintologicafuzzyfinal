package fuzzy;

import labirinto.Posicao;

public class LogicaFuzzy {
    private int sensorSul;
    private int sensorLeste;
    private int sensorOeste;
    private int sensorNorte;

    private double[] uSul;
    private double[] uLeste;
    private double[] uNorte;
    private double[] uOeste;

    private Distancia distanciaSul;
    private Distancia distanciaLeste;
    private Distancia distanciaNorte;
    private Distancia distanciaOeste;

    private double fuzzySaida;
    private Rota rota;
    private int deslocamento;
    private RoboEmAcao campo;
    private int limiteSensor;
    private int limiteLinhas;
    private int limiteColunas;

    private int teste;

    public LogicaFuzzy(RoboEmAcao campoRobo)
    {
        campo = campoRobo;
        limiteSensor = campo.sensorAcance;
        limiteLinhas = campo.lab.linhas;
        limiteColunas = campo.lab.colunas;

        uSul = new double[] { 0.0, 0.0, 0.0,};
        uLeste = new double[] { 0.0, 0.0, 0.0, };
        uNorte = new double[] { 0.0, 0.0, 0.0, };
        uOeste = new double[] { 0.0, 0.0, 0.0, };

        rota = Rota.SEMROTA;
    }

    private void crispEntrada(Posicao pos)
    {
        sensorSul = 0;
        sensorNorte = 0;
        sensorLeste = 0;
        sensorOeste = 0;

        // mapa de entrada
        boolean[][] mapa = campo.lab.componente(pos).movimentosPossiveis();

        // sensor sul
        for (int i=1; i <= limiteSensor; i++)
        {
            if (pos.linha + i < limiteLinhas && mapa[pos.linha+i][pos.coluna])
            {
                sensorSul = i;
            }
            else break;
        }

        // sensor leste
        for (int i = 1; i <= limiteSensor; i++)
        {
            if (pos.coluna + i < limiteColunas && mapa[pos.linha][pos.coluna+i])
            {
                sensorLeste = i;
            }
            else break;
        }

        // sensor norte
        for (int i = 1; i <= limiteSensor; i++)
        {
            if (pos.linha - i >= 0 && mapa[pos.linha-i][pos.coluna])
            {
                sensorNorte = i;
            }
            else break;
        }

        // sensor oeste
        for (int i = 1; i <= limiteSensor; i++)
        {
            if (pos.coluna - i >= 0 && mapa[pos.linha][pos.coluna - i])
            {
                sensorOeste = i;
            }
            else break;
        }
    }

    private double fuzzyPerto(int x)
    {
        double a = 0.0, b = 0.0, c = 0.0, u = 0.0;

        a = 1;
        b = (limiteSensor-a) / 2.0 + a;
        c = limiteSensor;

        if (x < 1) u = 0.0;

        if (x > 0 && x <= b) u = (b - x) / (b - a);           

        return u;
    }

    private double fuzzyLonge(int x)
    {
        double a = 0.0, b = 0.0, c = 0.0, u = 0.0;

        a = 1;
        b = (limiteSensor - a) / 2.0 + a;
        c = limiteSensor;

        if (x <= b) u = 0.0;

        if (x >= b && x < c) u = (c - x) / (b - a);

        if (x >= c) u = 1.0;

        return u;
    }

    private double fuzzyMediano(int x)
    {
        double a = 0.0, b = 0.0, c = 0.0, u = 0.0;

        a = 1.0;
        b = (limiteSensor - a) / 2.0 + a;
        c = limiteSensor;

        if (x < 1) u = 0.0;
        if (x >= c) u = 0.0;

        if (x >= 1 && x <= b) u = (x - a) / (b - a);
        if (x >= b && x <= c) u = (c - x) / (c - b);

        return u;
    }

    private double fuzzy_Or(double a, double b)
    {
        if (a > b) return a;
        else return b;
    }

    private double fuzzy_And(double a, double b)
    {
        if (a < b) return a;
        else return b;
    }

    private double fuzzy_Not(double a)
    {
        return 1 - a;
    }

    private Distancia fuzzyDistancia(double[] dist)
    {
        Distancia valorLing = Distancia.BLOQUEADA;

        if (fuzzy_Not(fuzzy_And(fuzzy_And (dist[0], dist[1]), dist[2]))>=1)
        {
            valorLing = Distancia.BLOQUEADA;
        }

        if (dist[0] > 0 && fuzzy_Not(fuzzy_And(dist[1], dist[2]))>=1)
        {
            valorLing = Distancia.MUITOPERTO;
        }

        if (dist[0] > 0 && dist[1] > 0 && fuzzy_Not(dist[2])>=1)
        {
            valorLing = Distancia.PERTO;
        }

        if (fuzzy_Not(dist[0]) >= 1 && dist[1] >= 1 && fuzzy_Not(dist[2]) >= 1)
        {
            valorLing = Distancia.MEDIA;
        }

        if (fuzzy_Not(dist[0]) >= 1 && dist[1] > 0 && dist[2] > 0)
        {
            valorLing = Distancia.LONGE;
        }

        if (fuzzy_Not(dist[0]) >= 1 && fuzzy_Not(dist[1]) >= 1 && dist[2] == 1 )
        {
            valorLing = Distancia.MUITOLONGE;
        }

        return valorLing;
    }

    

    private void fuzificar()
    {
        uSul[0] = fuzzyPerto(sensorSul);
        uSul[1] = fuzzyMediano(sensorSul);
        uSul[2] = fuzzyLonge(sensorSul);

        uLeste[0] = fuzzyPerto(sensorLeste);
        uLeste[1] = fuzzyMediano(sensorLeste);
        uLeste[2] = fuzzyLonge(sensorLeste);

        uNorte[0] = fuzzyPerto(sensorNorte);
        uNorte[1] = fuzzyMediano(sensorNorte);
        uNorte[2] = fuzzyLonge(sensorNorte);

        uOeste[0] = fuzzyPerto(sensorOeste);
        uOeste[1] = fuzzyMediano(sensorOeste);
        uOeste[2] = fuzzyLonge(sensorOeste);

        
    }

    private void fuzzyRegra()
    {      
        Rota rotaAnterior = rota;
        
        // Regra 0
        if (distanciaLeste != Distancia.BLOQUEADA &&
            rotaAnterior == Rota.SEMROTA) rota = Rota.LESTE;
                    
        // Regra 1
        if (distanciaSul != Distancia.BLOQUEADA &&
            rotaAnterior != Rota.SEMROTA) rota = Rota.SUL;

        // Regra 2
        if (distanciaSul == Distancia.BLOQUEADA &&
            distanciaOeste != Distancia.BLOQUEADA &&
            rotaAnterior != Rota.LESTE) rota = Rota.OESTE;

        // Regra 3
        if (distanciaSul == Distancia.BLOQUEADA &&
            distanciaOeste == Distancia.BLOQUEADA ||
            (distanciaSul == Distancia.BLOQUEADA &&
            distanciaOeste != Distancia.BLOQUEADA &&
            rotaAnterior == Rota.LESTE)) rota = Rota.LESTE;

        // Regra 4
        if (distanciaSul == Distancia.BLOQUEADA &&
            distanciaOeste != Distancia.BLOQUEADA &&
            distanciaLeste == Distancia.BLOQUEADA &&
            rotaAnterior == Rota.LESTE) rota = Rota.OESTE;
    }

    public void fuzzyInfere()
    {
        distanciaSul   = fuzzyDistancia(uSul);
        distanciaLeste = fuzzyDistancia(uLeste);
        distanciaNorte = fuzzyDistancia(uNorte);
        distanciaOeste = fuzzyDistancia(uOeste);

        fuzzyRegra();
    }

    public void desfuzificar()
    {
        fuzzySaida = 0;
        //fuzzySaida = (sensorSul * uSul[0] + sensorSul * uSul[1] + sensorSul * uSul[2]) / (uSul[0] + uSul[1] + uSul[2]);

        switch (rota)
        {
            case SEMROTA:
                fuzzySaida = 0;
                break;
            case SUL:
                fuzzySaida = (sensorSul * uSul[0] + sensorSul * uSul[1] + sensorSul * uSul[2]) / (uSul[0] + uSul[1] + uSul[2]);
                break;
            case LESTE:
                fuzzySaida = (sensorLeste * uLeste[0] + sensorLeste * uLeste[1] + sensorLeste * uLeste[2]) / (uLeste[0] + uLeste[1] + uLeste[2]);
                break;
            case NORTE:
                fuzzySaida = (sensorNorte * uNorte[0] + sensorNorte * uNorte[1] + sensorNorte * uNorte[2]) / (uNorte[0] + uNorte[1] + uNorte[2]);
                break;
            case OESTE:
                fuzzySaida = (sensorOeste * uOeste[0] + sensorOeste * uOeste[1] + sensorOeste * uOeste[2]) / (uOeste[0] + uOeste[1] + uOeste[2]);
                break;
        }

    }

    public Posicao CrispSaida(Posicao pos)
    {
        Posicao crispPosicao = new Posicao(pos.linha, pos.coluna);

        deslocamento = (int)Math.ceil(fuzzySaida);
        
        if (deslocamento < 0) deslocamento = 0;

        switch (rota)
        {
            case SUL:
                crispPosicao.linha = pos.linha + deslocamento;
                break;
            case LESTE:
                crispPosicao.coluna = pos.coluna + deslocamento;
                break;
            case NORTE:
                crispPosicao.linha = pos.linha - deslocamento;
                break;
            case OESTE:
                crispPosicao.coluna = pos.coluna - deslocamento;
                break;
            case SEMROTA:
                break;
        }

        return crispPosicao;
    }

    public Posicao fuzzy(Posicao posOrigem)
    {
    	Posicao posDestino;

        crispEntrada(posOrigem);
        fuzificar();
        fuzzyInfere();
        desfuzificar();

        posDestino = CrispSaida(posOrigem);

        return campo.portaSaida(posOrigem, posDestino);
    }
}
