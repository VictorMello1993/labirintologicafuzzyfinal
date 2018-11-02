package logicafuzzylabirinto;

import fuzzy.RoboEmAcao;
import java.util.Scanner;
import labirinto.Componente;
import labirinto.Posicao;

public class Tela {
    public static void imprimirLabirinto(RoboEmAcao campo){
        
     clearScreen();
		Scanner ler = new Scanner(System.in);
		/*
		Console s_console = Enigma.getConsole(); 
        TextAttributes attrs;
        
        attrs = new TextAttributes(new Color(96,223,79), new Color(76,62,175));
        s_console.setTextAttributes(attrs);
        
		
		ConsoleColor aux = Console.ForegroundColor;
        Console.ForegroundColor = ConsoleColor.Blue;
        */
        System.out.println("");
        System.out.println(" \\C 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 4 4 4 4 4");
        System.out.println(" L\\ 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9");
        //Console.ForegroundColor = aux;

        for (int i=0; i<campo.lab.linhas; i++)
        {
            //Console.ForegroundColor = ConsoleColor.Magenta;

            if (i < 10)
            {
            	System.out.print(" 0"+i + " ");
            }
            else
            {
            	System.out.print(" "+i + " ");
            }

            //Console.ForegroundColor = aux;

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

            //Console.ForegroundColor = ConsoleColor.Magenta;

            if (i < 10)
            {
            	System.out.print("0" + i + " ");
            }
            else
            {
            	System.out.print(i + " ");
            }

            System.out.println("");
            //Console.ForegroundColor = aux;
            
            if (campo.fim)
            {
            	 System.out.println("Fim - O robo encontrou a saída!");
            	 ler.nextLine();
            	 ler.close();
            }
        }

        //Console.ForegroundColor = ConsoleColor.Blue;
        System.out.println("    0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 4 4 4 4 4 \\L");
        System.out.println("    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 C\\");

        //Console.ForegroundColor = aux;
    }
    
    public static void imprimirLabirinto(RoboEmAcao campo, Posicao pos)
    {
    	clearScreen();
    	Scanner ler = new Scanner(System.in);
    	//ConsoleColor fundoOriginal = Console.BackgroundColor;
        //ConsoleColor fundoAlterado = ConsoleColor.DarkGray;

        boolean[][] posicoesPossiveis = campo.lab.componente(pos).movimentosPossiveis();

        //ConsoleColor aux = Console.ForegroundColor;
        //Console.ForegroundColor = ConsoleColor.Blue;
        System.out.println();
        System.out.println("\\C 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 4 4 4 4 4");
        System.out.println("L\\ 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9");
        //Console.ForegroundColor = aux;

        for (int i = 0; i < campo.lab.linhas; i++)
        {  
            //Console.ForegroundColor = ConsoleColor.Magenta;

            if (i < 10)
            {
            	System.out.print(" 0" + i + " ");
            }
            else
            {
            	System.out.print(" " + i + " ");
            }

            //Console.ForegroundColor = aux;

            for (int j = 0; j < campo.lab.colunas; j++)
            {
                if (posicoesPossiveis[i][j])
                {
                    //Console.BackgroundColor = fundoAlterado;
                }
                else
                {
                    //Console.BackgroundColor = fundoOriginal;
                }

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

            //Console.ForegroundColor = ConsoleColor.Magenta;

            if (i < 10)
            {
            	System.out.print("0" + i + " ");
            }
            else
            {
            	 System.out.print(i + " ");
            }

            System.out.println("");
            //Console.ForegroundColor = aux;
        }

        //Console.ForegroundColor = ConsoleColor.Blue;
        System.out.println("    0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 4 4 4 4 4 \\L");
        System.out.println("    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 C\\");

        //Console.ForegroundColor = aux;
        //Console.BackgroundColor = fundoOriginal;
        
        if (campo.fim)
        {
        	System.out.println("Fim - O robo encontrou a saída!");
        	ler.nextLine();
        	ler.close();
        }
    }
    
    public static void imprimirPeca(Componente componente)
    {
        //ConsoleColor aux = Console.ForegroundColor;

        switch (componente.cor)
        {
            case BRANCO:
                //Console.ForegroundColor = ConsoleColor.White;
                break;
            case AMARELO:
                //Console.ForegroundColor = ConsoleColor.Yellow;
                break;
            case AZUL:
                //Console.ForegroundColor = ConsoleColor.Blue;
                break;
            case MAGENTA:
                //Console.ForegroundColor = ConsoleColor.Magenta;
                break;
            case PRETO:
                //Console.ForegroundColor = ConsoleColor.Black;
                break;
            case VERDE:
                //Console.ForegroundColor = ConsoleColor.Green;
                break;
            case VERMELHO:
                //Console.ForegroundColor = ConsoleColor.Red;
                break;
            case AZULCLARO:
                //Console.ForegroundColor = ConsoleColor.Cyan;
                break;
        }

        System.out.print(componente);
        //Console.ForegroundColor = aux;
    }
    
    public static void clearScreen() 
    { 
	    char esc = 27; 
	    String clear = esc + "[2J"; //codigo ansi para limpar a tela 
	    System.out.print(clear); 
    }   
}

