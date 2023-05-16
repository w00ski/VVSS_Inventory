package inventory.model;


import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InventoryTest {

    private Inventory inventory;

    private Product product;

    @BeforeEach
    public void setUp(){
        inventory = new Inventory();
        product = new Product(3, "name3", 15.18, 15, 1, 20, FXCollections.observableArrayList());
    }

    @Test
    public void testAdd(){
        inventory.addProduct(product);
        assert 1 == inventory.getProducts().size();
    }

    @Test
    public void testRemove(){
        inventory.removeProduct(product);
        assert 0 == inventory.getProducts().size();
    }

}