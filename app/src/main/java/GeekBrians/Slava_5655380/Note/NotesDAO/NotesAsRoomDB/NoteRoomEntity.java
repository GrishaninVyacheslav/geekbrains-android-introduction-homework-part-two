package GeekBrians.Slava_5655380.Note.NotesDAO.NotesAsRoomDB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import GeekBrians.Slava_5655380.Note.Note;

// TODO: хранить tags и content в своих отдельных связанных таблицах

@Entity
public class NoteRoomEntity {
    @PrimaryKey
    public long uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "creation_date")
    public Date creationDate;

    @ColumnInfo(name = "modification_date")
    public Date modificationDate;

    @ColumnInfo(name = "tags")
    public String[] tags;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "content")
    public String content;

    public NoteRoomEntity(long uid, String name, Date creationDate, Date modificationDate, String[] tags, String description, String content) {
        this.uid = uid;
        this.name = name;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.tags = tags;
        this.description = description;
        this.content = content;
    }

    public NoteRoomEntity(Note note, NotesDao notesDao) {
        this.uid = note.UNIQUE_ID;
        Note.MetaData metaData = note.getMetadata();
        this.name = metaData.name;
        this.creationDate = metaData.creationDate;
        this.modificationDate = metaData.modificationDate;
        this.tags = metaData.tags;
        this.description = metaData.description;
        this.content = note.getContent();
    }

    public Note convertToNote() {
        return new Note(uid, new Note.MetaData(name, creationDate, modificationDate, tags, description), content);
    }
}
