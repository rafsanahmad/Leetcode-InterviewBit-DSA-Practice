package SystemDesign.VendingMachine.utils;

/*The Vending Machine throws this exception if the user requests a product that is sold out*/
public class SoldOutException extends RuntimeException {
    private String message;

    public SoldOutException(String string) {
        this.message = string;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
