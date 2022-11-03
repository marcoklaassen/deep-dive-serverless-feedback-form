package click.klaassen.feedback;

import io.quarkus.oidc.UserInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class User {
    private final String userName;
    User(UserInfo securityIdentity) {
        this.userName = securityIdentity.getUserInfoString();
    }
}
