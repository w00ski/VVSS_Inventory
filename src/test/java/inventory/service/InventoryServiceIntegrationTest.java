package inventory.service;

import inventory.model.Product;
import inventory.repository.fake.FakeInMemoryRepository;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryServiceIntegrationTest {

    private InventoryService inventoryService;
    private FakeInMemoryRepository inventoryRepository;

    @BeforeEach
    public void setUp(){
        inventoryRepository = new FakeInMemoryRepository();
        inventoryService = new InventoryService(inventoryRepository);
    }

    private void testSameSize(){
        assert inventoryService.getAllProducts().size() == inventoryRepository.getInventory().getProducts().size();
    }

    @Test
    public void testAdd(){
        assert inventoryService.getAllProducts().size() == 0;
        testSameSize();

        inventoryService.addProduct("name", 3.14, 5, 1, 10, FXCollections.observableArrayList());
        assert inventoryService.getAllProducts().size() == 1;
        testSameSize();
    }

    @Test
    public void testDelete(){
        Product product = new Product(0, "name", 3.14, 5, 1, 10, FXCollections.observableArrayList());
        inventoryService.addProduct("name", 3.14, 5, 1, 10, FXCollections.observableArrayList());
        assert inventoryService.getAllProducts().size() == 1;
        testSameSize();

        inventoryService.deleteProduct(product);
        assert inventoryService.getAllProducts().size() == 1;
        testSameSize();
    }

}
