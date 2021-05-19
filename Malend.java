import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Malend {
    private int x_telg;
    private int y_telg;
    public int x;
    public int y;
    private Boolean valgeNupp;
    private ArrayList<Malend> nupud;
    private String malendiTüüp;


    public int getX_telg() {
        return x_telg;
    }

    public int getY_telg() {
        return y_telg;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean getvalgeNupp() {
        return valgeNupp;
    }

    public String getMalendiTüüp() {
        return malendiTüüp;
    }


    private Boolean vangerdusValge = true;
    private Boolean vangerdusMust = true;

    public Malend(int x_telg, int y_telg, String malenditüüp, Boolean valge_nupp, ArrayList<Malend> nupud) {
        this.x_telg = x_telg;
        this.y_telg = y_telg;
        this.x = x_telg * 90;
        this.y = y_telg * 90;
        this.valgeNupp = valge_nupp;
        this.nupud = nupud;
        nupud.add(this);
        this.malendiTüüp = malenditüüp;
    }
    public Boolean getMinukord() {
        return (Male.kord % 2 != 0 && Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).valgeNupp ||
                Male.kord % 2 == 0 && !Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).valgeNupp);
    }
    public void käik(int x_telg, int y_telg){ //meetod mis sooritab lõplikku käigu
        if (Male.liigutatavMalend.malendiTüüp.equals("kuningas")) {
            if (Male.liigutatavMalend.valgeNupp) {
                vangerdusValge = false;
            }
            if (!Male.liigutatavMalend.valgeNupp) {
                vangerdusMust = false;
            }
        }

        this.x_telg = x_telg;
        this.y_telg = y_telg;
        this.x = x_telg * 90;
        this.y = y_telg * 90;
        Male.kord++;
        lipustumine();
        Male.käigud.add(this.malendiTüüp);
        Male.käigud.add(String.valueOf(this.x_telg));
        Male.käigud.add(String.valueOf(this.y_telg));

    }
    public void legaalne(int x_telg, int y_telg) throws IOException {//meetod mis kontrollib kas antud käik on males lubatud
        if (getMinukord()) {
            omaleKohale();
            return;
        }
        if (Male.liigutatavMalend.malendiTüüp.equals("ettur")) {
            if (Male.getMalend(x_telg * 90, y_telg * 90) == null) {
                if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x != 0) {
                    omaleKohale();

                    return;
                }
                if (Male.liigutatavMalend.y_telg == 6) {
                    if (Male.liigutatavMalend.valgeNupp) {
                        if (Male.sihtPunkti_y != 5 && Male.sihtPunkti_y != 4) {
                            omaleKohale();
                            return;
                        }
                    }
                }

                if (Male.liigutatavMalend.y_telg == 1) {

                    if (!Male.liigutatavMalend.valgeNupp) {
                        if (Male.sihtPunkti_y != 2 && Male.sihtPunkti_y != 3) {
                            omaleKohale();
                            return;
                        }
                    }
                }

                if (Male.liigutatavMalend.y_telg != 6) {
                    if (Male.liigutatavMalend.valgeNupp) {
                        if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y != 1) {
                            omaleKohale();
                            return;
                        }
                    }
                }
                if (Male.liigutatavMalend.y_telg != 1) {
                    if (!Male.liigutatavMalend.valgeNupp) {
                        if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y != -1) {
                            omaleKohale();
                            return;
                        }
                    }
                }
            }
            if (Male.getMalend(x_telg * 90, y_telg * 90) != null) {
                if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x != 1 && Male.liigutatavMalend.x_telg - Male.sihtPunkti_x != -1) {
                    omaleKohale();
                    return;
                }
                if (Male.liigutatavMalend.valgeNupp) {
                    if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y != 1) {
                        omaleKohale();
                        return;

                    }
                }
                if (!Male.liigutatavMalend.valgeNupp) {
                    if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y != -1) {
                        omaleKohale();
                        return;
                    }
                }

            }
        }

        if (Male.liigutatavMalend.malendiTüüp.equals("vanker")) {
            if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y == 0 || Male.liigutatavMalend.x_telg - Male.sihtPunkti_x == 0) {
            } else {
                omaleKohale();
                return;
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x < 0) {
                for (int i = 1; i < Male.sihtPunkti_x - Male.liigutatavMalend.x_telg; i++) {
                    if (Male.getMalend((x_telg - i) * 90, y_telg * 90) == null) {
                    } else {
                        omaleKohale();
                        return;
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x > 0) {
                for (int i = 1; i < Male.liigutatavMalend.x_telg - Male.sihtPunkti_x; i++) {
                    if (Male.getMalend((x_telg + i) * 90, y_telg * 90) == null) {
                    } else {
                        omaleKohale();
                        return;
                    }
                }
            }
            if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y > 0) {
                for (int i = 1; i < Male.liigutatavMalend.y_telg - Male.sihtPunkti_y; i++) {
                    if (Male.getMalend(x_telg * 90, (y_telg + i) * 90) == null) {
                    } else {
                        omaleKohale();
                        return;
                    }
                }
            }
            if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y < 0) {
                for (int i = 1; i < Male.sihtPunkti_y - Male.liigutatavMalend.y_telg; i++) {
                    if (Male.getMalend(x_telg * 90, (y_telg - i) * 90) == null) {
                    } else {
                        omaleKohale();
                        return;
                    }
                }
            }
        }
        if (Male.liigutatavMalend.malendiTüüp.equals("oda")) {
            if (Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x) == Math.abs(Male.liigutatavMalend.y_telg - Male.sihtPunkti_y)) {
            } else {
                omaleKohale();
                return;
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x > 0) {
                if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y < 0) {
                    for (int i = 1; i < Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x); i++) {
                        if (Male.getMalend((x_telg + i) * 90, (y_telg - i) * 90) == null) {
                        } else {
                            omaleKohale();

                            return;
                        }
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x < 0) {
                if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y < 0) {
                    for (int i = 1; i < Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x); i++) {
                        if (Male.getMalend((x_telg - i) * 90, (y_telg - i) * 90) == null) {
                        } else {
                            omaleKohale();
                            return;
                        }
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x > 0) {
                if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y > 0) {
                    for (int i = 1; i < Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x); i++) {
                        if (Male.getMalend((x_telg + i) * 90, (y_telg + i) * 90) == null) {
                        } else {
                            omaleKohale();
                            return;
                        }
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x < 0) {
                if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y > 0) {
                    for (int i = 1; i < Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x); i++) {
                        if (Male.getMalend((x_telg - i) * 90, (y_telg + i) * 90) == null) {
                        } else {
                            omaleKohale();
                            return;
                        }
                    }
                }
            }
        }
        if (Male.liigutatavMalend.malendiTüüp.equals("ratsu")) {
            if (Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x) == 1 && Math.abs(Male.liigutatavMalend.y_telg - Male.sihtPunkti_y) == 2 ||
                    Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x) == 2 && Math.abs(Male.liigutatavMalend.y_telg - Male.sihtPunkti_y) == 1) {

            } else {
                omaleKohale();
                return;
            }

        }
        if (Male.liigutatavMalend.malendiTüüp.equals("lipp")) {
            if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y == 0 || Male.liigutatavMalend.x_telg - Male.sihtPunkti_x == 0 ||
                    Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x) == Math.abs(Male.liigutatavMalend.y_telg - Male.sihtPunkti_y)) {
            } else {
                omaleKohale();
                return;
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x > 0) {
                if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y < 0) {
                    for (int i = 1; i < Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x); i++) {
                        if (Male.getMalend((x_telg + i) * 90, (y_telg - i) * 90) == null) {
                        } else {
                            omaleKohale();

                            return;
                        }
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x < 0) {
                if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y < 0) {
                    for (int i = 1; i < Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x); i++) {
                        if (Male.getMalend((x_telg - i) * 90, (y_telg - i) * 90) == null) {
                        } else {
                            omaleKohale();
                            return;
                        }
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x > 0) {
                if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y > 0) {
                    for (int i = 1; i < Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x); i++) {
                        if (Male.getMalend((x_telg + i) * 90, (y_telg + i) * 90) == null) {
                        } else {
                            omaleKohale();
                            return;
                        }
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x < 0) {
                if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y > 0) {
                    for (int i = 1; i < Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x); i++) {
                        if (Male.getMalend((x_telg - i) * 90, (y_telg + i) * 90) == null) {
                        } else {
                            omaleKohale();
                            return;
                        }
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x < 0) {
                if (Male.liigutatavMalend.y_telg == Male.sihtPunkti_y) {
                    for (int i = 1; i < Male.sihtPunkti_x - Male.liigutatavMalend.x_telg; i++) {
                        if (Male.getMalend((x_telg - i) * 90, y_telg * 90) == null) {
                        } else {
                            omaleKohale();
                            return;

                        }
                    }
                }
            }
            if (Male.liigutatavMalend.x_telg - Male.sihtPunkti_x > 0) {
                if (Male.liigutatavMalend.y_telg == Male.sihtPunkti_y) {
                    for (int i = 1; i < Male.liigutatavMalend.x_telg - Male.sihtPunkti_x; i++) {
                        if (Male.getMalend((x_telg + i) * 90, y_telg * 90) == null) {
                        } else {
                            omaleKohale();
                            return;

                        }
                    }
                }
            }
            if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y > 0) {
                if (Male.liigutatavMalend.x_telg == Male.sihtPunkti_x) {
                    for (int i = 1; i < Male.liigutatavMalend.y_telg - Male.sihtPunkti_y; i++) {
                        if (Male.getMalend(x_telg * 90, (y_telg + i) * 90) == null) {
                        } else {
                            omaleKohale();
                            return;

                        }
                    }
                }
            }
            if (Male.liigutatavMalend.y_telg - Male.sihtPunkti_y < 0) {
                if (Male.liigutatavMalend.x_telg == Male.sihtPunkti_x) {
                    for (int i = 1; i < Male.sihtPunkti_y - Male.liigutatavMalend.y_telg; i++) {
                        if (Male.getMalend(x_telg * 90, (y_telg - i) * 90) == null) {
                        } else {
                            omaleKohale();
                            return;
                        }
                    }
                }
            }
        }


        if (Male.liigutatavMalend.malendiTüüp.equals("kuningas")) { // meetod mis sooritab kuninga käigu ning käivitab vangerduse legaalsuse kontrollimeetodi
            if (Male.liigutatavMalend.valgeNupp) {
                if (Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x) == 1
                        || Male.liigutatavMalend.x_telg - Male.sihtPunkti_x == 0) {
                } else {
                    if (vangerdusValge) {
                        valge_vangerdus();
                        return;
                    } else {
                        omaleKohale();
                        return;
                    }
                }
                if (Math.abs(Male.liigutatavMalend.y_telg - Male.sihtPunkti_y) == 1
                        || Male.liigutatavMalend.y_telg - Male.sihtPunkti_y == 0) {
                } else {
                    if (vangerdusValge) {
                        valge_vangerdus();
                        return;
                    } else {
                        omaleKohale();
                        return;
                    }
                }
            }
            if (!Male.liigutatavMalend.valgeNupp) {
                if (Math.abs(Male.liigutatavMalend.x_telg - Male.sihtPunkti_x) == 1
                        || Male.liigutatavMalend.x_telg - Male.sihtPunkti_x == 0) {
                } else {
                    if (vangerdusMust) {
                        must_vangerdus();
                        return;
                    } else {
                        omaleKohale();
                        return;
                    }
                }
                if (Math.abs(Male.liigutatavMalend.y_telg - Male.sihtPunkti_y) == 1
                        || Male.liigutatavMalend.y_telg - Male.sihtPunkti_y == 0) {
                } else {
                    if (vangerdusMust) {
                        must_vangerdus();
                        return;
                    } else {
                        omaleKohale();
                        return;
                    }
                }

            }

        }
        if (Male.getMalend(x_telg * 90, y_telg * 90) != null) {
            if (Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).valgeNupp != valgeNupp) {
                Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).kustuta();
            } else {
                omaleKohale();
                return;
            }
        }
        käik(x_telg, y_telg);
    }

    public void omaleKohale() {
        System.out.println("Ei ole lubatud käik");
        x = this.x_telg * 90;
        y = this.y_telg * 90;

    }

    public void kustuta() throws IOException { //kustutab ära võetud malendi, kui tegemist on kuningaga siis lõpetab mängu
        if(this.malendiTüüp.equals("kuningas")) {
            if(this.valgeNupp){
            System.out.println("mäng läbi must võitis");
        }
            if(!this.valgeNupp){
                System.out.println("mäng läbi valge võitis");
            }
            System.out.println("Mängu jooksul sooritatud käigud on kirjutatud faili: käigud.txt");
            FileWriter myWriter = new FileWriter("käigud.txt");
            myWriter.write(String.valueOf(Male.käigud));
            myWriter.close();

        }
        nupud.remove(this);

    }

    public void asenda() {
        Scanner scanner = new Scanner(System.in);  // Teeme scanneri
        System.out.println("sisesta valikust: lipp, vanker, ratsu, oda"); // nimekiri
        String valik = scanner.nextLine(); //
        while (!valik.equals("lipp") && !valik.equals("vanker") && !valik.equals("ratsu") && !valik.equals("oda")) {
            Scanner scanner2 = new Scanner(System.in);  // Teeme scanneri
            System.out.println("Tegid trükivea");
            System.out.println("sisesta palun uuesti valikust: lipp, vanker, ratsu, oda");
            valik = scanner.nextLine(); //
        }
        malendiTüüp = valik; //muudame etturi kasutaja valitud nupuks
    }

    public void lipustumine() { //meetod mis muudab viimasele reale jõudnud etturi mõneks muuks nupuks
        if (Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).y_telg == 0) {
            if (Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).valgeNupp) {
                if (Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).malendiTüüp.equals("ettur")) {
                    Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).asenda();
                }
            }
        }
        if (Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).y_telg == 7) {
            if (!Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).valgeNupp) {
                if (Objects.requireNonNull(Male.getMalend(x_telg * 90, y_telg * 90)).malendiTüüp.equals("ettur")) {


                    Male.getMalend(x_telg * 90, y_telg * 90).asenda();
                }
            }
        }
    }

    public void valge_vangerdus() {
        if (Male.liigutatavMalend.valgeNupp && vangerdusValge && Male.sihtPunkti_y == 7 && Male.liigutatavMalend.y_telg == 7
                && Male.sihtPunkti_x == 6 && Male.liigutatavMalend.x_telg == 4 && Male.getMalend(5 * 90, 7 * 90) == null && Male.getMalend(6 * 90, 7 * 90) == null
                && Objects.requireNonNull(Male.getMalend(7 * 90, 7 * 90)).malendiTüüp.equals("vanker")) {
            nupud.add(new Malend(5, 7, "vanker", true, nupud));
            nupud.add(new Malend(6, 7, "kuningas", true, nupud));
            nupud.remove(Male.getMalend(7 * 90, 7 * 90));
            nupud.remove(Male.getMalend(4 * 90, 7 * 90));
            Male.kord++;
            vangerdusValge = false;
            Male.käigud.add("0-0");


        } else {
            omaleKohale();
        }
        if (Male.liigutatavMalend.valgeNupp && vangerdusValge && Male.sihtPunkti_y == 7 && Male.liigutatavMalend.y_telg == 7
                && Male.sihtPunkti_x == 2 && Male.liigutatavMalend.x_telg == 4 && Male.getMalend(2 * 90, 7 * 90) == null &&
                Male.getMalend(3 * 90, 7 * 90) == null &&
                Male.getMalend(90, 7 * 90) == null
                && Objects.requireNonNull(Male.getMalend(0, 7 * 90)).malendiTüüp.equals("vanker")) {

            nupud.add(new Malend(3, 7, "vanker", true, nupud));
            nupud.add(new Malend(2, 7, "kuningas", true, nupud));
            nupud.remove(Male.getMalend(0 * 90, 7 * 90));
            nupud.remove(Male.getMalend(4 * 90, 7 * 90));
            Male.kord++;
            vangerdusValge = false;
            Male.käigud.add("0-0-0");
        } else omaleKohale();
    }
    public void must_vangerdus() {
        if (!Male.liigutatavMalend.valgeNupp && vangerdusMust && Male.sihtPunkti_y == 0 && Male.liigutatavMalend.y_telg == 0
                && Male.sihtPunkti_x == 6 && Male.liigutatavMalend.x_telg == 4 && Male.getMalend(5 * 90, 0 * 90) == null &&
                Male.getMalend(6 * 90, 0) == null
                && Objects.requireNonNull(Male.getMalend(7 * 90, 0)).malendiTüüp.equals("vanker")) {
            nupud.add(new Malend(5, 0, "vanker", false, nupud));
            nupud.add(new Malend(6, 0, "kuningas", false, nupud));
            nupud.remove(Male.getMalend(7 * 90, 0));
            nupud.remove(Male.getMalend(4 * 90, 0));
            Male.kord++;
            vangerdusMust = false;
            Male.käigud.add("0-0");


        } else {
            omaleKohale();
        }
        if (!Male.liigutatavMalend.valgeNupp && vangerdusMust && Male.sihtPunkti_y == 0 && Male.liigutatavMalend.y_telg == 0
                && Male.sihtPunkti_x == 2 && Male.liigutatavMalend.x_telg == 4 && Male.getMalend(2 * 90, 0 * 90) == null &&
                Male.getMalend(3 * 90, 0) == null &&
                Male.getMalend(90, 0) == null
                && Objects.requireNonNull(Male.getMalend(0, 0)).malendiTüüp.equals("vanker")) {
            nupud.add(new Malend(3, 0, "vanker", false, nupud));
            nupud.add(new Malend(2, 0, "kuningas", false, nupud));
            nupud.remove(Male.getMalend(0, 0));
            nupud.remove(Male.getMalend(4 * 90, 0));
            Male.kord++;
            vangerdusMust = false;
            Male.käigud.add("0-0-0");

        } else omaleKohale();

    }
}
