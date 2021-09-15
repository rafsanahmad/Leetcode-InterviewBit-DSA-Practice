package SystemDesign.VendingMachine.api;

import java.util.List;

import SystemDesign.VendingMachine.model.Bucket;
import SystemDesign.VendingMachine.model.Coin;
import SystemDesign.VendingMachine.model.Item;

public interface VendingMachine {
    public long selectItemAndGetPrice(Item item);

    public void insertCoin(Coin coin);

    public List<Coin> refund();

    public Bucket<Item, List<Coin>> collectItemAndChange();

    public void reset();
}
