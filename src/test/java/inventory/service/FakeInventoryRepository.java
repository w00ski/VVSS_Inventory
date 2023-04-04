package inventory.service;

import inventory.model.Product;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FakeInventoryRepository extends InventoryRepository {

    private final ObservableList<Product> productList;

    public FakeInventoryRepository() {
        productList = FXCollections.observableArrayList();
    }

    @Override
    public void addProduct(Product product) {
        getInventory().addProduct(product);
        productList.add(product);
    }

    @Override
    public ObservableList<Product> getAllProducts() {
        return productList;
    }
}
