package zet.kedzieri.noted.note.entity.dto;

import zet.kedzieri.noted.note.entity.Note;
import zet.kedzieri.noted.user.entity.dto.NotedUserDTO;

import java.time.LocalDateTime;

public record NoteDTO(long id,
                      NotedUserDTO author,
                      String title, String content,
                      LocalDateTime createdAt, LocalDateTime editedAt
) {

    public static NoteDTO from(Note note) {
        return new NoteDTO(
                note.getId(),
                NotedUserDTO.from(note.getAuthor()),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getEditedAt()
        );
    }

}
