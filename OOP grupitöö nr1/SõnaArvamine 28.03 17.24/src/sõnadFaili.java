import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;


/*
Võtsin wikipediast kõik sõnad, ning sorteerisin need välja, mis ei vastanud meie tingimustele.
https://et.wiktionary.org/wiki/eesti_keel:S%C3%B5naloend_(K)
 */

public class sõnadFaili {

    public static List<String> sorteeriSõnad(String failinimi) throws IOException {
        List<String> K_sõnad = new ArrayList<>();

        File fail = new File(failinimi);
        Scanner scanner = new Scanner(fail, UTF_8);

        while (scanner.hasNextLine()) {
            String rida = scanner.nextLine();
            if (rida.length() <= 15 && rida.length() != 1 && rida.length() != 2 && !rida.contains(" ") && !rida.contains(".") && !rida.contains("!") &&
                    !rida.contains("-") && !rida.contains("-") && !rida.contains("é")) {
                K_sõnad.add(rida);
            }
        }
        return K_sõnad;
    }

    public static void sõnadUudeFaili(String uusFailinimi, List<String> massiiv) throws IOException {
        FileWriter myWriter = new FileWriter(uusFailinimi + ".txt"); // teen uue faili, kuhu panen sobivad sõned
        for (int i = 0; i < massiiv.size(); i++) { // käin kõik sõnad massiivist läbi
            String sõna = massiiv.get(i); // võtan sõna massiivist
            try {
                myWriter.write(sõna + "\n"); // kirjutan sõnad uude faili
            } catch (IOException e) { // kui tekib error
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        myWriter.close();
    }

    public static List<String> sonadMassiivi(String failinimi) throws IOException {
        List<String> koikSõnad = new ArrayList<>();

        File fail = new File (failinimi);
        Scanner scanner = new Scanner(fail, UTF_8);

        while (scanner.hasNextLine()) {
            String rida = scanner.nextLine();
            koikSõnad.add(rida);
        }
        return koikSõnad;
    }

    public static int pikimSõna(String failinimi) throws IOException {
        int suurimPikkus = 0;

        File fail = new File (failinimi);
        Scanner scanner = new Scanner(fail, UTF_8);

        while (scanner.hasNextLine()) {
            String rida = scanner.nextLine();
            if ( rida.length() > suurimPikkus) {
                suurimPikkus = rida.length();
                System.out.println(rida);
            }
        }

        return suurimPikkus;
    }


    public static void main(String[] args) throws IOException {

        /*
        String uusFailinimi = "sõnad9";
        //String uusFailinimi = "L_sõnad";
        //TODO siin peaks kasutusele võtma Isendiväljad, et saaks Listi nime muuta ja töö nõuded oleksid täidetud.
        List<String> sorteeri = sorteeriSõnad("sõnad.txt"); // sorteerin välja sobilikud sõnad ja panen need massiivi
        //List<String> L_sõnad = sorteeriSõnad("Lsonad.txt"); // sorteerin välja sobilikud sõnad ja panen need massiivi
        sõnadUudeFaili(uusFailinimi, sorteeri); // võtan sobilikud sõnad ja panen need uude faili
        System.out.println(sorteeri.size());
        */
        //System.out.println(pikimSõna("koiksonad.txt"));
    }
}
