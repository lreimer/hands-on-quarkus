package cloud.nativ.quarkus.boundary;

import cloud.nativ.quarkus.domain.Book;
import cloud.nativ.quarkus.domain.Bookshelf;
import cloud.nativ.quarkus.domain.Library;
import cloud.nativ.quarkus.domain.Loan;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@RegisterForReflection
@RequestScoped
public class LoanResource {

    @Inject
    Bookshelf bookshelf;
    @Inject
    Library library;

    private String isbn;

    public LoanResource() {
        // this is required! otherwise you get an error
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(absolute = true, unit = MetricUnits.MILLISECONDS)
    public Response loans() {
        Book book = bookshelf.findByISBN(isbn);
        return Response.ok(book.getLoans()).build();
    }

    @GET
    @Path("/{loanId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(absolute = true, unit = MetricUnits.MILLISECONDS)
    public Response loan(@PathParam("loanId") String loanId) {
        Loan loan = library.loanInfo(loanId);
        return Response.ok(loan).build();
    }

    @DELETE
    @Path("/{loanId}")
    @Counted(absolute = true)
    public Response returnBook(@PathParam("loanId") String loanId) {
        library.returnBook(isbn, loanId);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(absolute = true)
    public Response lendBook(Loan loan) {
        library.lendBook(isbn, loan);
        URI location = UriBuilder.fromResource(BookResource.class)
                .path("/{isbn}/loans/{loanId}")
                .resolveTemplate("isbn", isbn)
                .resolveTemplate("loanId", loan.getId())
                .build();
        return Response.created(location).build();
    }
}
