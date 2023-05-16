package inventory.repository;

import inventory.model.Inventory;
import inventory.model.Product;
import inventory.repository.fake.FakeInMemoryRepository;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;

class InventoryRepositoryTest {

    @InjectMocks
    private FakeInMemoryRepository repo;

    @Mock
    private Inventory inventory;

    private Product product;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        repo = new FakeInMemoryRepository();
        repo.setInventory(inventory);
        product = new Product(4, "name4", 3.14, 5, 1, 10, FXCollections.observableArrayList());
    }

    @Test
    public void addProduct(){
        Mockito.when(inventory.getProducts()).thenReturn(FXCollections.observableArrayList());
        assert repo.getAllProducts().size() == 0;
        Mockito.verify(inventory, times(1)).getProducts();

        Mockito.when(inventory.getProducts()).thenReturn(FXCollections.observableArrayList(product));
        Mockito.doNothing().when(inventory).addProduct(product);
        repo.addProduct(product);
        Mockito.verify(inventory, times(1)).addProduct(product);
        assert repo.getAllProducts().size() == 1;
        Mockito.verify(inventory, times(2)).getProducts();
    }

    @Test
    public void removeProduct(){
        Mockito.when(inventory.getProducts()).thenReturn(FXCollections.observableArrayList());
        Mockito.doNothing().when(inventory).removeProduct(product);
        repo.deleteProduct(product);
        assert 0 == repo.getAllProducts().size();
        Mockito.verify(inventory, times(1)).removeProduct(product);
        Mockito.verify(inventory, times(1)).getProducts();
    }

}