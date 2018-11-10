package logicafuzzylabirinto;

import fuzzy.RoboEmAcao;
import labirinto.Componente;

public class Tela {
    public static void imprimirLabirinto(RoboEmAcao campo){
        
        clearScreen();
        System.out.println("");
        
        //Se o programa terminar, não executar mais os loopings para não imprimir as bordas do labirinto para melhor visualização 
    if(!campo.fim) {
        
        System.out.println(" \\C 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 4 4 4 4 4");
        System.out.println(" L\\ 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9");
              
        for (int i=0; i<campo.lab.linhas; i++)
        {

            if (i < 10)
            {
            	System.out.print(" 0"+i + " ");
            }
            else
            {
            	System.out.print(" "+i + " ");
            }

            for (int j=0; j<campo.lab.colunas; j++)
            {
                if (campo.lab.componente(i, j) == null)
                {
                	System.out.print("- ");
                }
                else
                {
                    imprimirPeca(campo.lab.componente(i, j));
                    System.out.print(" ");
                }  
            }

            if (i < 10)
            {
            	System.out.print("0" + i + " ");
            }
            else
            {
            	System.out.print(i + " ");
            }
            
            System.out.println("");
        }
        
        System.out.println("    0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 4 4 4 4 4 \\L");
        System.out.println("    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 C\\");
        
    } else {
        System.out.println("Fim - O robo encontrou a saída!");
    }        
}
    
    public static void imprimirPeca(Componente componente)
    {
        switch (componente.cor)
        {
            case BRANCO:
                break;
            case AMARELO:
                break;
            case AZUL:
                break;
            case MAGENTA:
                break;
            case PRETO:
                break;
            case VERDE:
                break;
            case VERMELHO:
                break;
            case AZULCLARO:
                break;
        }
        
        System.out.print(componente);
    }
    
    public static void clearScreen() 
    { 
	    char esc = 27; 
	    String clear = esc + "[2J"; //codigo ansi para limpar a tela 
	    System.out.print(clear); 
    }   
}

