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
                "ZeroName",
                10,
                50,
                0,
                100,
                FXCollections.emptyObservableList()
        ));
        inventory.addProduct(new Product(
                1,
                "FirstName",
                10,
                50,
                0,
                100,
                FXCollections.emptyObservableList()
        ));
        inventory.addProduct(new Product(
                2,
                "SecondName",
                10,
                50,
                0,
                100,
                FXCollections.emptyObservableList()
        ));
        inventory.addProduct(new Product(
                3,
                "ThirdName",
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
        var prod = inventory.lookupProduct("3");
        assert (prod != null);
    }

    @Test
    void lookForProductByNameTest() {
        var prod = inventory.lookupProduct("Second");
        assert (prod != null);
    }

    @Test
    void lookForProductNotFoundTest() {
        var prod = inventory.lookupProduct("CantFind");
        assert (prod.getName() == null);
    }

    @Test
    void lookForProductAnythingTest() {
        var prod = inventory.lookupProduct("AnythingReally");
        assert (prod.getName() == null);
    }
}
