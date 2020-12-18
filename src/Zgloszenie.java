public class Zgloszenie {

    private String adresPoczatkowy;
    private String adresKoncowy;
    private int numerZgloszenia;
    private String dataGodzinaPrzyjazdu;
    private String status;
    private String dodatkoweUwagi;

    public Zgloszenie( int numerZgloszenia,String adresPoczatkowy, String adresKoncowy, String dataGodzinaPrzyjazdu,String status, String dodatkoweUwagi) {
        this.adresPoczatkowy = adresPoczatkowy;
        this.adresKoncowy = adresKoncowy;
        this.numerZgloszenia = numerZgloszenia;
        this.dataGodzinaPrzyjazdu = dataGodzinaPrzyjazdu;
        this.dodatkoweUwagi=dodatkoweUwagi;
        this.status=status;
    }

    @Override
    public String toString() {
        return adresPoczatkowy + ";" + adresKoncowy + ";" + dataGodzinaPrzyjazdu + ";" + status + ";" + dodatkoweUwagi;
    }

    public void przyjmijZgloszenie() {
        status="W trakcie";
    }
    public void zakonczZgloszenie(){
        status="Zakonczono";
    }

    public String getAdresPoczatkowy() {
        return adresPoczatkowy;
    }

    public String getAdresKoncowy() {
        return adresKoncowy;
    }

    public int getNumerZgloszenia() {
        return numerZgloszenia;
    }

    public String getDataGodzinaPrzyjazdu() {
        return dataGodzinaPrzyjazdu;
    }

    public String getStatus() {
        return status;
    }

    public String getDodatkoweUwagi() {
        return dodatkoweUwagi;
    }
}
