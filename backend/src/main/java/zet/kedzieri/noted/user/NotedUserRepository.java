package zet.kedzieri.noted.user;

import org.hibernate.query.IllegalQueryOperationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zet.kedzieri.noted.user.entity.NotedUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotedUserRepository extends JpaRepository<NotedUser, Long> {

    @Query("SELECT u FROM noteduser u WHERE u.username = ?1 AND u.foreignAuth IS NULL")
    Optional<NotedUser> findNativeAuthUser(String username);

    @Query("SELECT u FROM noteduser u WHERE u.username = ?1 AND u.foreignAuth = ?2")
    Optional<NotedUser> findForeignAuthUser(String username, String foreignAuth);

    List<NotedUser> findAllByUsername(String username);

}
