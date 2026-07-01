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
    // Formato com 4 casas decimais e separador brasileiro.
    private static final DecimalFormat F = new DecimalFormat("0.0000",
            DecimalFormatSymbols.getInstance(Locale.forLanguageTag("pt-BR")));

    // Componentes da interface.
    private JTextField campoA;
    private JTextField campoN;
    private JTextField campoN1;
    private JLabel resultado;

    // Monta a janela principal.
    public App() {
        super("ProjetoFisica - Potencial eletrico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 460));
        setLocationRelativeTo(null);
        setResizable(false);
        criarInterface();
    }

    // Cria e organiza os componentes Swing.
    private void criarInterface() {
        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Titulo e texto de orientacao.
        JPanel topo = new JPanel(new BorderLayout(0, 4));
        JLabel titulo = new JLabel("Potencial eletrico no centro");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        topo.add(titulo, BorderLayout.NORTH);
        topo.add(new JLabel("Informe a distancia(a) e as cargas das particulas Q2 e clique em Calcular."), BorderLayout.SOUTH);

        // 2. A arte ASCII exatamente como na sua imagem
        String arte = "+2q1          (1)q2          -3q1\n" +
                "  * - - - - - - * - - - - - - *\n" +
                "  |      a             a      |\n" +
                "a |                           | a\n" +
                "  |                           |\n" +
                "  |      a             a      |\n" +
                "  * - - - - - - * - - - - - - *\n" +
                "-q1           (2)q2          +2q1";

        // 3. Configurando o componente de texto para parecer um desenho
        javax.swing.JTextArea desenho = new javax.swing.JTextArea(arte);
        desenho.setFont(new Font("Monospaced", Font.BOLD, 14)); // Monospaced mantém a grade alinhada!
        desenho.setEditable(false); // Impede o usuário de apagar o desenho
        desenho.setOpaque(false); // Deixa o fundo transparente igual ao do painel
        desenho.setFocusable(false); // Remove aquele cursor de digitação piscando

        // 4. Painel extra só para centralizar o desenho bonitinho na tela
        JPanel painelDesenho = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
        painelDesenho.add(desenho);

        // Adiciona tudo no "topo"
        topo.add(painelDesenho, BorderLayout.CENTER);

        // Campo de entrada de a.
        JPanel entrada = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 5));
        entrada.setBorder(BorderFactory.createTitledBorder("Entrada"));
        campoA = new JTextField(10);
        campoN = new JTextField(10);
        campoN1 = new JTextField(10);

        entrada.add(new JLabel("a (cm):"));
        entrada.add(campoA);
        entrada.add(new JLabel("   (1) multiplicador de q2:"));
        entrada.add(campoN);
        entrada.add(new JLabel(" (2) multiplicador de q2:"));
        entrada.add(campoN1);


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
        String texto = campoA.getText().trim().replace(",", ".");
        if (texto.isEmpty()) {
            erro("Preencha o valor de a.");
            return;
        }

        double aCm;
        try {
            aCm = Double.parseDouble(texto);
        } catch (NumberFormatException excecao) {
            erro("Digite um numero valido para a.");
            return;
        }

        if (aCm <= 0 || aCm > 1000) {
            erro("O valor de a deve estar entre 0 e 1000 cm.");
            return;
        }

        String textoN = campoN.getText().trim().replace(",", ".");
        if (textoN.isEmpty()) {
            erro("Preencha o valor do multiplicaor de (1) q2.");
            return;
        }

        double n;
        try {
            n = Double.parseDouble(textoN);
        } catch (NumberFormatException excecao) {
            erro("Digite um numero valido para o multiplicaor de (1) q2.");
            return;
        }

        String textoN1 = campoN1.getText().trim().replace(",", ".");
        if (textoN.isEmpty()) {
            erro("Preencha o valor do multiplicaor de (2) q2.");
            return;
        }

        double n1;
        try {
            n1 = Double.parseDouble(textoN1);
        } catch (NumberFormatException excecao) {
            erro("Digite um numero valido para o multiplicaor de (2) q2.");
            return;
        }

        double v = ((n * K * Q2) / (aCm / 2.0) + (n1 * K * Q2) / (aCm / 2.0) ) * 100.0;
        resultado.setText("V = " + F.format(v) + " V");
    }

    // Limpa os campos da interface.
    private void limpar() {
        campoA.setText("");
        campoN.setText("");
        campoN1.setText("");
        resultado.setText("V = -");
        campoA.requestFocus();
    }

    // Exibe mensagens de erro ao usuario.
    private void erro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro de validacao", JOptionPane.ERROR_MESSAGE);
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
