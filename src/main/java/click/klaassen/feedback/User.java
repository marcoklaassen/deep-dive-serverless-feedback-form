package click.klaassen.feedback;

import io.quarkus.security.identity.SecurityIdentity;
import lombok.Data;

@Data
public class User {
    private final String userName;
    User(SecurityIdentity securityIdentity) {
        this.userName = securityIdentity.getPrincipal().getName();
    }
}
