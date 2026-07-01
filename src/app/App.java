package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame {

    // Constantes do problema.
    private static final long serialVersionUID = 1L;
    private static final double K = 8.9875517923E9;
    private static final double Q2 = 6.00E-12;
    private static final double Q1 = 3.40E-12;
    // Formato com 4 casas decimais e separador brasileiro.
    private static final DecimalFormat F = new DecimalFormat("0.0000",
            DecimalFormatSymbols.getInstance(Locale.forLanguageTag("pt-BR")));

    // Componentes da interface.
    private JTextField campoA, campoN, campoN1;
    private JTextField campoQ1_3, campoQ1_4, campoQ1_5, campoQ1_6; // Campos das quinas
    private JLabel resultado;

    // Monta a janela principal.
    public App() {
        super("ProjetoFisica - Potencial eletrico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(850, 600)); // Aumentei um pouco para caber a fórmula dupla
        setLocationRelativeTo(null);
        setResizable(false);
        criarInterface();
    }

    // Cria e organiza os componentes Swing.
    private void criarInterface() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Titulo, subtitulo e constantes.
        JPanel topo = new JPanel(new BorderLayout(0, 10)); // 10 de espaco abaixo dos textos

        // Painel com 3 linhas e 1 coluna para empilhar os textos
        JPanel painelTextos = new JPanel(new java.awt.GridLayout(3, 1, 0, 3));

        JLabel titulo = new JLabel("Potencial eletrico no centro");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));

        JLabel subtitulo = new JLabel("Informe a distancia (a) e os multiplicadores de q2 e clique em Calcular.");

        // Novo JLabel exclusivo para as constantes (usando Unicode para o ²)
        JLabel constantes = new JLabel(
                "Constantes: K = 8.987 x 10^9 N·m²/C²   •   Q2 = 6.00 x 10^-12 C   •   Q1 = 3.40 x 10^-12 C");
        constantes.setForeground(java.awt.Color.GRAY); // Deixa o texto cinza

        // Adiciona os 3 JLabels na ordem correta dentro do mini-painel
        painelTextos.add(titulo);
        painelTextos.add(subtitulo);
        painelTextos.add(constantes);

        // Adiciona o bloco de textos no norte do topo
        topo.add(painelTextos, BorderLayout.NORTH);

        // 2. A arte ASCII
        String arte = "(1)q1          (2)q2          (3)q1\n" +
                "  * - - - - - - * - - - - - - *\n" +
                "  |      a             a      |\n" +
                "a |                           | a\n" +
                "  |                           |\n" +
                "  |      a             a      |\n" +
                "  * - - - - - - * - - - - - - *\n" +
                "(4)q1           (5)q2          (6)q1";

        // 3. Configurando o componente de texto para parecer um desenho
        javax.swing.JTextArea desenho = new javax.swing.JTextArea(arte);
        desenho.setFont(new Font("Monospaced", Font.BOLD, 14)); // Monospaced mantém a grade alinhada!
        desenho.setEditable(false); // Impede o usuário de apagar o desenho
        desenho.setOpaque(false); // Deixa o fundo transparente igual ao do painel
        desenho.setFocusable(false); // Remove aquele cursor de digitação piscando

        // 4. Painel extra só para centralizar o desenho bonitinho na tela
        JPanel painelDesenho = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        painelDesenho.add(desenho);

        // Adiciona tudo no topo
        topo.add(painelDesenho, BorderLayout.CENTER);

        // Campo de entrada organizado em uma grade de 4 linhas e 4 colunas
        JPanel entrada = new JPanel(new java.awt.GridLayout(4, 4, 5, 5));
        entrada.setBorder(BorderFactory.createTitledBorder("Entrada"));

        campoA = new JTextField();
        campoN = new JTextField();
        campoN1 = new JTextField();
        campoQ1_3 = new JTextField();
        campoQ1_4 = new JTextField();
        campoQ1_5 = new JTextField();
        campoQ1_6 = new JTextField();

        // Linha 1: Distância a
        entrada.add(new JLabel("a (cm):", JLabel.RIGHT));
        entrada.add(campoA);
        entrada.add(new JLabel(""));
        entrada.add(new JLabel("")); // Espaços vazios

        // Linha 2: Cargas 1 e 2 (Cima: esquerda e centro)
        entrada.add(new JLabel("(1) mult. q1:", JLabel.RIGHT));
        entrada.add(campoQ1_3);
        entrada.add(new JLabel("(2) mult. q2:", JLabel.RIGHT));
        entrada.add(campoN);

        // Linha 3: Cargas 3 e 4 (Cima: direita e Baixo: esquerda)
        entrada.add(new JLabel("(3) mult. q1:", JLabel.RIGHT));
        entrada.add(campoQ1_4);
        entrada.add(new JLabel("(4) mult. q1:", JLabel.RIGHT));
        entrada.add(campoQ1_5);

        // Linha 4: Cargas 5 e 6 (Baixo: centro e direita)
        entrada.add(new JLabel("(5) mult. q2:", JLabel.RIGHT));
        entrada.add(campoN1);
        entrada.add(new JLabel("(6) mult. q1:", JLabel.RIGHT));
        entrada.add(campoQ1_6);

        // Area de saida do resultado final.
        JPanel saida = new JPanel(new BorderLayout());
        saida.setBorder(BorderFactory.createTitledBorder("Resultado"));
        resultado = new JLabel("V = -");
        resultado.setFont(new Font("SansSerif", Font.BOLD, 16));
        saida.add(resultado, BorderLayout.CENTER);

        // Botoes de acao.
        JButton calcular = new JButton("Calcular");
        calcular.addActionListener(e -> calcular());

        JButton limpar = new JButton("Limpar");
        limpar.addActionListener(e -> limpar());

        JPanel botoes = new JPanel();
        botoes.add(calcular);
        botoes.add(limpar);

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.add(entrada, BorderLayout.NORTH);
        centro.add(saida, BorderLayout.SOUTH);

        principal.add(topo, BorderLayout.NORTH);
        principal.add(centro, BorderLayout.CENTER);
        principal.add(botoes, BorderLayout.SOUTH);
        setContentPane(principal);
    }

    // Valida a entrada e calcula o potencial eletrico.
    private void calcular() {
        // Lê os campos na nova ordem (garantindo que 2 e 5 são as centrais q2)
        double aCm = lerCampo(campoA, "a");
        double n1_q1 = lerCampo(campoQ1_3, "(1) q1");
        double n2_q2 = lerCampo(campoN, "(2) q2");
        double n3_q1 = lerCampo(campoQ1_4, "(3) q1");
        double n4_q1 = lerCampo(campoQ1_5, "(4) q1");
        double n5_q2 = lerCampo(campoN1, "(5) q2");
        double n6_q1 = lerCampo(campoQ1_6, "(6) q1");

        // Se algum campo retornou NaN (erro de digitação), interrompe o cálculo
        if (Double.isNaN(aCm) || Double.isNaN(n1_q1) || Double.isNaN(n2_q2) ||
                Double.isNaN(n3_q1) || Double.isNaN(n4_q1) || Double.isNaN(n5_q2) || Double.isNaN(n6_q1)) {
            return;
        }

        if (aCm <= 0 || aCm > 1000) {
            erro("O valor de a deve estar entre 0 e 1000 cm.");
            return;
        }

        // Limite de segurança para os multiplicadores
        if (n1_q1 < -100 || n1_q1 > 100 || n2_q2 < -100 || n2_q2 > 100 ||
                n3_q1 < -100 || n3_q1 > 100 || n4_q1 < -100 || n4_q1 > 100 ||
                n5_q2 < -100 || n5_q2 > 100 || n6_q1 < -100 || n6_q1 > 100) {
            erro("Os multiplicadores devem estar entre -100 e 100.");
            return;
        }

        // 1. Calculo do potencial das cargas q2 (meio do retangulo - variáveis 2 e 5)
        double distQ2Cm = aCm / 2.0;
        double vQ2 = ((n2_q2 * K * Q2) / distQ2Cm + (n5_q2 * K * Q2) / distQ2Cm) * 100.0;

        // 2. Calculo do potencial das cargas q1 (quinas do retangulo - Pitagoras)
        double distQuinaCm = aCm * (Math.sqrt(5) / 2.0);
        double somaQ1 = n1_q1 + n3_q1 + n4_q1 + n6_q1;
        double vQ1 = ((somaQ1 * K * Q1) / distQuinaCm) * 100.0;

        // 3. Soma os dois potenciais
        double vTotal = vQ2 + vQ1;

        // 4. Monta a string da fórmula para exibição substituindo com as variáveis
        // corretas
        String formulaMontada = String.format(
                "V = { [ (%.1f %+.1f %+.1f %+.1f) * K * Q1 ] / (%.1f * &radic;5 / 2) <br>" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; + [ (%.1f * K * Q2) / (%.1f / 2) + (%.1f * K * Q2) / (%.1f / 2) ] } * 100",
                n1_q1, n3_q1, n4_q1, n6_q1, aCm, n2_q2, aCm, n5_q2, aCm);

        // 5. Atualiza o JLabel com a fórmula montada e o resultado final
        resultado.setText("<html>"
                + "<div style='text-align: center;'>"
                + "<span style='font-size: 11px; color: gray;'>" + formulaMontada + "</span><br><br>"
                + "<font color='blue' size='5'><b>V = " + F.format(vTotal) + " V</b></font>"
                + "</div></html>");
    }

    // Limpa os campos da interface.
    private void limpar() {
        campoA.setText("");
        campoN.setText("");
        campoN1.setText("");
        campoQ1_3.setText("");
        campoQ1_4.setText("");
        campoQ1_5.setText("");
        campoQ1_6.setText("");
        resultado.setText("V = -");
        campoA.requestFocus();
    }

    // Exibe mensagens de erro ao usuario.
    private void erro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro de validacao", JOptionPane.ERROR_MESSAGE);
    }

    private double lerCampo(JTextField campo, String nomeCampo) {
        String texto = campo.getText().trim().replace(",", ".");
        if (texto.isEmpty()) {
            erro("Preencha o valor de " + nomeCampo + ".");
            return Double.NaN;
        }
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            erro("Digite um numero valido para " + nomeCampo + ".");
            return Double.NaN;
        }
    }

    // Ponto de entrada da aplicacao.
    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Se der erro, ele apenas ignora e usa o padrão antigo
        }
        new App().setVisible(true);
    }
}