package id.co.libralink.common.query;

import id.co.libralink.common.enums.OperationType;
import lombok.Getter;

@Getter
public class SearchCriteria {

    private final String fieldName;
    private final Object value;
    private final Object valueB; // for case between
    private final OperationType operationType;

    public SearchCriteria(String fieldName, Object value, OperationType operationType) {
        this.fieldName = fieldName;
        this.value = value;
        this.valueB = null;
        this.operationType = operationType;
    }

    public SearchCriteria(String fieldName, Object value, Object valueB, OperationType operationType){
        this.fieldName = fieldName;
        this.value = value;
        this.valueB = valueB;
        this.operationType = operationType;
    }

}
