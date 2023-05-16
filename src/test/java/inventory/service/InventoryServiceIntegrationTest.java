package inventory.service;

import inventory.model.Product;
import inventory.repository.fake.FakeInMemoryRepository;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class InventoryServiceIntegrationTest {

    private InventoryService inventoryService;
    private FakeInMemoryRepository inventoryRepository;

    @BeforeEach
    public void setUp() {
        inventoryRepository = new FakeInMemoryRepository();
        inventoryService = new InventoryService(inventoryRepository);
    }

    private void assertSize() {
        assert inventoryService.getAllProducts().size() == inventoryRepository.getInventory().getProducts().size();
    }

    @Test
    public void testAdd() {
        assert inventoryService.getAllProducts().size() == 0;
        assertSize();

        Product product = Mockito.mock(Product.class);
        Mockito.when(product.getName()).thenReturn("name");
        Mockito.when(product.getPrice()).thenReturn(10.3);
        Mockito.when(product.getInStock()).thenReturn(10);
        Mockito.when(product.getMin()).thenReturn(1);
        Mockito.when(product.getMax()).thenReturn(100);

        inventoryService.addProduct(
                product.getName(),
                product.getPrice(),
                product.getInStock(),
                product.getMin(),
                product.getMax(),
                FXCollections.observableArrayList()
        );

        assert inventoryService.getAllProducts().size() == 1;
        assertSize();
    }

    @Test
    public void testDelete() {
        Product product = Mockito.mock(Product.class);
        Mockito.when(product.getName()).thenReturn("name");
        Mockito.when(product.getPrice()).thenReturn(10.3);
        Mockito.when(product.getInStock()).thenReturn(10);
        Mockito.when(product.getMin()).thenReturn(1);
        Mockito.when(product.getMax()).thenReturn(100);

        inventoryService.addProduct(
                product.getName(),
                product.getPrice(),
                product.getInStock(),
                product.getMin(),
                product.getMax(),
                FXCollections.observableArrayList()
        );
        assert inventoryService.getAllProducts().size() == 1;
        assertSize();

        inventoryService.deleteProduct(product);
        assert inventoryService.getAllProducts().size() == 1;
        assertSize();
    }

}
