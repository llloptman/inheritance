package ru.netology.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
class ProductManagerEmptyTest {
    //    @Mock
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);

    Product book1 = new Book(1, "Book1", 10, "Book1 Author");
    Product book2 = new Book(2, "Book2", 10, "Book2 Author");
    Product book3 = new Book(3, "Book3", 10, "Book3 Author");
    Product smartphone1 = new Smartphone(1, "Nokia", 50, "China");
    Product smartphone2 = new Smartphone(2, "Nokia2", 50, "China");
    Product smartphone3 = new Smartphone(3, "Nokia", 50, "China");

    @Test
    void addToRepository() {
        manager.addToRepository(book1);
        Product[] actual = manager.getProducts(repository);
        Product[] expected = new  Product[1];
        expected[0] = book1;
        assertArrayEquals(expected, actual);
    }

    @Test
    void getProducts() {
        Product[] actual = manager.getProducts(repository);
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByBookName() {
        Product[] actual = manager.searchBy("Book1");
        Product[] expectad = new Product[0];
        assertArrayEquals(expectad, actual);
    }

    @Test
    void searchByBookAuthor() {
        Product[] actual = manager.searchBy("Book1 Author");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchBySmartphoneName() {
        Product[] actual = manager.searchBy("Book1");
        Product[] expectad = new Product[0];
        assertArrayEquals(expectad, actual);
    }

    @Test
    void searchBySmartphoneCreator() {
        Product[] actual = manager.searchBy("Book1");
        Product[] expectad = new Product[0];
        assertArrayEquals(expectad, actual);
    }

    @Test
    void matches() {
        boolean actual = manager.matches(book1,"blabla");
        assertEquals(false,actual);
    }
}