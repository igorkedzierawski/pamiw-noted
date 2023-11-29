package zet.kedzieri.noted.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zet.kedzieri.noted.config.jwtprocessing.JwtAuthed;
import zet.kedzieri.noted.note.entity.dto.NoteDTO;
import zet.kedzieri.noted.note.entity.form.NoteForm;
import zet.kedzieri.noted.note.entity.dto.NoteDeletionConfirmationDTO;
import zet.kedzieri.noted.note.NoteService;
import zet.kedzieri.noted.user.entity.NotedUser;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static zet.kedzieri.noted.note.config.NoteConfig.NOTES_PAGE_MAX_SIZE;
import static zet.kedzieri.noted.note.entity.form.NoteForm.CreatingNote;
import static zet.kedzieri.noted.note.entity.form.NoteForm.EditingNote;

@RestController
@RequestMapping("/api/v1/note")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @Operation(description = "Stwórz nową notatkę. " +
            "Pole id jest ignorowane. " +
            "Może zostać wykonane przez zalogowanego użytkownika. ")
    @ResponseBody
    @PostMapping(value = "create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public NoteDTO create(
            @JwtAuthed NotedUser user,
            @RequestBody @Validated({CreatingNote.class}) NoteForm form
    ) {
        return NoteDTO.from(noteService.createNote(user, form.getTitle(), form.getContent()));
    }

    @Operation(description = "Uaktualnij treść istniejącej notatki. " +
            "Może zostać wykonane przez zalogowanego użytkownika. ")
    @ResponseBody
    @PatchMapping(value = "update", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public NoteDTO update(
            @JwtAuthed NotedUser user,
            @RequestParam("id") long noteId,
            @RequestBody @Validated({EditingNote.class}) NoteForm form
    ) {
        return NoteDTO.from(noteService.updateNote(user, noteId, form.getTitle(), form.getContent()));
    }

    @Operation(description = "Usuń istniejącą notatkę. " +
            "Usunięta będzie notatka o id podanym w parametrze `id`. " +
            "Może zostać wykonane przez zalogowanego użytkownika. ")
    @ResponseBody
    @DeleteMapping(value = "delete", produces = APPLICATION_JSON_VALUE)
    public NoteDeletionConfirmationDTO delete(
            @JwtAuthed NotedUser user,
            @RequestParam("id") long noteId
    ) {
        return NoteDeletionConfirmationDTO.from(noteService.deleteNote(user, noteId));
    }

    @Operation(description = "Pobierz wybraną stronę listy notatek. " +
            "Może zostać wykonane przez zalogowanego użytkownika. " +
            "Wspierana jest paginacja poprzez podanie na parametr `page` numeru strony. " +
            "Podanie niewłaściwej wartości spowoduje zwrócenie pierwszej strony. Parametr " +
            "`size` oznacza długość strony i maksymalnie może wynosić " + NOTES_PAGE_MAX_SIZE + ". " +
            "Wartości większe są zamieniane na wartość graniczną, a wartości mniejsze lub " +
            "równe 0 spowodują zwrócenie pustej strony")
    @ResponseBody
    @GetMapping(value = "page", produces = APPLICATION_JSON_VALUE)
    public Page<NoteDTO> notesPage(
            @JwtAuthed NotedUser user,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        if (size <= 0) {
            return Page.empty();
        }
        size = Math.min(size, NOTES_PAGE_MAX_SIZE);
        return noteService.paginateNotesByPageNumber(user, PageRequest.of(page, size)).map(NoteDTO::from);
    }

    @Operation(description = "Pobierz wybraną notatkę. " +
            "Może zostać wykonane przez zalogowanego użytkownika. ")
    @ResponseBody
    @GetMapping(value = "get", produces = APPLICATION_JSON_VALUE)
    public NoteDTO note(
            @JwtAuthed NotedUser user,
            @RequestParam("id") long noteId
    ) {
        return NoteDTO.from(noteService.getNoteById(user, noteId));
    }

}
