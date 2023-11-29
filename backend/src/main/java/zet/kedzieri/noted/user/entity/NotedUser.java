package zet.kedzieri.noted.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "noteduser")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NotedUser {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String username;

    public NotedUser(String name, String surname, String username) {
        this.name = name;
        this.surname = surname;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotedUser notedUser = (NotedUser) o;

        return getId() == notedUser.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

}
