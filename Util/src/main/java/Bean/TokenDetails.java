package Bean;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class TokenDetails {
    private String header;
    private String payLoad;
    private String userId;
    private String expiry;
    private String iat;
    private String roles;
}
