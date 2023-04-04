package inventory.service;

import inventory.model.Part;
import inventory.repository.InventoryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InventoryServiceECPTest {

    private InventoryRepository repo;
    private InventoryService service;
    private ObservableList<Part> parts;

    @BeforeEach
    void setUp() {
        repo = new FakeInventoryRepository();
        service = new InventoryService(repo);
        parts = FXCollections.observableArrayList();
        parts.add(new Part(1, "Part 1", 1.0, 5, 1, 10));
        parts.add(new Part(2, "Part 2", 2.0, 10, 1, 20));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @Order(2)
    void testAddProduct_ValidInput() {
        String name = "Denumire1";
        double price = 10.2;
        int inStock = 2;
        int min = 1;
        int max = 100;
        ObservableList<Part> addParts = parts;

        service.addProduct(name, price, inStock, min, max, addParts);

        assertEquals(1, repo.getAllProducts().size());
    }

    @Test
    @Order(1)
    void testAddProduct_InvalidPrice() {
        String name = "Denumire2";
        double price = 0.0;
        int inStock = 2;
        int min = 1;
        int max = 100;
        ObservableList<Part> addParts = parts;

        // Execute & Verify
        assertThrows(RuntimeException.class,
                () -> service.addProduct(name, price, inStock, min, max, addParts));
    }

    @Test
    @Order(4)
    @Disabled
    void testAddProduct_InvalidPriceType() {
        String name = "Denumire2";
        double price =   Double.parseDouble("zece ron");
        int inStock = 2;
        int min = 1;
        int max = 100;
        ObservableList<Part> addParts = parts;

        // Execute & Verify
        assertThrows(RuntimeException.class,
                () -> service.addProduct(name, price, inStock, min, max, addParts));
    }

    @Test
    @Order(3)
    @RepeatedTest(value = 5)
    void testAddProduct_InvalidMin() {
        String name = "Denumire2";
        double price =   10.2;
        int inStock = -5;
        int min = 1;
        int max = 100;
        ObservableList<Part> addParts = parts;

        // Execute & Verify
        assertThrows(RuntimeException.class,
                () -> service.addProduct(name, price, inStock, min, max, addParts));
    }
}