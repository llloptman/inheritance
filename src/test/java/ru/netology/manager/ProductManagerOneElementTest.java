package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@ExtendWith(MockitoExtension.class)
class ProductManagerOneElementTest {
    //    @Mock
    ProductRepository repositoryBooks = new ProductRepository();
    ProductRepository repositorySmartphones = new ProductRepository();
    ProductManager managerBooks = new ProductManager(repositoryBooks);
    ProductManager managerSmartphones = new ProductManager(repositorySmartphones);


    Product book1 = new Book(1, "Book1", 10, "Book1 Author");
    Product book2 = new Book(2, "Book2", 10, "Book2 Author");
    Product book3 = new Book(3, "Book3", 10, "Book3 Author");
    Product book4 = new Book(4, "Book1", 10, "Book1 Author");
    Product smartphone1 = new Smartphone(1, "Nokia", 50, "China");
    Product smartphone2 = new Smartphone(2, "Nokia2", 50, "China");
    Product smartphone3 = new Smartphone(3, "Nokia", 50, "China");
    Product product = new Product(10, "product", 0);

    @BeforeEach
    public void setUp() {
        managerBooks.addToRepository(book3);
        managerSmartphones.addToRepository(smartphone1);
    }

    @Test
    void addBookToRepository() {
        managerBooks.addToRepository(book1);
        Product[] actual = managerBooks.getProducts(repositoryBooks);
        Product[] expected = new Product[2];
        expected[0] = book3; //в зависимости от того, что добавляли в BeforeEach
        expected[1] = book1;
        assertArrayEquals(expected, actual);
    }

    @Test
    void addSmartphoneToRepository() {
        managerBooks.addToRepository(smartphone3);
        Product[] actual = managerBooks.getProducts(repositoryBooks);
        Product[] expected = new Product[2];
        expected[0] = book3; //в зависимости от того, что добавляли в BeforeEach
        expected[1] = smartphone3;
        assertArrayEquals(expected, actual);
    }

    @Test
    void getProducts() {
        Product[] actual = managerSmartphones.getProducts(repositorySmartphones);
        Product[] expected = new Product[1];
        expected[0] = smartphone1; //в зависимости от того, что добавляли в BeforeEach
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindProductByBookName() {
        Product[] actual = managerBooks.searchBy("Book3");
        Product[] expected = new Product[1];
        expected[0] = book3; //в зависимости от того, что добавляли в BeforeEach
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindProductByBookName() {
        Product[] actual = managerBooks.searchBy("Book5");
        Product[] expected = new Product[0];
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindProductByBookAuthor() {
        Product[] actual = managerBooks.searchBy("Book3 Author");
        Product[] expected = new Product[1];
        expected[0] = book3; //в зависимости от того, что добавляли в BeforeEach
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindProductBySmartphoneName() {
        Product[] actual = managerSmartphones.searchBy("Nokia");
        Product[] expected = new Product[1];
        expected[0] = smartphone1; //в зависимости от того, что добавляли в BeforeEach
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindProductBySmartphoneCreator() {
        Product[] actual = managerSmartphones.searchBy("China");
        Product[] expected = new Product[1];
        expected[0] = smartphone1; //в зависимости от того, что добавляли в BeforeEach
        assertArrayEquals(expected, actual);
    }

    @Test
    void matches() {
        System.out.println("actualByName");
        boolean actualByName = managerBooks.matches(book3, "Book3");
        assertEquals(true, actualByName);

        System.out.println("actualByAuthor");
        boolean actualByAuthor = managerBooks.matches(book3, "Book3 Author");
        assertEquals(true, actualByAuthor);

        System.out.println("actualByCreator");
        boolean actualByCreator = managerSmartphones.matches(smartphone2, "China");
        assertEquals(true, actualByCreator);

        System.out.println("actualNotFound");
        boolean actualNotFound = managerSmartphones.matches(smartphone1, "Chinad");
        assertEquals(false, actualNotFound);

        System.out.println("actualCaseInsensitive");
        boolean actualCaseInsensitive = managerSmartphones.matches(smartphone2, "CHINA");
        assertEquals(true, actualCaseInsensitive);

        System.out.println("actual Not Book nor Smartphone");
        boolean actualNotBookNorSmartphone = managerSmartphones.matches(product, "CHINA");
        assertEquals(false, actualNotBookNorSmartphone);

    }
}