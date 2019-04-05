package cloud.nativ.quarkus.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * The Bookshelf implementation is used to find and managed books.
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class Bookshelf {

    @Inject
    private EntityManager entityManager;

    /**
     * Returns the list of all books in the shelf.
     *
     * @return a collection of books
     */
    public Collection<Book> findAll() {
        TypedQuery<Book> findAll = entityManager.createNamedQuery(Book.FIND_ALL, Book.class);
        return Collections.unmodifiableCollection(findAll.getResultList());
    }

    /**
     * Find the book by its ISBN and return a reference to it.
     *
     * @param isbn the ISBN
     * @return the book
     */
    public Book findByISBN(String isbn) {
        return entityManager.getReference(Book.class, Objects.requireNonNull(isbn));
    }

    /**
     * Check if book under given ISBN already exists.
     *
     * @param isbn the ISBN
     * @return true of exists, otherwise false
     */
    public boolean exists(String isbn) {
        return entityManager.find(Book.class, isbn) != null;
    }

    /**
     * Creates a new book in the bookshelf.
     *
     * @param book a book to create
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void create(Book book) {
        Objects.requireNonNull(book);
        entityManager.persist(book);
    }

    /**
     * Creates a new book in the bookshelf.
     *
     * @param book a book to update
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void update(String isbn, Book book) {
        Objects.requireNonNull(book);
        entityManager.merge(book);
    }

    /**
     * Deletes a book via ISBN.
     *
     * @param isbn the ISBN
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(String isbn) {
        Objects.requireNonNull(isbn);
        Book reference = entityManager.getReference(Book.class, isbn);
        entityManager.remove(reference);
    }
}
