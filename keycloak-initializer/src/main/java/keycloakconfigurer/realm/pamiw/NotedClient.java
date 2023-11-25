package keycloakconfigurer.realm.pamiw;

import org.keycloak.representations.idm.ClientRepresentation;

import java.util.List;

public class NotedClient extends ClientRepresentation {

    public NotedClient() {
        setClientId("noted");
        setEnabled(true);
        setRedirectUris(List.of("http://localhost/*", "http://localhost:80/*"));
        setDirectAccessGrantsEnabled(true);
        setPublicClient(true);
    }

}
