package parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe abstrata Recursive Descent Parser Base.
 *
 * @author douglas.
 */
public abstract class RDParserBase {

    /**
     * Atributo `input` armazena a expressão de entrada.
     */
    private final String input;
    /**
     * Atributo `_input` armazena a expressão de entrada e servirá para aplicar os passos de processamento.
     */
    private String _input;
    /**
     * Atributo `digitGroup` armazena a captura dos números identificados em cada aferição.
     */
    private String digitGroup;
    /**
     * Atributo `synbol` armazena a operação aritmética aferido, como
     * por exemplo '+'.
     */
    private String symbol;

    RDParserBase(String input) {
        this.input = input;
        this._input = input;
    }

    /**
     * Método `getInput` retorna a expressão de entrada.
     *
     * @return A expressão de entrada.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Método `getDigitGroup` retorna o grupo de dígitos identificados na
     * aferição dos números.
     *
     * @return O grupo de dígitos identificados.
     */
    public String getDigitGroup() {
        return this.digitGroup;
    }

    /**
     * Método `getSymbol` retorna a operação aritmética aferida.
     *
     * @return A operação aritmética aferida.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Método `accept` verifica em `_input` se contém, em seu início, a
     * substring `str`.
     * O Atributo `symbol` recebe o conteúdo de `str`.
     *
     * @param str String a ser verficada.
     *
     * @return A validação da substring `str` no início de `_input`.
     */
    protected Boolean accept(String str) {
        if (this._input.startsWith(str)) {
            this._input = this._input.substring(str.length()).trim();
            this.symbol = str;
            return true;
        }

        return false;
    }

    /**
     * Método `accept` verifica em `_input` se há alguma ocorrência de acordo
     * com a expressão regular `pat`.
     * O Atributo `symbol` recebe a ocorrência capturada pela expressão
     * regular `pat`.
     *
     * @param pat Expressão regular para buscar alguma ocorrência.
     * @return A validação da substring `str` no início de `_input`.
     */
    protected Boolean accept(Pattern pat) {
        Matcher mat = pat.matcher(this._input);
        Boolean find = mat.find();

        if (find) {
            this._input = this._input.substring(mat.end()).trim();
            this.digitGroup = mat.group();
        }

        return find;
    }

    /**
     * Método `except` verifica se `str` pertence à expressão de entrada.
     * E retorna `true` caso pertença e `false` caso contrário.
     *
     * @param str String a ser verificada.
     * @return A validação caso a `str` pertença à expressão de entrada.
     */
    protected Boolean expect(String str) {
        return this.accept(str);
    }

    /**
     * Método `expect` verifica em `_input` se há alguma ocorrência de acordo
     * com a expressão regular `pat`.
     * E retorna `true` caso haja ocorrência  e `false` caso contrário.
     *
     * @param pat String a ser verificada.
     * @return A validação caso `str` pertença à expressão de entrada.
     */
    protected Boolean expect(Pattern pat) {
        return this.accept(pat);
    }

    /**
     * Método abstrato `parser`.
     * Execução de validações e o método `expr`.
     *
     * @return
     */
    public abstract String parser();

    /**
     * Método abstrato `expr`.
     * <exor> ::= <term>(('+'|'-')<term>)*
     *
     * @return
     */
    public abstract Double expr();

    /**
     * Método abstrato `term`.
     * <term> ::= <factor>(('*'|'/'|'//'|'%')<factor>)*
     *
     * @return
     */
    public abstract Double term();

    /**
     * Método abstrato `factor`.
     * <factor> ::= <base>('^'<factor>)?
     *
     * @return
     */
    public abstract Double factor();

    /**
     * Método abstrato `base`.
     * <base> ::= ('+'|'-')<base>
     *          | <number>
     *          | '('<expr>')'
     *
     * @return
     */
    public abstract Double base();

    /**
     * Método abstrato `number`.
     * <number> ::= <digit>+'.'?<digit>*(('E'|'e')('+'|'-')?<digit>+)?
     *
     * @return
     */
    public abstract Double number();

    /**
     * Método abstrato `digit`.
     * <digit> ::= ‘0’ | ‘1’ | ‘2’ | ‘3’ | ‘4’ | ‘5’ | ‘6’ | ‘7’ | ‘8’ | ‘9’
     *
     * @return
     */
    public abstract Double digit();

}
