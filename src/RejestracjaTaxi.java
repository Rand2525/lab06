import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RejestracjaTaxi {
    private JPanel panelGlowny;
    private JTextField imieTextField;
    private JTextField nazwiskoTextField;
    private JButton zarejestrujButton;
    private JButton cofnijButton;
    private String imie;
    private String nazwisko;
    private int NumerBoczny;

    public JPanel getPanelGlowny() {
        return panelGlowny;
    }

    public RejestracjaTaxi(JFrame rejestracja) {
        cofnijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rejestracja.dispose();
            }
        });
        zarejestrujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(imieTextField.getText().equals("") || nazwiskoTextField.getText().equals(""))
                    JOptionPane.showMessageDialog(null,"Wypelnij wszystkie pola!");
                else
                {
                    Taksowkarz taksowkarz = new Taksowkarz(imie,nazwisko);
                    JOptionPane.showMessageDialog(null,"Twoj numer boczny to " + NumerBoczny);
                }
            }
        });
    }
}
