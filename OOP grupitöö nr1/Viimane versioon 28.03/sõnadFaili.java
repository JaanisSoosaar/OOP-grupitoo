import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;


/*
Abimeetod, mille abil saame töödelda sõnad.txt faili.

Võtsin wikipediast kõik sõnad, ning sorteerisin need välja, mis ei vastanud meie tingimustele.
https://et.wiktionary.org/wiki/eesti_keel:S%C3%B5naloend
 */

public class sõnadFaili {

    public static List<String> sorteeriSõnadMassiivi(String failinimi) throws IOException {
        List<String> sõnad = new ArrayList<>();

        File fail = new File(failinimi);
        Scanner scanner = new Scanner(fail, UTF_8);

        while (scanner.hasNextLine()) {
            String rida = scanner.nextLine();
            if (rida.length() <= 15 && rida.length() != 1 && rida.length() != 2 && !rida.contains(" ") &&
                    !rida.contains(".") && !rida.contains("!") &&
                    !rida.contains("-") && !rida.contains("-") && !rida.contains("é")) {
                sõnad.add(rida); // lisan kõik sobilikud sõnad massiivi.
            }
        }
        return sõnad;
    }

    public static void sõnadUudeFaili(String uusFailinimi, List<String> massiiv) throws IOException {
        FileWriter myWriter = new FileWriter(uusFailinimi + ".txt"); // teen uue faili, kuhu panen sobivad sõnad
        for (int i = 0; i < massiiv.size(); i++) { // käin kõik sõnad massiivist läbi
            String sõna = massiiv.get(i); // võtan sõna massiivist
            try {
                myWriter.write(sõna + "\n"); // kirjutan sõnad uude faili
            } catch (IOException e) { // kui tekib error
                System.out.println("Tekkis probleem.");
                e.printStackTrace();
            }
        }
        myWriter.close();
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


        String uusFailinimi = "sõnad10"; // faili nimi, kuhu sorteeritud sõnad lähevad.
        List<String> sorteeritud = sorteeriSõnadMassiivi("sõnad.txt"); // sorteerin välja sobilikud sõnad ja panen need massiivi
        sõnadUudeFaili(uusFailinimi, sorteeritud); // võtan sobilikud sõnad ja panen need uude faili
        System.out.println(sorteeritud.size());

        //System.out.println(pikimSõna("sonad.txt"));

    }
}
