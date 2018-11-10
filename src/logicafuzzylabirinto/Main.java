package logicafuzzylabirinto;

import fuzzy.LogicaFuzzy;
import fuzzy.RoboEmAcao;
import labirinto.Posicao;

public class Main {

    public static void main(String[] args) {
        RoboEmAcao campo = new RoboEmAcao();
        Posicao origem = new Posicao(0, 0);
        Posicao destino = new Posicao(0, 0);
        LogicaFuzzy logicaFuzzy = new LogicaFuzzy(campo);

        int i;

        while (!campo.fim)
        {
            i = 1;

            while (i < 9 && !campo.saidaEncontrada)
            {
                Tela.imprimirLabirinto(campo);

                campo.moveSensor(destino);
                delay(200);
                i++;
            }

            campo.paraSensor(destino);
            origem = new Posicao(destino.linha, destino.coluna);

            if (campo.saidaEncontrada)
            {
                campo.naPosicao(origem);
            }

            if (!campo.fim)
            {
            	destino = logicaFuzzy.fuzzy(origem);
                destino = campo.portaSaida(origem, destino);
                campo.moveRobo(origem, destino);
            }  
        }
        
        Tela.imprimirLabirinto(campo);
    }
    static private void delay(int time_ms)
    {
    	try 
    	{
        Thread.sleep(time_ms);
        } catch (Exception e) {
		   //e.printStackTrace();
        }
    }
}
