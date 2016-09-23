package calcu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Calculadora extends JFrame {

    private static final long serialVersionUID = 1583724102189855698L;
    boolean nuevaOperacion = true;
    JTextField pantalla;
    double resultado;
    String operacion;
    JPanel numeros, operaciones;

    public Calculadora() {
        super();
        setSize(300, 350);
        setTitle("Convertidor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JPanel panel = (JPanel) this.getContentPane();
        panel.setLayout(new BorderLayout());

        pantalla = new JTextField("0", 20);
        pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
        pantalla.setFont(new Font("Arial", Font.BOLD, 25));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);
        pantalla.setBackground(Color.white);
        panel.add("North", pantalla);

        numeros = new JPanel();
        numeros.setLayout(new GridLayout(4, 3));
        numeros.setBorder(new EmptyBorder(4, 4, 4, 4));

        for (int n = 9; n >= 0; n--) {
            nuevoBotonNumerico("" + n);
        }

        nuevoBotonNumerico(".");
        panel.add("Center", numeros);

        operaciones = new JPanel();
        operaciones.setLayout(new GridLayout(6, 1));
        operaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
        
        nuevoBotonOperacion("CE");
        nuevoBotonOperacion("CONVERTIR");
        nuevoBotonOperacion("=");
        panel.add("East", operaciones);
        validate();

    }

    private void nuevoBotonNumerico(String digito) {
        JButton btn = new JButton();
        btn.setText(digito);
        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                numeroPulsado(btn.getText());
            }
        });
        numeros.add(btn);
    }

    private void nuevoBotonOperacion(String operacion) {
        JButton btn = new JButton(operacion);
        btn.setForeground(Color.GREEN);

        btn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent evt) {
                JButton btn = (JButton) evt.getSource();
                operacionPulsado(btn.getText());
            }
        });
        operaciones.add(btn);
    }

    private void numeroPulsado(String digito) {
        if (pantalla.getText().equals("0") || nuevaOperacion) {
            pantalla.setText(digito);
        } else {
            pantalla.setText(pantalla.getText() + digito);
        }
        nuevaOperacion = false;
    }

    private void operacionPulsado(String tecla) {
        if (tecla.equals("=")) {
            calcularResultado();
        } else if (tecla.equals("CE")) {
            resultado = 0;
            pantalla.setText("0");
            nuevaOperacion = true;
        } else {
            operacion = tecla;
            if ((resultado > 0) && !nuevaOperacion) {
                calcularResultado();
            } else {
                resultado = new Double(pantalla.getText());
            }
        }
        nuevaOperacion = true;
    }

    private void calcularResultado() {
        if (operacion.equals("CONVERTIR")) {
            resultado = resultado/19;
        }
        pantalla.setText("" + resultado);
        operacion = "";

    }

}
