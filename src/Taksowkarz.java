public class Taksowkarz {

    private final String imie;
    private final String nazwisko;
    private int numerTaxi;
    private String status = "Nie pracuje";


    public Taksowkarz(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + " " + numerTaxi + " " + status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void przyjmijZgloszenie(Zgloszenie zgloszenie)
    {

    }
    public void odrzucZgloszenie(Zgloszenie zgloszenie)
    {

    }
}