package inventory.repository;

import inventory.model.Inventory;
import inventory.model.Product;
import inventory.repository.fake.FakeInMemoryRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class InventoryRepositoryIntegrationTest {

    private InventoryService inventoryService;

    @InjectMocks
    private FakeInMemoryRepository inventoryRepository;
    @Mock
    private Inventory inventory;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        inventoryService = new InventoryService(inventoryRepository);
    }

    @Test
    public void testAdd(){
        Product product = new Product(0, "name", 3.14, 5, 1, 10, FXCollections.observableArrayList());
        Mockito.doNothing().when(inventory).addProduct(product);
        Mockito.when(inventory.getProducts()).thenReturn(FXCollections.observableArrayList());
        assert inventoryService.getAllProducts().size() == 0;
        Mockito.when(inventory.getProducts()).thenReturn(FXCollections.observableArrayList(product));
        inventoryService.addProduct("name", 3.14, 5, 1, 10, FXCollections.observableArrayList());
        assert inventoryService.getAllProducts().size() == 1;

        Mockito.verify(inventory, Mockito.times(2)).getProducts();
    }

    @Test
    public void testDelete(){
        Product product = new Product(0, "name", 3.14, 5, 1, 10, FXCollections.observableArrayList());
        Mockito.doNothing().when(inventory).removeProduct(product);
        Mockito.when(inventory.getProducts()).thenReturn(FXCollections.observableArrayList(product));
        assert inventoryService.getAllProducts().size() == 1;
        Mockito.when(inventory.getProducts()).thenReturn(FXCollections.observableArrayList());
        inventoryService.deleteProduct(product);
        assert inventoryService.getAllProducts().size() == 0;
        Mockito.verify(inventory, Mockito.times(1)).removeProduct(product);
        Mockito.verify(inventory, Mockito.times(2)).getProducts();
    }

}
