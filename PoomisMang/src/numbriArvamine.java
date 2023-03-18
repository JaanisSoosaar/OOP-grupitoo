import java.util.Scanner;
import static java.lang.Math.round;

public class numbriArvamine {
/*
    Tegin valmis meie mängu, aga numbrite jaoks.
    See lahendus ei sobi muidugi, sest juhendi järgi
    peaksime kasutama ka isikumeetodeid ja sellega kaasnevat.
    Seega kui tahame seda kuidagi kasutada, siis peaksime selle ümber tegema.

 */

    static int suvalineNumber(int mitmeKohaline) {
        int number = 0;
        if (mitmeKohaline==1) { // ühekohaline number
            number = (int)round(Math.random() * (9));
            return number;
        }
        if (mitmeKohaline==2) { // kahekohaline number
            number = (int)(10 + (Math.random() * ((99 - 10) + 1)));
            return number;
        }
        if (mitmeKohaline==3) { // kolmekohaline number
            number = (int)(100 + (Math.random() * ((999 - 100) + 1)));
            return number;
        }
        if (mitmeKohaline==4) { // neljakohaline number
            number = (int)(1000 + (Math.random() * ((9999 - 1000) + 1)));
            return number;
        }
        if (mitmeKohaline==5) { // viiekohaline number
            number = (int)(10000 + (Math.random() * ((99999 - 10000) + 1)));
            return number;
        }
        return 100;
    } // suvalineNumber

    static String arvaNumber(int mitmeKohaline) {
        int numberArvamiseks = suvalineNumber(mitmeKohaline);
        int mängijaArvamusInt = -1;
        Scanner mängijaArvab = new Scanner(System.in);
        while ( mängijaArvamusInt != numberArvamiseks) {
            System.out.println("Palun sisesta number!");
            String mängijaArvamus = mängijaArvab.nextLine();// Mängija sisestab
            mängijaArvamusInt = Integer.parseInt(mängijaArvamus);

            if (mängijaArvamusInt < numberArvamiseks) {
                System.out.println("Teie arvatud number on liiga väike!");
            }
            if (mängijaArvamusInt > numberArvamiseks) {
                System.out.println("Teie arvatud number on liiga suur!");
            }


        }
        return "Tubli! Arvasid õige numbri ära!\nSee number oli: " +
                numberArvamiseks;
    }

    public static void main(String[] args) {
        Scanner mängijaSisestab = new Scanner(System.in);
        System.out.println("Mitme kohalist positiivset arvu te soovite arvata? (1-5)");
        String mänguSuurus = mängijaSisestab.nextLine();// Mängija sisestab
        int mitmeKohaline = Integer.parseInt(mänguSuurus);
        System.out.println("Alustan mängu " + mitmeKohaline + "-kohaliste arvudega!");
        System.out.println(arvaNumber(mitmeKohaline));

    }
}
