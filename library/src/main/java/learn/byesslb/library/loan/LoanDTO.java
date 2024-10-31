package learn.byesslb.library.loan;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import learn.byesslb.library.book.BookDTO;
import learn.byesslb.library.customer.CustomerDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Loan Model")
public class LoanDTO implements Comparable<LoanDTO> {

    @ApiModelProperty(value = "Book concerned by the loan")
    private BookDTO bookDTO = new BookDTO();

    @ApiModelProperty(value = "Customer concerned by th loan")
    private CustomerDTO customerDTO = new CustomerDTO();

    @ApiModelProperty(value = "Loan begining date")
    private LocalDate loanBeginDate;

    @ApiModelProperty(value = "Loan ending date")
    private LocalDate loanEndDate;

    @Override
    public int compareTo(LoanDTO o) {
        return o.getLoanBeginDate().compareTo(this.loanBeginDate);
    }
}
