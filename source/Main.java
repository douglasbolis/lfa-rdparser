import parser.RDParserBase;
import parser.RDParserMel;
import ui.Input;
import ui.Output;

/**
 * Classe principal `Main`.
 *
 * @author douglas.
 */
public class Main {

    /**
     * Método main para a execução da aplicação.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            /**
             * Capturando a expressão digitada.
             */
            String input = Input.read();

            /**
             * Criando uma nova instância para o objeto melRDP.
             */
            RDParserBase melRDP = new RDParserMel(input);

            /**
             * Exibindo o resultado final daexpressão fornecida.
             */
            Output.println(melRDP.parser());
        } catch (RuntimeException error) {
            /**
             * Capturação e exibição do erro ocorrido.
             */
            Output.println("Error: " + error.getMessage());
        }
    }

}
