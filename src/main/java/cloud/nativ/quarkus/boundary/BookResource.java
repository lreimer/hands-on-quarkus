package cloud.nativ.quarkus.boundary;

import cloud.nativ.quarkus.domain.Book;
import cloud.nativ.quarkus.domain.Bookshelf;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Objects;


@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class BookResource {

    @Inject
    Bookshelf bookshelf;
    @Context
    ResourceContext context;

    @GET
    @Timed(absolute = true, unit = MetricUnits.MILLISECONDS)
    public Response books() {
        return Response.ok(bookshelf.findAll()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(absolute = true)
    public Response create(@Valid Book book) {
        bookshelf.create(book);
        URI location = UriBuilder.fromResource(BookResource.class)
                .path("/{isbn}")
                .resolveTemplate("isbn", book.getIsbn())
                .build();
        return Response.created(location).build();
    }

    @GET
    @Path("/{isbn}")
    @Timed(absolute = true, unit = MetricUnits.MILLISECONDS)
    public Response get(@PathParam("isbn") String isbn) {
        Book book = bookshelf.findByISBN(isbn);
        return Response.ok(book).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{isbn}")
    @Counted(absolute = true)
    public Response update(@PathParam("isbn") String isbn, @Valid Book book) {
        if (!Objects.equals(isbn, book.getIsbn())) {
            // return Response.status(Response.Status.BAD_REQUEST).build();
            // throw new WebApplicationException("ISBN must match path parameter.", Response.Status.BAD_REQUEST);
            throw new BadRequestException("ISBN must match path parameter.");
        }
        bookshelf.update(isbn, book);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{isbn}")
    @Counted(absolute = true)
    public Response delete(@PathParam("isbn") String isbn) {
        bookshelf.delete(isbn);
        return Response.ok().build();
    }

    @Path("/{isbn}/author")
    public AuthorResource author(@PathParam("isbn") String isbn) {
        Book book = bookshelf.findByISBN(isbn);
        return new AuthorResource(book);
    }

    @Path("/{isbn}/loans")
    public LoanResource loans(@PathParam("isbn") String isbn) {
        LoanResource loanResource = context.getResource(LoanResource.class);
        loanResource.setIsbn(isbn);

        return loanResource;
    }
}
