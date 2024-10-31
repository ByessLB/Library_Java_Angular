package learn.byesslb.library.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Customer Model")
public class CustomerDTO implements Comparable<CustomerDTO> {

    @ApiModelProperty(value = "Customer id")
    private Integer id;

    @ApiModelProperty(value = "Customer first name")
    private String firstName;

    @ApiModelProperty(value = "Customer last name")
    private String lastName;

    @ApiModelProperty(value = "Customer job")
    private String job;

    @ApiModelProperty(value = "Customer address")
    private String address;

    @ApiModelProperty(value = "Customer email")
    private String email;

    @ApiModelProperty(value = "Customer creation date in the system")
    private String creationDate;

    @Override
    public int compareTo(CustomerDTO o) {
        return this.lastName.compareToIgnoreCase(o.getLastName());
    }
}
