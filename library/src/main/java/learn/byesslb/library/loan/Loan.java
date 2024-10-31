package learn.byesslb.library.loan;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "LOAN")
@AssociationOverrides({
    @AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "BOOK_ID")),
    @AssociationOverride(name = "pk.customer", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
})
public class Loan implements Serializable {
    private static final long serialVersionUID = 1442936034881497431L;

    @EmbeddedId
    private LoanId pk = new LoanId();

    @Column(name = "BEGIN_DATE", nullable = false)
    private LocalDate beginDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private LoanStatus status;
}
