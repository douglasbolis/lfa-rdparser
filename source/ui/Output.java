package ui;

/**
 * Classe `Output` para exibição de mensagens de retono na tela.
 *
 * @author douglas.
 */
public class Output {

    /**
     * Método `print` exibe a mensagem `msg` na tela sem quebra de linha.
     *
     * @param msg Mensagem as ser exibida.
     */
    public static void print(String msg) {
        System.out.print(msg);
    }

    /**
     * Método `println` exibe a mensagem `msg` na tela com quebra de linha.
     *
     * @param msg Mensagem a ser exibida.
     */
    public static void println(String msg) {
        System.out.println(msg);
    }

}
