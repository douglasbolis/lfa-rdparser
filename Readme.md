# LFA - Parser Descendente Recursivo

Este trabalho consiste na implementação de um parser descendente recursivo para uma Linguagem Livre de Contexto chamada _**MEL**(Micro Expression Language)_.

O trabalho em questão foi nos passado na disciplina _LFA(Linguagens Formais e Autômatos)_ do curso de graduação Bach. Sistemas de Informação do IFES-Serra pelo Professor Jefferson Andrade.

## Autor

Douglas Bolis Lima

## Gramática

A linguagem _MEL_ é a linguagem gerada pela gramática abaixo em notação [EBNF](https://en.wikipedia.org/wiki/Extended_Backus%E2%80%93Naur_form):

```html
<expr>   ::= <term> ((‘+’ | ‘-’) <term>)*
<term>   ::= <factor> ((‘*’ | ‘/’ | ‘//’ | ‘%’) <factor>)*
<factor> ::= <base> (‘^’ <factor>)?
<base>   ::= (‘+’ | ‘-’) <base>
           |  <number>
           |  ‘(’ <expr> ‘)’
<number> ::= <digit>+ ‘.’? <digit>* ((‘E’ | ‘e’)(‘+’ | ‘-’)? <digit>+)?
<digit>  ::= ‘0’ | ‘1’ | ‘2’ | ‘3’ | ‘4’ | ‘5’ | ‘6’ | ‘7’ | ‘8’ | ‘9’
```

## Ambiente de Desenvolvimento

Este trabalho foi desenvolvido utilizando a linguagem de programação [JAVA_v8.0](https://www.java.com/pt_BR/download/faq/java8.xml), a IDE [Visual Studio Code](https://code.visualstudio.com) e o OS [Linux Pop!_OS 19.04](https://system76.com/pop).

## Código Fonte

No diretório [_source_](./source) se encontram os códigos fonte do trabalho e contém os arquivos:

Arquivo|Path|Descrição
---|---|---
**Main.java**|source/Main.java|Arquivo contendo a classe principal **`Main`** que realiza a captura da expressão de entrada do programa, a chamada para o seu processamento e a exibição do resultado final da expressão.
**RDParserBase.java**|source/parser/RDParserBase.java| Arquivo contendo a classe abstrata **`RDPParserBase`** com as definições dos métodos `accept` e `expect`, ambos com parâmetros de entrada com os tipos `String` ou `Pattern`, esse segundo um tipo para a aplicação de expressões regulares.<br> A classe abstrata **`RDParserBase`** também possui métodos abstratos que quando for extendida por outras classes devem ser implementados.
**RDParserMel.java**|source/parser/RDParserMel.java|Arquivo contendo a classe **`RDParserMel`** que contém a implementação da gramática descrita no enunciado do trabalho apresentado pelo professor.<br> Esta classe extende a classe abstrata `RDParserBase` e implementa seus métodos abstratos `parser`, `expr`, `term`, `factor`, `base`, `number` e `digit` além de implementar outros métodos como `validate`, `validateParenthesesNumber` e `validateParenthesesSequenc` que valida as condições do uso de parênteses na expressão de entrada e `formatResult` que 'formata' o resultado final da expressão para retornar um número inteiro caso o mesmo seja.
**Input.java**|source/ui/Input.java|Arquivo contendo a classe **`Input`** que é utilizada para a capturação de mensagens do teclado do usuário. A classe possui os métodos estáticos `read` e `readln`. O método `read` pode ou não receber uma mensagem do tipo `String` como entrada, imprime-a na tela e espera a entrada de uma mensagem pelo usuário na mesma linha da mensagem exibida. O método `readln` recebe uma mensagem do tipo `String` como entrada, imprime-a na tela e espera a entrada de uma mensagem pelo usuário na linha de baixo da mensagem exibida.
**Output.java**|source/ui/Output.java|Arquivo contendo a classe **`Output`** que é utilizada para exibição de mensagens na tela do usuário. A classe possui os métodos estáticos `print` e `println` que recebem uma mensagem do tipo `String` como entrada e imprime-a na tela. Esses dois métodos remetem aos métodos `print` e `println` do Java.
**Makefile**|source/Makefile|Arquivo contendo as rotinas de compilação, construção e execução do programa desenvolvido.

## Execução do Programa

Para facilitar a execução do programa foi adicionado ao trabalho um arquivo de _build_ com algumas rotinas de execução como:

Rotina|Descrição
---|---
**build**|Esta rotina primeiramente remove a compilação anterior(caso exista) e compila os códigos fontes do trabalho novamente e contrói o programa.
**run**|Esta rotina, após o programa contruído, o executa.
**clean**|Esta rotina remove os arquivos .class e .jar gerados na compilação e contrução do programa.

Obs.: Executando `make` na linha de comando a rotina `build` será executada.

### Passos para execução do programa

#### Passo 1:
O primeiro passo consiste no acesso ao diretório `source`.
```shell
$ cd source
```

#### Passo 2:
O segundo passo consiste na compilação e construção do programa.
```shell
$ make
```
ou
```shell
$ make build
```
Após essa rotina ser finalizada os arquivos gerados na compilação estarão contidos no diretório `dist` e o programa construído estará no mesmo diretório do _Makefile_.
```
source
|_ Makefile
|_ trab1.jar
|_ dist
   |_ Main.class
   |_ parser
      |_ RDParserBase.class
      |_ RDParserMel.class
   |_ ui
      |_ Input.class
      |_ Output.class
```

#### Passo 3:
O terceiro passo consiste na execução do programa construído.
```shell
$ make run
```
Na execução dessa rotina o comportamento do programa será como pedido no enunciado do trabalho e a interação com o programa se dará da forma como descrita pelo professor.

Exemplo:
```shell
$ make run
10 * 5 + 100 / 10 - 5 + 7 % 2
56
```
