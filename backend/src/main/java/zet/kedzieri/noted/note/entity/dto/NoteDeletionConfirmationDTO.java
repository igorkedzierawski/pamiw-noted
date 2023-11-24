package zet.kedzieri.noted.note.entity.dto;

import zet.kedzieri.noted.note.entity.Note;

public record NoteDeletionConfirmationDTO(long id) {

    public static NoteDeletionConfirmationDTO from(Note note) {
        return new NoteDeletionConfirmationDTO(note.getId());
    }

}
