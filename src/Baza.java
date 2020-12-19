import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Baza {
    private JPanel panelGlowny;
    private JTabbedPane tabbedPane1;
    private JTextField numerZgloszeniaTextField;
    private JButton cofnijButton;
    private JButton przydzielZgłoszenieButton;
    private JTextField numerTaxiTextField;
    private JTable tabelaTAXI;
    private JTable tabelaZgloszen;

    private List<Zgloszenie> listaZgloszen = new ArrayList<>();
    private List<Taksowkarz> listaTaxi = new ArrayList<>();
    public JPanel getPanelGlowny() {
        return panelGlowny;
    }

    public boolean czyJestZgloszenie(int numerZgloszenia,List<Zgloszenie> listaZgloszen)
    {
        boolean jestZgloszenie=false;
        for(Zgloszenie zgloszenie : listaZgloszen)
        {
            if(zgloszenie.getNumerZgloszenia()==numerZgloszenia) {
                jestZgloszenie = true;
                break;
            }
        }
        return jestZgloszenie;
    }
    public boolean czyJestWolny(int numerTaxi,List<Taksowkarz> listaTaxi)
    {
        boolean jestWolny=false;
        for(Taksowkarz taksowkarz : listaTaxi)
        {
            if(taksowkarz.getNumerTaxi()==numerTaxi)
            {
                if(taksowkarz.getStatus().equals("Wolny")) {
                    jestWolny = true;
                    break;
                }
            }
        }
        return jestWolny;
    }
    public boolean czyJestTaxi(int numerTaxi, List<Taksowkarz> listaTaxi)
    {
        boolean jestTaksowkarz=false;
        for(Taksowkarz taksowkarz : listaTaxi)
        {
            if(taksowkarz.getNumerTaxi()==numerTaxi)
            {
                jestTaksowkarz=true;
                break;
            }
        }
        return jestTaksowkarz;
    }

    public Zgloszenie wyszukajZgloszenie(int numerZgloszenia,List<Zgloszenie> listaZgloszen)
    {
        for(Zgloszenie zgloszenie : listaZgloszen)
        {
            if(zgloszenie.getNumerZgloszenia()==numerZgloszenia)
            {
                return zgloszenie;
            }
        }
        return null;
    }

    public int wyszukujPoNumerzeZgloszenia(int numerZgloszenia,List<Zgloszenie> listaZgloszen)
    {
        for(Zgloszenie zgloszenie : listaZgloszen)
        {
            if(zgloszenie.getNumerZgloszenia()==numerZgloszenia)
            {
                return zgloszenie.getNumerKlienta();
            }
        }
        return 0;
    }

    public Baza(JFrame baza,List<Zgloszenie> listaZgloszen,List<Taksowkarz> listaTaxi) {


        tabelaZgloszen.setModel(new ZgloszenieTableModel(listaZgloszen));


        tabelaZgloszen.getColumnModel().getColumn(0).setHeaderValue("Numer zgloszenia");
        tabelaZgloszen.getColumnModel().getColumn(1).setHeaderValue("Numer klienta");
        tabelaZgloszen.getColumnModel().getColumn(2).setHeaderValue("Poczatek trasy");
        tabelaZgloszen.getColumnModel().getColumn(3).setHeaderValue("Koniec trasy");
        tabelaZgloszen.getColumnModel().getColumn(4).setHeaderValue("Data i godzina przyjazdu");
        tabelaZgloszen.getColumnModel().getColumn(5).setHeaderValue("Dodatkowe uwagi dla kierowcy");
        tabelaZgloszen.getColumnModel().getColumn(6).setHeaderValue("Status");

        tabelaTAXI.setModel(new TaxiTableModel(listaTaxi));
        tabelaTAXI.getColumnModel().getColumn(0).setHeaderValue("Imie");
        tabelaTAXI.getColumnModel().getColumn(1).setHeaderValue("Nazwisko");
        tabelaTAXI.getColumnModel().getColumn(2).setHeaderValue("Numer Taxi");
        tabelaTAXI.getColumnModel().getColumn(3).setHeaderValue("Status");


        cofnijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                baza.dispose();
            }
        });

        przydzielZgłoszenieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(numerZgloszeniaTextField.getText().equals("") || numerTaxiTextField.getText().equals("")) {

                    JOptionPane.showMessageDialog(null,"Wszystkie pola musza byc wypelnione!");
                }
                else
                {
                    int numerZgloszenia = Integer.parseInt(numerZgloszeniaTextField.getText());
                    int numerTaxi = Integer.parseInt(numerTaxiTextField.getText());
                    try {
                         numerZgloszenia = Integer.parseInt(numerZgloszeniaTextField.getText());
                         numerTaxi = Integer.parseInt(numerTaxiTextField.getText());
                    }catch (NumberFormatException exception)
                    {
                        exception.printStackTrace();
                    }
                    if(czyJestZgloszenie(numerZgloszenia,listaZgloszen)==false)
                    {
                        JOptionPane.showMessageDialog(null,"Nie ma takiego zgloszenia");
                    }
                    else
                    if(czyJestTaxi(numerTaxi,listaTaxi)==false)
                    {
                        JOptionPane.showMessageDialog(null,"Nie ma takiego taksowkarza");
                    }
                    else
                    if(czyJestWolny(numerTaxi,listaTaxi)==false)
                    {
                        JOptionPane.showMessageDialog(null,"Ten taksowkarz jest aktualnie zajety lub nie pracuje");
                    }
                    else
                    {
                        new NadawcaCentrala().send("Zgloszenie w trakcie realizacji",wyszukujPoNumerzeZgloszenia(numerZgloszenia,listaZgloszen));
//                        new NadawcaCentrala().send(wyszukajZgloszenie(numerZgloszenia,listaZgloszen).toString(),numerTaxi);
                    }
                }


            }
        });
    }

    public void dodajZgloszenie(Zgloszenie zgloszenie) {
        listaZgloszen.add(zgloszenie);
    }
}
