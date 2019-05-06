package ui;

import java.util.Scanner;

/**
 * Classe `Input` para capturar as entradas de strings através do teclado.
 *
 * @author douglas.
 */
public class Input {

    /**
     * Realiza a captura de uma string sem exibir alguma mensagem.
     *
     * @return A string capturada.
     */
    public static String read() {
        Scanner reader = new Scanner(System.in);

        String entry = reader.nextLine();
        reader.close();

        return entry;
    }

    /**
     * Realiza a captura de uma string com a exibição da string `msg`.
     *
     * @param msg Mensagem a ser exibida.
     *
     * @return A string capturada.
     */
    public static String read(String msg) {
        Scanner reader = new Scanner(System.in);

        Output.print(msg);
        String entry = reader.nextLine();
        reader.close();

        return entry;
    }

    /**
     * Realiza a captura de uma string com a exibição da string `msg`
     * com quebra de linha.
     *
     * @param msg Mensagem a ser exibida com quebra de linha.
     *
     * @return A string capturada.
     */
    public static String readln(String msg) {
        Scanner reader = new Scanner(System.in);

        Output.println(msg);
        String entry = reader.nextLine();
        reader.close();

        return entry;
    }

}
