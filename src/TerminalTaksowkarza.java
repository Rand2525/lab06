import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerminalTaksowkarza {
    private JPanel panelGlowny;
    private JTextField numerTextField;
    private JButton zalogujButton;
    private JButton zakonczPraceButton;
    private JButton zrealizujZgloszenieButton;
    private JLabel numerLabel;
    private JTextField zgloszenieTextField;
    private int numer;
    private boolean zgloszenie=true;
    private Taksowkarz taksowkarz;

    public TerminalTaksowkarza() {
        zakonczPraceButton.setVisible(false);
        zrealizujZgloszenieButton.setVisible(false);
        zgloszenieTextField.setVisible(false);
        zgloszenieTextField.setEditable(false);
        zalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if(numerTextField.getText().equals(""))
                        JOptionPane.showMessageDialog(null,"Podaj swój numer TAXI!");
                    else
                    {
                        numer=Integer.parseInt(numerTextField.getText());
                        zakonczPraceButton.setVisible(true);
                        zgloszenieTextField.setVisible(true);
                        zrealizujZgloszenieButton.setVisible(true);
                        zalogujButton.setVisible(false);
                        numerTextField.setText("");
                        numerTextField.setVisible(false);
                        numerLabel.setText("TAXI " + numer);
                        taksowkarz.setStatus("Wolny");
                        JOptionPane.showMessageDialog(null,"Zalogowano");
                    }

                }catch (NumberFormatException exception)
                {
                    JOptionPane.showMessageDialog(null,"Numer TAXI musi być liczbą!");
                    numerTextField.setText("");
                }
            }
        });
        zakonczPraceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(zgloszenie==true)
                    JOptionPane.showMessageDialog(null,"Nie zakonczono zgloszenia!");
                else
                {
                    taksowkarz=null;
                    zakonczPraceButton.setVisible(false);
                    zrealizujZgloszenieButton.setVisible(false);
                    zalogujButton.setVisible(true);
                    numerTextField.setVisible(true);
                    taksowkarz.setStatus("Nie pracuje");
                }
            }
        });
        zrealizujZgloszenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Zrealizowano zgłoszenie");
                zgloszenie=false;
                zgloszenieTextField.setText("Brak zgloszenia");
            }
        });
    }

    public static void main(String[] args) {

        JFrame terminal = new JFrame("TAXI");
        terminal.setContentPane(new TerminalTaksowkarza().panelGlowny);
        terminal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        terminal.setBounds(600,300,600,300);
        terminal.setVisible(true);
    }
}
