package zet.kedzieri.noted.note;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zet.kedzieri.noted.note.entity.Note;
import zet.kedzieri.noted.note.exception.IllegalNoteAccessException;
import zet.kedzieri.noted.note.exception.NoteNotFoundByIdException;
import zet.kedzieri.noted.user.entity.NotedUser;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepo;

    public Note createNote(NotedUser user, String title, String content) {
        Note note = new Note(user,
                title, content,
                LocalDateTime.now(), null
        );
        return noteRepo.save(note);
    }

    public Note updateNote(NotedUser user, long noteId, String title, String content) {
        Note note = noteRepo.findById(noteId).orElseThrow(
                () -> new NoteNotFoundByIdException(noteId)
        );
        if(!user.equals(note.getAuthor())) {
            throw new IllegalNoteAccessException(noteId);
        }
        note.setTitle(title);
        note.setContent(content);
        note.setEditedAt(LocalDateTime.now());
        return noteRepo.save(note);
    }

    public Note deleteNote(NotedUser user, long noteId) {
        Note note = noteRepo.findById(noteId).orElseThrow(
                () -> new NoteNotFoundByIdException(noteId)
        );
        if(!user.equals(note.getAuthor())) {
            throw new IllegalNoteAccessException(noteId);
        }
        noteRepo.delete(note);
        return note;
    }

    public Page<Note> paginateNotesByPageNumber(NotedUser user, Pageable pageable) {
        return noteRepo.findNotesPageByAuthor(user, pageable);
    }

    public Note getNoteById(NotedUser user, long noteId) {
        Note note = noteRepo.findById(noteId).orElseThrow(
                () -> new NoteNotFoundByIdException(noteId)
        );
        if(!Objects.equals(user, note.getAuthor())) {
            throw new IllegalNoteAccessException(noteId);
        }
        return note;
    }

}
