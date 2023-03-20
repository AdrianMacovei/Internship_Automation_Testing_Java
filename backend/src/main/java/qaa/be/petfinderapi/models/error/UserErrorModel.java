package qaa.be.petfinderapi.models.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserErrorModel extends ErrorModel {
    private String type;
    private String title;
    private int status;
    private String traceId;
    private String detail;
    private String instance;
    private ErrorModel errors;

}
