package com.twu.biblioteca;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by alexa on 2/02/2019.
 */
public class BookTest {

    Book book;

    @Rule
    public final ExpectedException failure = ExpectedException.none();

    @Test
    public void shouldPrintAllDetailsOfABook() {
        book = new Book("Title 1", "Author 1", "2001");
        assertThat(book.getFullDetail(),
                is(" Title 1              | Author 1        | 2001 |"));
    }

    @Test
    public void shouldPrintErrorMessageWhenBookHasNoDetailsNull() {
        book = new Book(null, null, null);
        assertThat(book.getFullDetail(), is("Error!!! Book has no details"));
    }

    @Test
    public void shouldPrintErrorMessageWhenBookHasNoDetailsEmpty() {
        book = new Book("", "", "");
        assertThat(book.getFullDetail(), is("Error!!! Book has no details"));
    }

    @Test
    @Ignore
    public void shouldPrintUnknownWhenDetailIsNotComplete() {
        book = new Book("Title 2", null, "2000");
        assertThat(book.getFullDetail(),
                is(" Title 2              | Unknown         | 2000 |"));
    }

    @Test
    public void shouldThrowBookCannotBeCheckedOutWhenBorrowingACheckedOutBook() throws Exception {
        failure.expect(ItemCannotBeCheckedOutException.class);

        book = new Book("Title 3", "Author 3", "2019", false);

        book.borrowItem(null);
    }

    @Test
    public void shouldThrowBookNotValidForReturnExceptionWhenReturningAnAvailableBook() throws Exception {
        failure.expect(ItemNotValidForReturnException.class);

        book = new Book("Title 4", null, null, true);

        book.returnItem();
    }

    @Test
    public void shouldAddLibNumberDetailsWhenBorrowed() {
        book = new Book("Title 1", "Author 1", "2001", false);
        Customer customer = new Customer("123-4567", "name name", "phone", "email", "pass");
        book.setBorrowedBy(customer);
        assertThat(book.getFullDetail(),
                is(" 123-4567 | Title 1              | Author 1        | 2001 |"));
    }

    @Test
    @Ignore
    public void successCheckForYear() {

    }

    @Test
    @Ignore
    public void failedCheckForYear() {

    }


}
