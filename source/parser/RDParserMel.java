package parser;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Classe Recursive Descent Parser Micro Expression Language.
 * Extendida de RDParserBase.
 *
 * @author douglas.
 */
public class RDParserMel extends RDParserBase {

    public RDParserMel(String input) {
        super(input);
    }

    /**
     * Método `parser` realiza a validação da expressão de entrada e
     * se a expressão for válida executa o método `expr`.
     *
     * @return O resultado final da expressão.
     */
    @Override
    public String parser() {
        if (!this.validate()) {
            throw new RuntimeException("Malformed expression");
        }
        return this.formatResult(this.expr());
    }

    @Override
    public Double expr() {
        double result = this.term();
        while (this.accept("+") || this.accept("-")) {
            String symbol = this.getSymbol();
            switch (symbol) {
                case "+":
                    result += this.term();
                    break;
                case "-":
                    result -= this.term();
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    @Override
    public Double term() {
        double result = this.factor();
        while (this.accept("//") || this.accept("%") || this.accept("*") || this.accept("/")) {
            String symbol = this.getSymbol();
            switch (symbol) {
                case "*":
                    result *= this.factor();
                    break;
                case "/":
                    result /= this.factor();
                    break;
                case "//":
                    int resultInt = (int) (result / this.factor());
                    result = resultInt;
                    break;
                case "%":
                    result %= this.factor();
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    @Override
    public Double factor() {
        double result = this.base();
        if (this.accept("^")) {
            result = Math.pow(result, this.base());
        }
        return result;
    }

    @Override
    public Double base() {
        if (this.accept("+") || this.accept("-")) {
            String symbol = this.getSymbol();
            Double result = this.base();
            if (symbol.equals("-")) {
                result *= -1;
            }
            return result;
        } else if (this.accept("(")) {
            double result = this.expr();
            if (!this.expect(")")) {
                throw new RuntimeException("Malformed expression");
            }
            return result;
        } else {
            return this.number();
        }
    }

    @Override
    public Double number() {
        if (this.accept(Pattern.compile("^\\d+(\\.\\d*)?((E|e)(\\+|\\-)?\\d+)?"))) {
            return this.digit();
        }
        throw new RuntimeException("Malformed expression");
    }

    @Override
    public Double digit() {
        return Double.parseDouble(this.getDigitGroup());
    }

    /**
     * Método `validate` realiza a validação da contagem de parênteses e
     * a sequência de parênteses.
     *
     * @return Caso a quantidade de parênteses abrindo e fechando, e não
     * contenha a sequência `()` returna `true`, caso contrário retorna `false`.
     */
    private Boolean validate() {
        return this.validateParenthesesNumber(Pattern.compile("\\(|\\)"), this.getInput(), 0) && this.validateParenthesesSequence(this.getInput());
    }

    /**
     * Método `validateParenthesesNumber` realiza a contagem do número de
     * parênteses `(` e `)`.
     * Encontrado o `(` aumenta 1 em `count`.
     * Encontrado o `)` diminui 1 em `count`.
     *
     * @param regex Expressão regular para buscar os parênteses.
     * @param str String contendo a expressão a ser validada.
     * @param count Controle do número de parênteses encontrado.
     *
     * @return Caso `count` menor que 0 em algum momento ou ao final maior
     * que 0 returna `false`, caso contrário retorna `true`.
     */
    private Boolean validateParenthesesNumber(Pattern regex, String str, int count) {
        Matcher mat = regex.matcher(str);

        if (count < 0) {
            return false;
        } else if (str.length() == 0) {
            return count == 0;
        } else if (mat.find()) {
            return this.validateParenthesesNumber(regex, str.substring(mat.end()), mat.group().equals("(") ? count + 1 : count - 1);
        }
        return this.validateParenthesesNumber(regex, "", count);
    }

    /**
     * Método `validateParenthesesSequence` realiza a validação na string `str`
     * para a ocorrência da substring `()`.
     *
     * @param str String a ser validada.
     *
     * @return Caso ocorra a sequência `()` return `false`, caso contrário
     * return `true`.
     */
    private Boolean validateParenthesesSequence(String str) {
        Pattern pattern = Pattern.compile("\\(\\d*\\)");
        Matcher mat = pattern.matcher(str);

        Boolean finish = false;
        Boolean isValid = true;

        while (isValid && !finish) {
            if (mat.find()) {
                isValid = mat.group().length() > 2;
            } else {
                finish = true;
            }
        }

        return isValid;
    }

    /**
     * Método `formatResult` realiza a formatação do resultado.
     * Caso o número for `1.0` retorna `1`.
     * Caso o número for `1.5` retorna `1.5`.
     *
     * @param result Resultado da expressão.
     *
     * @return O resultado formatado.
     */
    private String formatResult(Double result) {
        return (result % 1.0 == 0) ? Integer.toString((int)(result / 1)) : String.valueOf(result);
    }

}
