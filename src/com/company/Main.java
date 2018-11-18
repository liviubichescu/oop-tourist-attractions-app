package com.company;

import domain.CompareGPS;
import domain.GPS;
import domain.LDISortata;
import ui.Console;

import java.util.PriorityQueue;
import java.util.Scanner;

/*
Atracții  turistice  de vizitat
Descriere:
Emilia vrea să viziteze cât mai multe atracții turistice din jurul ei. Dar din păcate ea nu are
foarte mult timp liber, deci decide să vizitele doar cele mai apropiate K atracții. Fiecare atracție turistică
este reprezentată prin coordonatele ei GPS, x și y. Emilia locuiește la coodonatele GPS 0,0.

Container folosit: TAD Coadă cu priorități.
 */
public class Main {

    public static void main(String[] args) {

        CompareGPS rel = new CompareGPS();
        LDISortata vizita = new LDISortata(rel);
        LDISortata coadaBackup = new LDISortata(rel);
        Console console = new Console(rel, vizita, coadaBackup);

        GPS atractie1 = new GPS("Plaja", 10, 13);   //16
        GPS atractie2 = new GPS("Centru", 16, 13);  //20
        GPS atractie3 = new GPS("Piata", 7, 40);    //40
        GPS atractie4 = new GPS("Cinema", 100, 32); //104
        GPS atractie5 = new GPS("Palat", 90, 50);    //102
        GPS atractie6 = new GPS("Opera", 7, 25);    //25
        GPS atractie7 = new GPS("Mall", 25, 115);   //117

        vizita.offer(atractie1);
        vizita.offer(atractie2);
        vizita.offer(atractie3);
        vizita.offer(atractie4);
        vizita.offer(atractie5);
        vizita.offer(atractie6);
        vizita.offer(atractie7);

        console.startMenu();

    }
}
