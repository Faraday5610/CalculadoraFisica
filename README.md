# ProjetoFisica - Potencial eletrico no centro do retangulo

## Descricao do exercicio

Este projeto resolve o exercicio da figura 24.14, no qual ha um arranjo retangular de cargas fixas e se deseja encontrar o potencial eletrico no centro do retangulo, com `V = 0` no infinito.

       +2q1             +4q2               -3q1
        * - - - - - - - - * - - - - - - - - *
        |        a                 a        |
        |                                   |
      a |                                   | a
        |                                   |
        |        a                 a        |
        * - - - - - - - - * - - - - - - - - *
       -q1              +4q2               +2q1

Os dados do enunciado sao:

- `a = 39,0 cm` no exemplo original
- `q1 = 3,40 pC`
- `q2 = 6,00 pC`
- cargas nos cantos: `+2q1`, `-3q1`, `-q1`, `+2q1`
- cargas nos pontos medios: `+4q2` e `+4q2`

## Resolucao fisica

O potencial eletrico de uma carga puntiforme e dado por:

`V = kq/r`

Como potencial e grandeza escalar, a soma total e feita por superposicao:

`V_total = soma(kqi/ri)`

No centro do retangulo:

- a distancia ate qualquer canto e `r_cantos = (a * sqrt(5)) / 2`
- a distancia ate qualquer ponto medio superior ou inferior e `r_meios = a / 2`

Substituindo as cargas do problema:

`V = k * [(2q1 - 3q1 - q1 + 2q1)/r_cantos + (4q2 + 4q2)/r_meios]`

Os termos dos cantos se cancelam:

`2q1 - 3q1 - q1 + 2q1 = 0`

Logo, sobra apenas a contribuicao das cargas `+4q2`:

`V = k * (8q2) / (a/2)`

`V = 16*k*q2/a`

Para `a = 39,0 cm = 0,390 m`:

- `V_total = 2,2123 V`

## Regras de entrada

O programa aceita apenas:

- numeros validos
- `a > 0`
- para evitar valores sem sentido neste exercicio, foi colocado um limite superior de `1000 cm`

## Funcionalidades

- interface grafica em Java Swing
- campo unico para informar `a`
- exibicao do potencial final em volts
- tratamento de erro para campo vazio, texto invalido e valor fora do intervalo
- botoes `Calcular` e `Limpar`

## Resultado esperado com o exemplo do enunciado

Ao informar `a = 39,0`, a interface deve mostrar aproximadamente:

- `Potencial devido aos cantos = 0,0000 V`
- `Potencial devido aos pontos medios = 2,2123 V`
- `Potencial eletrico total = 2,2123 V`
