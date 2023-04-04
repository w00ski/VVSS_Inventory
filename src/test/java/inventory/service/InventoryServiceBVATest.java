package inventory.service;

import inventory.model.Part;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryServiceBVATest {

    private InventoryRepository repo;
    private InventoryService service;
    private ObservableList<Part> parts;

    @BeforeEach
    void setUp() {
        repo = new FakeInventoryRepository();
        service = new InventoryService(repo);
        parts = FXCollections.observableArrayList();
        parts.add(new Part(1, "Part 1", 0.001, 1, 1, 10));
        parts.add(new Part(2, "Part 2", 0.001, 1, 1, 20));
    }

    @AfterEach
    void tearDown() {

    }

    @DisplayName("Adding a product with valid input should succeed")
    @ParameterizedTest
    @CsvSource({"0.0,false", "0.02,true", "0.01,true"})
    void testAddProduct_InvalidPrice(double price, boolean isValid) {
        String name = "Denumire2";
        int inStock = 50;
        int min = 1;
        int max = 100;
        ObservableList<Part> addParts = parts;

        if (isValid) {
            service.addProduct(name, price, inStock, min, max, addParts);
            assertEquals(1, repo.getAllProducts().size());
        } else {
            assertThrows(RuntimeException.class,
                    () -> service.addProduct(name, price, inStock, min, max, addParts));
        }
    }

    @ParameterizedTest
    @CsvSource({"1,true", "0,false", "2,true"})
    @Tag("stock")
    void testAddProduct_MinStockValues(int inStock, boolean isValid) {
        String name = "Denumire1";
        double price = 10.2;
        int min = 1;
        int max = 100;
        ObservableList<Part> addParts = parts;

        if (isValid) {
            service.addProduct(name, price, inStock, min, max, addParts);
            assertEquals(1, repo.getAllProducts().size());
        } else {
            assertThrows(RuntimeException.class,
                    () -> service.addProduct(name, price, inStock, min, max, addParts));
        }
    }

    @ParameterizedTest
    @CsvSource({"100,true", "101,false", "99,true"})
    void testAddProduct_InvalidMinMax(int inStock, boolean isValid) {
        String name = "Product 4";
        double price = 1.0;
        int min = 1;
        int max = 100;
        ObservableList<Part> addParts = parts;

        if (isValid) {
            service.addProduct(name, price, inStock, min, max, addParts);
            assertEquals(1, repo.getAllProducts().size());
        } else {
            assertThrows(RuntimeException.class,
                    () -> service.addProduct(name, price, inStock, min, max, addParts));
        }
    }
}