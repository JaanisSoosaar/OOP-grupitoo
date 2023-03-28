import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static String getWord() throws IOException {
        List<String> words = Files.readAllLines(Paths.get("sõnad.txt"));
        String sõna = words.get(new Random().nextInt(words.size()));
        return sõna;
    }

    public static mäng looMängija(String arvatavsõna) throws IOException {
        int pikkus = arvatavsõna.length(); // leian genereeritud sõna pikkuse.
        String pakutavSõna = "-".repeat(pikkus); // panen paika arvatava sõna kuju.
        char[] pakutudTähed = new char[0]; // loon listi, kus hakkan koguma pakutud tähti.
        mäng mängija = new mäng(arvatavsõna, pakutavSõna, 0, 0, pakutudTähed); // loon mäng isendi, kus salvestatakse mängu infot.

        return mängija;
    }

    public static void main(String[] args) throws IOException {
        String arvatavsõna = getWord(); // genereerin juhuslikult sõna, mida mängija hakkab arvama.
        arvatavsõna = arvatavsõna.toLowerCase(); // muudan genereeritud sõna kõik tähed väikesteks.
        //System.out.println(arvatavsõna); // kommenteeri välja, kui soovid näha arvatavat sõna

        mäng mängija = looMängija(arvatavsõna);// loon mäng isendi, kus salvestatakse mängu infot.
        Scanner mängijaArvab = new Scanner(System.in);
        int indeks = 0;
        System.out.println("\nMängu eesmärk on ära arvata suvaliselt genereeritud sõna." +
                " Mängu võitmiseks peaksid seda tegema vähem kui 10 veaga. Tähti saad pakkuda ükshaaval. Edu!\n");
        while (!mängija.getPakutavSõna().equals(mängija.getArvatavSõna())) {

            if (mängija.getVigadeArv() == 10) {
                System.out.println("\nTegid üle 10 vea. Oled kaotanud!");
                System.out.println("Arvatav sõna oli: " + mängija.getArvatavSõna());
                System.out.println("Pakkusid " + mängija.getPakkumisteArv() + " korda. Pakkusid tähti: " + Arrays.toString(mängija.getPakutudTähed()));
                System.out.println("Järgmine kord läheb paremini!");
                break;
            }
            System.out.println("Oled pakkunud tähti: " + Arrays.toString(mängija.getPakutudTähed()) +
                    " Sinu arvatav sõna: " + mängija.getPakutavSõna() + "\nPalun paku järgmine täht: ");


            String mängijaArvamus = mängijaArvab.nextLine();// mängija sisestab uue tähe.
            char pakkumine = mängijaArvamus.charAt(0); // muudan mängija pakkumise char-iks.

            if (mängijaArvamus.length() > 1) { // kontrollin, kas mängija sisestas ainult ühe tähe.
                System.out.println("Palun paku ainult ÜKS täht!");
                continue;
            }
            mängija.setPakkumisteArv(mängija.getPakkumisteArv()+1); // suurendan mängija pakkumiste arvu ühe võrra.

            char[] pakutudTähedUus = Arrays.copyOf(mängija.getPakutudTähed(), indeks+1); // loon uue listi, kuhu lisan mängija pakutud tähe.
            pakutudTähedUus[indeks++] = pakkumine;
            mängija.setPakutudTähed(pakutudTähedUus);

            if (arvatavsõna.indexOf(pakkumine) != -1) { // kontrollin, kas mängija pakkumine esineb otsitavas sõnas.
                System.out.println("Tubli! Pakutud täht: " + pakkumine + " esines otsitavas sõnas!");
            } else {
                System.out.println("Pakutud täht ei esinenud otsitavas sõnas.");
                mängija.setVigadeArv(mängija.getVigadeArv()+1);
            }

            String pakutavSõna = mängija.getPakutavSõna();
            try { // kontrollin, millisel kohal sõnas esines mängija pakkumine ning seejärel vahetan pakutavas sõnas vastava tähe.
                if (mängija.getArvatavSõna().charAt(0) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakkumine + pakutavSõna.substring(1);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(1) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 1) + pakkumine + pakutavSõna.substring(2);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(2) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 2) + pakkumine + pakutavSõna.substring(3);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(3) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 3) + pakkumine + pakutavSõna.substring(4);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(4) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 4) + pakkumine + pakutavSõna.substring(5);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(5) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 5) + pakkumine + pakutavSõna.substring(6);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(6) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 6) + pakkumine + pakutavSõna.substring(7);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(7) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 7) + pakkumine + pakutavSõna.substring(8);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(8) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 8) + pakkumine + pakutavSõna.substring(9);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(9) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 9) + pakkumine + pakutavSõna.substring(10);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(10) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 10) + pakkumine + pakutavSõna.substring(11);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(11) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 11) + pakkumine + pakutavSõna.substring(12);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(12) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 12) + pakkumine + pakutavSõna.substring(13);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(13) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 13) + pakkumine + pakutavSõna.substring(14);
                    mängija.setPakutavSõna(uusSõna);
                }
                if (mängija.getArvatavSõna().charAt(14) == pakkumine) {
                    pakutavSõna = mängija.getPakutavSõna();
                    String uusSõna = pakutavSõna.substring(0, 14) + pakkumine + pakutavSõna.substring(15);
                    mängija.setPakutavSõna(uusSõna);
                }

            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        if (mängija.getPakutavSõna().equals(mängija.getArvatavSõna())) { // kui mängija arvab sõna ära, siis väljastan vajaliku info.
            System.out.println("\nArvasid otsitava sõna ära! See sõna oli: " + mängija.getArvatavSõna());
            System.out.println("Pakkusid " + mängija.getPakkumisteArv() + " korda. Pakkusid tähti: " + Arrays.toString(mängija.getPakutudTähed()));
            System.out.println("Tegid " + mängija.getVigadeArv() + " viga!");
        }


    }

}