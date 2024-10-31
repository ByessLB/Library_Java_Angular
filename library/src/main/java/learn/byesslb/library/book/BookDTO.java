package learn.byesslb.library.book;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import learn.byesslb.library.category.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "Book Model")
public class BookDTO implements Comparable<BookDTO> {

    @ApiModelProperty(value = "Book id")
    private Integer id;

    @ApiModelProperty(value = "Book title")
    private String title;

    @ApiModelProperty(value = "Book isbn")
    private String isbn;

    @ApiModelProperty(value = "Book release date by the editor")
    private String releaseDate;

    @ApiModelProperty(value = "Book register date in the library")
    private String registerDate;

    @ApiModelProperty(value = "Book total examplaries")
    private Integer TotalExamplaries;

    @ApiModelProperty(value = "Book author")
    private String author;

    @ApiModelProperty(value = "Book category")
    private CategoryDTO category;

    @Override
    public int compareTo(BookDTO o) {
        return title.compareToIgnoreCase(o.getTitle());
    }
}
