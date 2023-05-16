package inventory.model;

import inventory.model.Inventory;
import inventory.model.Product;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryUnitTest {

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
        Product prod = inventory.lookupProduct("3");
        assert (prod != null);
    }

    @Test
    void lookForProductByNameTest() {
        Product prod = inventory.lookupProduct("Second");
        assert (prod != null);
    }

    @Test
    void lookForProductNotFoundTest() {
        Product prod = inventory.lookupProduct("CantFind");
        assert (prod.getName() == null);
    }

    @Test
    void lookForProductAnythingTest() {
        Product prod = inventory.lookupProduct("AnythingReally");
        assert (prod.getName() == null);
    }
}
