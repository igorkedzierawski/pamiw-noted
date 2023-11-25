package keycloakconfigurer;

import keycloakconfigurer.realm.pamiw.PAMiWRealm;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RealmRepresentation;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Keycloak admin = ExternalConfig.getAdmin();
        PAMiWRealm realm = new PAMiWRealm();
        Optional<RealmRepresentation> existingRealm = admin.realms().findAll().stream()
                .filter(r -> r.getRealm().equals(realm.getRealm()))
                .findFirst();
        if(existingRealm.isPresent()) {
            if(!ExternalConfig.shouldOverrideExistingConfiguration()) {
                System.out.println("Configured not to override existing configuration => Exiting");
                return;
            }
            admin.realms().realm(existingRealm.get().getRealm()).remove();
        }
        admin.realms().create(realm);
    }

}
