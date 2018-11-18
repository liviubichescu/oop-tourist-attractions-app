package ui;

import domain.CompareGPS;
import domain.GPS;
import domain.LDISortata;

import java.util.Scanner;

public class Console {

    private CompareGPS rel;
    private LDISortata vizita;
    private LDISortata coadaBackup;

    public Console(CompareGPS rel, LDISortata vizita, LDISortata coadaBackup) {
        this.rel = rel;
        this.vizita = vizita;
        this.coadaBackup = coadaBackup;
    }

    /**
     * Meniu utilizator.
     */
    private void showMenu() {
        System.out.println();
        System.out.println("1. Adaugare atractie turistica");
        System.out.println("2. Cele mai apropiate atractii turistice!!! ");
        System.out.println("3. Cele mai apropiate atractii dupa o anumita distanta data!!! ");
        System.out.println("4. Afisare atractii turistice");
        System.out.println("x. Exit");
        System.out.println("Dati optiunea:");

    }

    /**
     * Adauga o noua atractie turistica.
     */
    private void adaugaAtractie() {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduceti numele atractiei turistice:");
        String atractie = in.next();
        System.out.println("Dati pozitia X:");
        int pozitiaX = in.nextInt();
        System.out.println("Dati pozitia Y:");
        int pozitiaY = in.nextInt();
        vizita.offer(new GPS(atractie, pozitiaX, pozitiaY));
        System.out.println("----------------");

    }

    /**
     * Afiseaza cele mai apropiate K atractii turistice.
     */
    private void atractiiApropiate() {
        Scanner in = new Scanner(System.in);
        int i = 0;
        System.out.println("Introduceti la cate atractii poate sa mearga Emilia: ");
        int k = in.nextInt();
        System.out.println("Cele mai apropiate " + k + " atractii de Emilia sunt:");
        if (k > vizita.getLungime() || k < 1) {
            System.out.println("Emilia poate sa mearga la maxim " + vizita.getLungime() + " atractii!!!");
            System.out.println("Pentru a le vedea, introduceti un numar <= cu " + vizita.getLungime() + " !!!");
        } else {
            if (vizita.getLungime() > 0) {
                while (i < k) {
                    GPS t = vizita.remove();
                    System.out.println(t.getAtractie() + "------- " + "pozitieX: " + t.getPozitieX() + "; " + "pozitieY: "
                            + t.getPozitieY() + "; " + "distanta: " + t.getDistanta());
                    i++;
                    coadaBackup.offer(t);
                }
                if (coadaBackup.getLungime() > 0) {
                    int j = 0;
                    while (!coadaBackup.isEmpty()) {
                        GPS m = coadaBackup.remove();
                        vizita.offer(m);
                    }
                }
            }
        }
    }

    /**
     * Afiseaza cate atractii pot fi vizitate, dupa o anumita distanta data.
     */
    private void atractiiDupaDistanta() {
        Scanner in = new Scanner(System.in);
        System.out.println("Introduceti distanta pe care Emilia poate sa o parcurga: ");
        int m = in.nextInt();
        double distVizita = 0;
        while (true) {
            GPS t = vizita.remove();
            if ((distVizita + (t.getDistanta() * 2)) < m) {
                System.out.println(t.getAtractie() + "------- " + "pozitieX: " + t.getPozitieX() + "; " + "pozitieY: "
                        + t.getPozitieY() + "; " + "distanta: " + t.getDistanta());
                distVizita = (t.getDistanta() * 2) + distVizita;
                coadaBackup.offer(t);
            } else {
                coadaBackup.offer(t);
                break;
            }
        }
        int j = 0;
        while (coadaBackup.getLungime() != 0) {
            GPS t = coadaBackup.remove();
            j++;
            vizita.offer(t);
        }
        System.out.println("Distanta totala a atractiilor este: " + distVizita);
    }

    /**
     * Afiseaza toate atractiile.
     */
    private void afiseaza() {
        while (!vizita.isEmpty()) {
            GPS t = vizita.remove();
            System.out.println(t.getAtractie() + " ------" + " pozitieX: " + t.getPozitieX() + ";" + " " +
                    " pozitieY: " + +t.getPozitieY() + "; " + " distanta: " + t.getDistanta());
            coadaBackup.offer(t);
        }
        while (!coadaBackup.isEmpty()) {
            GPS m = coadaBackup.remove();
            vizita.offer(m);
        }
    }

    /**
     * Citeste optiunile introduse de utilizator.
     */
    public void startMenu() {
        Scanner in = new Scanner(System.in);

        loop:
        while (true) {
            showMenu();
            String optiune = in.next();
            switch (optiune) {
                case "1":
                    adaugaAtractie();
                    break;
                case "2":
                    atractiiApropiate();
                    break;
                case "3":
                    atractiiDupaDistanta();
                    break;
                case "4":
                    afiseaza();
                    break;
                case "x":
                    break loop;
                default:
                    System.out.println("Comanda invalida");
                    break;
            }
            System.out.println();
        }
    }

}
