package domain;

public class GPS {

    String atractie;
    int pozitieX, pozitieY;
    double distanta;

    public GPS(String atractie, int pozitieX, int pozitieY) {
        this.atractie = atractie;
        this.pozitieX = pozitieX;
        this.pozitieY = pozitieY;
        this.distanta = Math.sqrt((pozitieX * pozitieX) + (pozitieY * pozitieY));
    }

    public String getAtractie() {
        return atractie;
    }

    public int getPozitieX() {
        return pozitieX;
    }

    public int getPozitieY() {
        return pozitieY;
    }

    public double getDistanta() {
        return distanta;
    }


}
