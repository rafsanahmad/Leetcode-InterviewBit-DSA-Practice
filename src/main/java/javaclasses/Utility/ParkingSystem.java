package javaclasses.Utility;

class ParkingSystem {

    int bigCount = 0;
    int mediumCount = 0;
    int smallCount = 0;

    public ParkingSystem(int big, int medium, int small) {
        this.bigCount = big;
        this.mediumCount = medium;
        this.smallCount = small;
    }

    public boolean addCar(int carType) {
        switch (carType) {
            case 1:
                if (bigCount > 0)
                    --bigCount;
                else
                    return false;
                break;
            case 2:
                if (mediumCount > 0)
                    --mediumCount;
                else
                    return false;
                break;
            case 3:
                if (smallCount > 0)
                    --smallCount;
                else
                    return false;
                break;
        }
        return true;
    }
}
