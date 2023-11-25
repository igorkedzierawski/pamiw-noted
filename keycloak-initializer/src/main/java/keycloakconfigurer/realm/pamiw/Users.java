package keycloakconfigurer.realm.pamiw;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;

public class Users extends ArrayList<UserRepresentation> {

    public Users() {
        addAll(Stream.of(
                new SimpleUser("admin", "admin", PAMiWRealm.Role.ADMIN),
                new SimpleUser("user", "user", PAMiWRealm.Role.USER),
                new SimpleUser("someuser", "password", PAMiWRealm.Role.USER)
        ).map(SimpleUser::intoUserRepresentation).toList());
    }

    private record SimpleUser(String username, String password, PAMiWRealm.Role... roles) {

        UserRepresentation intoUserRepresentation() {
            return new UserRepresentation() {{
                setUsername(username());
                setEnabled(true);
                setCredentials(singletonList(new CredentialRepresentation() {{
                    setType(CredentialRepresentation.PASSWORD);
                    setValue(password());
                }}));
                setRealmRoles(Arrays.stream(roles()).map(Enum::toString).toList());
            }};
        }

    }
}
