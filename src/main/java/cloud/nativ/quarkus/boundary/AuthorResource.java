package cloud.nativ.quarkus.boundary;

import cloud.nativ.quarkus.domain.Author;
import cloud.nativ.quarkus.domain.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private final Book book;

    AuthorResource(Book book) {
        this.book = book;
    }

    @GET
    public Author get() {
        return book.getAuthor();
    }
}
