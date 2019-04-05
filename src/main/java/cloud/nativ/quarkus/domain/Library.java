package cloud.nativ.quarkus.domain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * The Library implementation supports lending books.
 */
@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class Library {
    @Inject
    EntityManager entityManager;

    /**
     * Return a book with given ISBN on given load.
     *
     * @param isbn   the ISBN
     * @param loanId the loan ID
     */
    public void returnBook(String isbn, String loanId) {
        Book book = entityManager.getReference(Book.class, isbn);
        book.removeLoan(new Loan(loanId));
    }

    /**
     * Lend a book with given ISBN and create a new loan on it.
     *
     * @param isbn the book ISBN
     * @param loan the loan info
     */
    public void lendBook(String isbn, Loan loan) {
        Book book = entityManager.getReference(Book.class, isbn);
        book.addLoan(loan);
    }

    /**
     * Get the loan identified by its ID.
     *
     * @param loanId the loan ID
     * @return the loan
     */
    public Loan loanInfo(String loanId) {
        return entityManager.getReference(Loan.class, loanId);
    }
}
