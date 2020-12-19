import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Centrala {
    private JTextField zgloszenieJText;
    private JButton zarejestrujTAXIButton;
    private JButton pokazBazeButton;
    private JPanel panelGlowny;
    private JButton zarejestrujKlientaButton;
    private String adresPoczatkowy;
    private String adresKoncowy;
    private String[] daneZgloszenia;
    private String dataGodzinaPrzyjazdu;
    private String status;
    private String dodatkoweUwagi;
    private int numerZgloszenia;



    private static List<Zgloszenie> listaZgloszen = new ArrayList<>();
    private List<Taksowkarz> listaTAXI = new ArrayList<>();

    public static void dodajZgloszenie(Zgloszenie zgloszenie)
    {
        listaZgloszen.add(zgloszenie);
    }

    public Centrala() throws FileNotFoundException {
        zgloszenieJText.setEditable(false);
        OdbiorcaCentrala odbiorcaCentrala = new OdbiorcaCentrala(zgloszenieJText);
        odbiorcaCentrala.start();

        Scanner odczyt = new Scanner(new File("listaZgloszen.txt"));

        zarejestrujTAXIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame rejestracja = new JFrame("Rejestracja TAXI");
                try {
                    rejestracja.setContentPane(new RejestracjaTaxi(rejestracja).getPanelGlowny());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                rejestracja.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                rejestracja.setBounds(600,300,600,300);
                rejestracja.setVisible(true);
            }
        });
        pokazBazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                numerZgloszenia=0;
                while (odczyt.hasNextLine())
                {
                    try {

                        daneZgloszenia=odczyt.nextLine().split(";");

                        adresPoczatkowy=daneZgloszenia[0];
                        adresKoncowy=daneZgloszenia[1];
                        dataGodzinaPrzyjazdu=daneZgloszenia[2];
                        status=daneZgloszenia[3];
                        dodatkoweUwagi=daneZgloszenia[4];
                        Zgloszenie zgloszenie = new Zgloszenie(numerZgloszenia,adresPoczatkowy,adresKoncowy,dataGodzinaPrzyjazdu,status,dodatkoweUwagi);
                        listaZgloszen.add(zgloszenie);
                        numerZgloszenia++;
                        System.out.print(" " + daneZgloszenia [0]);
                        System.out.print(" " + daneZgloszenia [1]);
                        System.out.print(" " + daneZgloszenia [2]);
                        System.out.print(" " + daneZgloszenia [3]);
                        System.out.println(" " + daneZgloszenia [4]);

                    }catch (NoSuchElementException e)
                    {

                    }

                }
                JFrame baza = new JFrame("Baza");
                baza.setContentPane(new Baza(baza,listaZgloszen).getPanelGlowny());
                baza.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                baza.setBounds(600,300,600,300);
                baza.setVisible(true);
            }
        });
        zarejestrujKlientaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame rejestracjaKlienta = new JFrame("Rejestracja Klienta");
                try {
                    rejestracjaKlienta.setContentPane(new RejestracjaKlientow(rejestracjaKlienta).getPanelGlowny());
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                rejestracjaKlienta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                rejestracjaKlienta.setBounds(600,300,600,300);
                rejestracjaKlienta.setVisible(true);

            }
        });
    }

    public static void main(String[] args) throws FileNotFoundException {

        JFrame centrala = new JFrame("Centrala");
        centrala.setContentPane(new Centrala().panelGlowny);
        centrala.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centrala.setBounds(600, 300, 600, 300);
        centrala.setVisible(true);


//        try {
//            ServerSocket gniazdoSerwera = new ServerSocket(1024);
//            while (true)
//            {
//                Socket gniazdo = gniazdoSerwera.accept();
//                InputStream inputStream = gniazdo.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String wiadomosc = bufferedReader.readLine();
//                System.out.println(wiadomosc);
//                gniazdo.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
