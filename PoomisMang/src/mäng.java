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
    /*
    public static String getWord() throws IOException {
        List<String> words = Files.readAllLines(Paths.get("sõnad.txt"));
        String word = words.get(new Random().nextInt(words.size()));
        //int len = word.length();
        // System.out.println(len);
        return word;
    }

    public void go() throws IOException {
        setArvatavSõna(mäng.getWord());
        System.out.println(arvatavSõna);
        int pikkus = arvatavSõna.length();
        System.out.println(pikkus);
        String pakutavSõne = "-".repeat(pikkus);
        while (!pakutavSõne.equals(arvatavSõna)) {
            System.out.println(pakutavSõne);
        }


    }*/

}