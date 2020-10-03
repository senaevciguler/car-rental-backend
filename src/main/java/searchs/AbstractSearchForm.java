package searchs;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractSearchForm implements Serializable {
    private Boolean deleted;
}
