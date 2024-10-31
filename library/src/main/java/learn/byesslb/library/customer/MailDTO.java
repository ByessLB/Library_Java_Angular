package learn.byesslb.library.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Mail Model")
public class MailDTO {

    @ApiModelProperty(value = "Mail sender adderss")
    public final String MAIL_FROM = "noreply.library.test@gmail.com";

    @ApiModelProperty(value = "Customer receiver id")
    private Integer customerId;

    @ApiModelProperty(value = "Email subject")
    private String emailSubject;

    @ApiModelProperty(value = "Email content")
    private String emailContent;
}