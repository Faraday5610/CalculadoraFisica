# ProjetoFisica - Potencial eletrico no centro do retangulo

<img width="855" height="457" alt="Captura de tela 2026-06-30 112152" src="https://github.com/user-attachments/assets/fba13db8-e40b-46a4-ac3d-cf5b0c51289a" />

## Descricao do exercicio

Este projeto resolve e expande o exercicio da figura 24.14 do livro texto, no qual ha um arranjo retangular de cargas e se deseja encontrar o potencial eletrico no centro do retangulo, com `V = 0` no infinito.

       (1)q1          (2)q2          (3)q1
        * - - - - - - * - - - - - - *
        |      a             a      |
      a |                           | a
        |                           |
        |      a             a      |
        * - - - - - - * - - - - - - *
       (4)q1          (5)q2          (6)q1

Os dados base do enunciado sao:

- `a = 39,0 cm` no exemplo original
- `q1 = 3,40 pC` (Carga base das quinas)
- `q2 = 6,00 pC` (Carga base dos centros)

Neste simulador, os multiplicadores de todas as 6 cargas podem ser definidos livremente pelo usuario.

## Resolucao fisica

O potencial eletrico de uma carga puntiforme e dado por:
`V = kq/r`

Como o potencial e uma grandeza escalar, a soma total e feita pelo principio da superposicao:
`V_total = soma(kqi/ri)`

No centro do retangulo, temos duas distancias diferentes a considerar:

- A distancia ate qualquer quina (onde estao os q1) forma a hipotenusa de um triangulo: `r_cantos = a * sqrt(5) / 2`
- A distancia ate os pontos medios superior ou inferior (onde estao os q2) e a metade da altura: `r_meios = a / 2`

Agrupando as cargas pelas suas distancias e colocando as constantes em evidencia, a formula geral implementada no codigo e:

`V = { [ (n1 + n3 + n4 + n6) * K * Q1 ] / r_cantos } + { [ (n2 + n5) * K * Q2 ] / r_meios }`

_(Onde n1 a n6 sao os multiplicadores digitados pelo usuario)._

**Nota:** No exercicio original do livro, os multiplicadores das quinas sao `+2`, `-3`, `-1` e `+2`. A soma deles resulta em zero, anulando a primeira parte da equacao. O simulador demonstra isso perfeitamente se voce utilizar esses valores!

## Regras de entrada

O programa aceita apenas:

- Numeros validos (positivos ou negativos, aceitando notacao cientifica).
- `a > 0`. Para evitar valores sem sentido de distancias astronomicas, ha um limite superior de `1000 cm` para `a`.
- Para evitar objetos macroscopicos irreais e manter o conceito de particulas pontuais, os multiplicadores (1 a 6) possuem um limite entre `-100` e `100`.

## Funcionalidades

- Interface grafica em Java Swing com layout estruturado e diagrama ASCII.
- 7 campos independentes para informar a distancia `a` e os multiplicadores de todas as particulas.
- Exibicao dinamica da formula matematica completa, montada passo a passo com os valores substituidos e formatados com sinais corretos (+/-).
- Tratamento rigido de excecoes para campos vazios, textos invalidos e limites de valores.
- Botoes `Calcular` e `Limpar` com reorganizacao de foco automatico.

## Resultado esperado com o exemplo do enunciado

Para obter o resultado original do livro, preencha os campos com os seguintes valores:

- `a` = 39
- Cargas q1 (quinas): `2`, `-3`, `-1` e `2`
- Cargas q2 (centros): `4` e `4`

O resultado esperado na interface sera a formula montada e o valor final de aproximadamente:

- `V = 2,2123 V`
