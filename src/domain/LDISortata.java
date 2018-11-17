package domain;

public class LDISortata {
    private Nod prim;
    private Nod ultim;
    private int lungime;
    private CompareGPS relatie;

    /**
     * Constructor
     *
     * @param c un camp de tip comparator
     */
    public LDISortata(CompareGPS c) {
        this.relatie = c;
        this.prim = null;
        this.ultim = null;
        this.lungime = 0;
    }

    /**
     * Clasa Nod
     */
    public class Nod {
        private GPS elem;
        private Nod urmator;
        private Nod anterior;

        /**
         * Constructorul clasei nod
         *
         * @param e elemente de tip GPS
         */
        public Nod(GPS e) {
            this.elem = e;
            this.urmator = null;
            this.anterior = null;
        }
    }

    /**
     * Adauga elementul dat intr-o coada cu prioritati;
     *
     * @param coord elementul de tip GPS
     */
    public void offer(GPS coord) {
        Nod nodNou = new Nod(coord);
        nodNou.elem = coord;
        nodNou.urmator = null;
        nodNou.anterior = null;

        if (this.prim == null) {       // daca prim este null atunci nodul nou va fi si prim si ultim
            this.prim = nodNou;
            this.ultim = nodNou;
        } else if (this.relatie.compare(coord, this.prim.elem) == -1) {  // comparam cu prim
            nodNou.urmator = this.prim;
            this.prim.anterior = nodNou;
            this.prim = nodNou;
        } else if (this.relatie.compare(coord, this.ultim.elem) == 1) {  //  comparam cu ultim
            nodNou.anterior = this.ultim;
            this.ultim.urmator = nodNou;
            this.ultim = nodNou;
        } else {           // daca distanta este > decat dist lui prim si < decat dist lui ultim va fi pus inre 2 noduri
            Nod nodCurent = this.prim;
            while (nodCurent != null && this.relatie.compare(coord, nodCurent.elem) == 1) { // parcurgem coada si comparam prioritatile
                nodCurent = nodCurent.urmator;
            }
            if (nodCurent != null && this.relatie.compare(coord, nodCurent.elem) == -1) {  // daca nodul curent este diferit de null si
                // prioritatea nodului urmator este mai mare atunci inseram noul nod
                nodNou.anterior = nodCurent.anterior;
                nodNou.urmator = nodCurent;
                nodCurent.anterior.urmator = nodNou;
                nodCurent.anterior = nodNou;
            }
        }
        this.lungime++;

    }

    /**
     * Sterge elementul cu prioritate maxima din coada cu prioritati
     *
     * @return elementul sters
     */
    public GPS remove() {
        GPS atractie = this.prim.elem;
        if (this.lungime > 1) {   // inseamna ca am o coada cu mini 2 elemente
            this.prim = this.prim.urmator;
        } else {
            this.prim = null;
            this.ultim = this.prim;
        }
        this.lungime--;
        return atractie;
    }

    /**
     * Arata si sterge elementul cu prioritate maxima din coada
     *
     * @return elementul cu prioritate maxima sau null daca coada e vida
     */
    public GPS pool() {
        if (isEmpty()) {
            return null;
        }
        GPS rezultat = this.prim.elem;
        remove();
        return rezultat;
    }

    /**
     * Arata, dar nu sterge, elementul cu prioritate maxima din coada
     *
     * @return elementul cu prioritate maxima sau null daca coada e vida
     */
    public GPS peek() {
        return (getLungime() == 0) ? null : (GPS) this.prim.elem;
    }

    /**
     * Sterge toate elementele din coada.
     */
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    /**
     * Verifica daca coada este vida
     *
     * @return adevarat daca este goala, sau fals daca nu e goala
     */
    public boolean isEmpty() {
        if (this.lungime == 0) {
            return true;
        }
        return false;
    }

    /**
     * Getter pentru lungimea cozii cu prioritati
     *
     * @return lungimea cozii
     */
    public int getLungime() {
        return this.lungime;
    }
}
