package inventory;

import inventory.model.Inventory;
import inventory.model.Part;
import inventory.model.Product;
import inventory.repository.InventoryRepository;
import inventory.service.FakeInventoryRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.lang.reflect.Array;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.addProduct(new Product(
                0,
                "Name0",
                10,
                50,
                0,
                100,
                FXCollections.emptyObservableList()
        ));
        inventory.addProduct(new Product(
                1,
                "Name1",
                10,
                50,
                0,
                100,
                FXCollections.emptyObservableList()
        ));
        inventory.addProduct(new Product(
                2,
                "Name2",
                10,
                50,
                0,
                100,
                FXCollections.emptyObservableList()
        ));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void lookForProductByIdTest() {
        var prod = inventory.lookupProduct("2");
        assert (prod != null);
    }

    @Test
    void lookForProductByNameTest() {
        var prod = inventory.lookupProduct("Name2");
        assert (prod != null);
    }

    @Test
    void lookForProductByNameNotFoundTest() {
        var prod = inventory.lookupProduct("Name100");
        assert (prod.getName() == null);
    }

    @Test
    void lookForProductByIdNotFoundTest() {
        var prod = inventory.lookupProduct("100");
        assert (prod.getName() == null);
    }

    @Test
    void lookForProductNotFoundTest() {
        var prod = inventory.lookupProduct("");
        assert (prod == null);
    }
}
