package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductManagerNonEmptyTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book first = new Book(1, "Head First Java", 300, "Kathy Sierra");
    private Book second = new Book(2, "Foundations of Software Testing", 350, "Cem Kaner");
    private Smartphone third = new Smartphone(3, "IPhone 13", 130, "Apple");
    private Smartphone fourth = new Smartphone(4, "Galaxy S21", 150, "Samsung");
    private Smartphone fifth = new Smartphone(5, "Galaxy A31", 100, "Samsung");

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    void shouldGetAll() {
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, fourth, fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchNameInBooks() {
        Product[] actual = manager.searchBy("foundations of software testing");
        Product[] expected = new Product[]{second};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchAuthorInBooks() {
        Product[] actual = manager.searchBy("KATHY SIERRA");
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchNameInSmartphones() {
        Product[] actual = manager.searchBy("ipHONE 13");
        Product[] expected = new Product[]{third};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchManufacturerInSmartphones() {
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[]{third};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchByIf2SmartphonesWithManufacturerExist() {
        Product[] actual = manager.searchBy("Samsung");
        Product[] expected = new Product[]{fourth, fifth};
        assertArrayEquals(expected, actual);
    }
}