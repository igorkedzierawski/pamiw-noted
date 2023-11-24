package zet.kedzieri.noted.note.entity.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import zet.kedzieri.noted.note.config.NoteConfig;

@NoArgsConstructor
@Getter
@Setter
public class NoteForm {

    @NotNull(message = "{note.null_title}", groups = {CreatingNote.class, EditingNote.class})
    @NotBlank(message = "{note.empty_title}", groups = {CreatingNote.class, EditingNote.class})
    @Size(message = "{note.too_long_title}", max = NoteConfig.TITLE_MAX_LENGTH, groups = {CreatingNote.class, EditingNote.class})
    private String title;

    @NotNull(message = "{note.null_content}", groups = {CreatingNote.class, EditingNote.class})
    @Size(message = "{note.too_long_content}", max = NoteConfig.CONTENT_MAX_LENGTH, groups = {CreatingNote.class, EditingNote.class})
    private String content;

    public NoteForm(Long id, String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public interface CreatingNote {}

    public interface EditingNote {}

}