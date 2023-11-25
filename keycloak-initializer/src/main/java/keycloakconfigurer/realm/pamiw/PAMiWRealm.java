package keycloakconfigurer.realm.pamiw;

import keycloakconfigurer.ExternalConfig;
import org.keycloak.representations.idm.RealmRepresentation;

import java.util.List;

public class PAMiWRealm extends RealmRepresentation {

    public PAMiWRealm() {
        setRealm("PAMiW");
        setEnabled(true);
        setRegistrationAllowed(true);
        setClients(List.of(new NotedClient()));
        setUsers(new Users());
        setIdentityProviders(ExternalConfig.getIdentityProviders());
    }

    public enum Role {
        ADMIN, USER;
    }

}
