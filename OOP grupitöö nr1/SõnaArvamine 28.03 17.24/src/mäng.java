public class mäng {
    private static String arvatavSõna;
    private String pakutavSõna;
    private int vigadeArv;
    private int pakkumisteArv;
    private char[] pakutudTähed;

    public mäng(String arvatavSõna, String pakutavSõna, int vigadeArv, int pakkumisteArv, char[] pakutudTähed) {
        this.arvatavSõna = arvatavSõna;
        this.pakutavSõna = pakutavSõna;
        this.vigadeArv = vigadeArv;
        this.pakkumisteArv = pakkumisteArv;
        this.pakutudTähed = pakutudTähed;
    }

    public String getArvatavSõna() {
        return arvatavSõna;
    }

    public String getPakutavSõna() {
        return pakutavSõna;
    }

    public void setPakutavSõna(String pakutavSõna) {
        this.pakutavSõna = pakutavSõna;
    }

    public int getVigadeArv() {
        return vigadeArv;
    }

    public void setVigadeArv(int vigadeArv) {
        this.vigadeArv = vigadeArv;
    }

    public int getPakkumisteArv() {
        return pakkumisteArv;
    }

    public void setPakkumisteArv(int pakkumisteArv) {
        this.pakkumisteArv = pakkumisteArv;
    }

    public char[] getPakutudTähed() {
        return pakutudTähed;
    }
    public void setPakutudTähed(char[] pakutudTähed) {
        this.pakutudTähed = pakutudTähed;
    }

}