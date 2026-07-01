# ProjetoFisica - Potencial eletrico no centro do retangulo

<img width="855" height="457" alt="Captura de tela 2026-06-30 112152" src="https://github.com/user-attachments/assets/fba13db8-e40b-46a4-ac3d-cf5b0c51289a" />

## Descricao do exercicio

Este projeto resolve o exercicio da figura 24.14, no qual ha um arranjo retangular de cargas fixas e se deseja encontrar o potencial eletrico no centro do retangulo, com `V = 0` no infinito.

       +2q1          (1)q2          -3q1
        * - - - - - - * - - - - - - *
        |      a             a      |
      a |                           | a
        |                           |
        |      a             a      |
        * - - - - - - * - - - - - - *
       -q1           (2)q2          +2q1

Os dados do enunciado sao:

- `a = 39,0 cm` no exemplo original
- `q1 = 3,40 pC`
- `q2 = 6,00 pC`
- cargas nos cantos: `+2q1`, `-3q1`, `-q1`, `+2q1`
- cargas nos pontos medios: Multiplicadores variaveis `(1)q2` e `(2)q2` definidos pelo usuario

## Resolucao fisica

O potencial eletrico de uma carga puntiforme e dado por:
`V = kq/r`

Como potencial e grandeza escalar, a soma total e feita por superposicao:
`V_total = soma(kqi/ri)`

No centro do retangulo:

- a distancia ate qualquer canto e `r_cantos = (a * sqrt(5)) / 2`
- a distancia ate qualquer ponto medio superior ou inferior e `r_meios = a / 2`

Substituindo as cargas do problema (onde `n` e `n1` sao os multiplicadores):
`V = k * [(2q1 - 3q1 - q1 + 2q1)/r_cantos + (n*q2 + n1*q2)/r_meios]`

Os termos dos cantos se cancelam:
`2q1 - 3q1 - q1 + 2q1 = 0`

Logo, sobra apenas a contribuicao das cargas centrais escolhidas pelo usuario:
`V = k * (n*q2 + n1*q2) / (a/2)`

Que no codigo (convertendo `a` para metros) e calculado como:
`V = [ (n * K * Q2) / (a / 2) + (n1 * K * Q2) / (a / 2) ] * 100`

## Regras de entrada

O programa aceita apenas:

- numeros validos
- `a > 0`
- para evitar valores sem sentido neste exercicio, foi colocado um limite superior de `1000 cm` para `a`
- para evitar objetos macroscopicos irreais, o limite dos multiplicadores (1) e (2) de q2 deve estar entre `-100` e `100`

## Funcionalidades

- interface grafica em Java Swing com exibicao de texto e diagrama ASCII
- campos independentes para informar a distancia `a` e os multiplicadores `n` e `n1` de `q2`
- exibicao da formula matematica montada passo a passo com os valores do usuario
- exibicao do potencial final em volts
- tratamento de erro para campos vazios, textos invalidos e valores fora do intervalo
- botoes `Calcular` e `Limpar`

## Resultado esperado com o exemplo do enunciado

Ao informar `a = 39` e os multiplicadores `4` e `4` (conforme o exercicio original), a interface deve mostrar a formula substituida e o resultado final de aproximadamente:

- `V = 2,2123 V`
