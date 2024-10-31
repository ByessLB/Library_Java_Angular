package learn.byesslb.library.loan;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import learn.byesslb.library.book.Book;
import learn.byesslb.library.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class LoanId implements Serializable {
    private static final long serialVersionUID = 3912193101593832821L;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Customer customer;
    @Column(name = "CREATION_DATE_TIME")
    private LocalDateTime creationDateTime;

    public LoanId() {
        super();
    }

    public LoanId(Book book, Customer customer) {
        super();
        this.book = book;
        this.customer = customer;
        this.creationDateTime = LocalDateTime.now();
    }
}