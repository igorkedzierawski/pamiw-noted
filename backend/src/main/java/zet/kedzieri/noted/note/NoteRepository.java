package zet.kedzieri.noted.note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zet.kedzieri.noted.note.entity.Note;
import zet.kedzieri.noted.user.entity.NotedUser;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM note n WHERE n.author = :author")
    Page<Note> findNotesPageByAuthor(@Param("author") NotedUser author, Pageable page);

}
