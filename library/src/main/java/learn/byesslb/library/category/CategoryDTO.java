package learn.byesslb.library.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Category Model")
public class CategoryDTO implements Comparable<CategoryDTO> {

    @ApiModelProperty(value = "Category code")
    private String code;

    @ApiModelProperty(value = "Category label")
    private String label;

    public CategoryDTO() {}

    public CategoryDTO(String code, String label) {
        super();
        this.code = code;
        this.label = label;
    }

    @Override
    public int compareTo(CategoryDTO o) {
        return label.compareToIgnoreCase(o.label);
    }
}
