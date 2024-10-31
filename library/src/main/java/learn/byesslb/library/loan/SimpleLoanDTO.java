package learn.byesslb.library.loan;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Simple Loan Model")
public class SimpleLoanDTO {

    @ApiModelProperty(value = "Book id concerned by the loan")
    private Integer bookId;

    @ApiModelProperty(value = "Customer id concerned by the loan")
    private Integer customerId;

    @ApiModelProperty(value = "Loan beginig date")
    private LocalDate beginDate;

    @ApiModelProperty(value = "Loan ending date")
    private LocalDate endDate;
}
