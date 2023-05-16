package inventory.repository.fake;

import inventory.model.Inventory;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import javafx.collections.ObservableList;

public class FakeInMemoryRepository extends InventoryRepository {
    private Inventory inventory;

    public FakeInMemoryRepository() {
        this.inventory = new Inventory();
    }

    @Override
    public void addProduct(Product product){
        inventory.addProduct(product);
    }

    @Override
    public void deleteProduct(Product product){
        inventory.removeProduct(product);
    }

    @Override
    public void setInventory(Inventory inventory){
        this.inventory=inventory;
    }

    @Override
    public Inventory getInventory(){
        return inventory;
    }

    @Override
    public ObservableList<Product> getAllProducts(){
        return inventory.getProducts();
    }

}
