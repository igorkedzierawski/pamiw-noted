package zet.kedzieri.noted.note.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import zet.kedzieri.noted.note.config.NoteConfig;
import zet.kedzieri.noted.user.entity.NotedUser;

import java.time.LocalDateTime;

@Entity(name = "note")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Note {

    @Id
    @GeneratedValue
    @NotNull
    private long id;

    @ManyToOne
    @JoinColumn
    @NotNull
    private NotedUser author;

    @Column(length = NoteConfig.TITLE_MAX_LENGTH)
    @NotNull
    private String title;

    @Column(length = NoteConfig.CONTENT_MAX_LENGTH)
    @NotNull
    private String content;

    @Column
    @NotNull
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime editedAt;

    public Note(NotedUser author, String title, String content, LocalDateTime createdAt, LocalDateTime editedAt) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.editedAt = editedAt;
    }

}
