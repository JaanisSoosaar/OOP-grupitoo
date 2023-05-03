import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;


/*
Võtsin wikipediast kõik K-tähega algavad sõnad, ning sorteerisin need välja, mis olid 4 märgi pikkused.
https://et.wiktionary.org/wiki/eesti_keel:S%C3%B5naloend_(K)
Lisasin need hetkel massiivi K_sõnad ning kirjutasin ka meetodi sõnadUudeFaili abil need uude .txt faili.
 */

public class sõnadFaili {

    public static List<String> sorteeriSõnad(String failinimi) throws IOException {
        List<String> K_sõnad = new ArrayList<>();

        File fail = new File(failinimi);
        Scanner scanner = new Scanner(fail, UTF_8);

        while (scanner.hasNextLine()) {
            String rida = scanner.nextLine();
            if (rida.length() == 4 || rida.length() == 3 || rida.length() == 5 && !rida.contains(".") && !rida.contains("!") && !rida.contains("š") && !rida.contains("ž")) {
                if (!rida.contains(".") && !rida.contains("!") && !rida.contains("š") && !rida.contains("ž") && !rida.contains("-")) {
                    K_sõnad.add(rida);
                }
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


    public static void main(String[] args) throws IOException {
        String uusFailinimi = "K_sõnad";
        //String uusFailinimi = "L_sõnad";
        //TODO siin peaks kasutusele võtma Isendiväljad, et saaks Listi nime muuta ja töö nõuded oleksid täidetud.
        List<String> K_sõnad = sorteeriSõnad("sonad.txt"); // sorteerin välja sobilikud sõnad ja panen need massiivi
        //List<String> L_sõnad = sorteeriSõnad("Lsonad.txt"); // sorteerin välja sobilikud sõnad ja panen need massiivi
        sõnadUudeFaili(uusFailinimi, K_sõnad); // võtan sobilikud sõnad ja panen need uude faili
        System.out.println(K_sõnad.size());

    }
}

/*
Sõnu:

4 tähelisi:
K - 415/ ilma š ja ž 413
L - 289

5 tähelisi:
K - 612
 */