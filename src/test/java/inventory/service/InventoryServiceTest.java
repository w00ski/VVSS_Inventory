package inventory.service;


import inventory.model.Product;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;

class InventoryServiceTest {
    @InjectMocks
    private InventoryService inventoryService;
    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProduct(){
        Product product = new Product(0, "name", 3.14, 4, 1, 10, FXCollections.observableArrayList());
        Mockito.doNothing().when(inventoryRepository).addProduct(product);

        Mockito.when(inventoryRepository.getAllProducts()).thenReturn(FXCollections.observableArrayList());
        assert inventoryService.getAllProducts().size() == 0;
        Mockito.when(inventoryRepository.getAllProducts()).thenReturn(FXCollections.observableArrayList(product));
        inventoryService.addProduct("name", 3.14, 4, 1, 10, FXCollections.observableArrayList());
        assert  inventoryService.getAllProducts().size() == 1;

        Mockito.verify(inventoryRepository, times(2)).getAllProducts();
    }

    @Test
    public void testRemoveProduct(){
        Product product = new Product(0, "name", 3.14, 4, 1, 10, FXCollections.observableArrayList());
        Mockito.doNothing().when(inventoryRepository).deleteProduct(product);
        Mockito.when(inventoryRepository.getAllProducts()).thenReturn(FXCollections.observableArrayList(product));
        assert inventoryService.getAllProducts().size() == 1;
        Mockito.when(inventoryRepository.getAllProducts()).thenReturn(FXCollections.observableArrayList());
        inventoryService.deleteProduct(product);
        assert inventoryService.getAllProducts().size() == 0;

        Mockito.verify(inventoryRepository, times(1)).deleteProduct(product);
        Mockito.verify(inventoryRepository, times(2)).getAllProducts();
    }
}