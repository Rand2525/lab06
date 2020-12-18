import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Baza {
    private JPanel panelGlowny;
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton cofnijButton;
    private JButton przydzielZgłoszenieButton;
    private JTextField textField2;
    private JTable tabelaTAXI;
    private JTable tabelaZgloszen;

    private List<Zgloszenie> listaZgloszen = new ArrayList<>();
    private List<Taksowkarz> listaTaxi = new ArrayList<>();
    public JPanel getPanelGlowny() {
        return panelGlowny;
    }

    public Baza(JFrame baza,List<Zgloszenie> listaZgloszen) {
        tabelaZgloszen.setModel(new ZgloszenieTableModel(listaZgloszen));
        tabelaZgloszen.getColumnModel().getColumn(0).setHeaderValue("Numer zgloszenia");
        tabelaZgloszen.getColumnModel().getColumn(1).setHeaderValue("Poczatek trasy");
        tabelaZgloszen.getColumnModel().getColumn(2).setHeaderValue("Koniec trasy");
        tabelaZgloszen.getColumnModel().getColumn(3).setHeaderValue("Data i godzina przyjazdu");
        tabelaZgloszen.getColumnModel().getColumn(4).setHeaderValue("Dodatkowe uwagi dla kierowcy");
        tabelaZgloszen.getColumnModel().getColumn(5).setHeaderValue("Status");

        tabelaTAXI.setModel(new TaxiTableModel(listaTaxi));
        tabelaTAXI.getColumnModel().getColumn(0).setHeaderValue("Numer Taxi");
        tabelaTAXI.getColumnModel().getColumn(1).setHeaderValue("Imie");
        tabelaTAXI.getColumnModel().getColumn(2).setHeaderValue("Nazwisko");
        tabelaTAXI.getColumnModel().getColumn(3).setHeaderValue("Status");

        cofnijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                baza.dispose();
            }
        });

    }

    public void dodajZgloszenie(Zgloszenie zgloszenie) {
        listaZgloszen.add(zgloszenie);
    }
}
