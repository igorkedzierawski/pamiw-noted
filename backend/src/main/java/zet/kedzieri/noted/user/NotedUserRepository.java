package zet.kedzieri.noted.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zet.kedzieri.noted.user.entity.NotedUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotedUserRepository extends JpaRepository<NotedUser, Long> {

    Optional<NotedUser> findUserByUsername(String username);

    List<NotedUser> findAllByUsername(String username);

}
