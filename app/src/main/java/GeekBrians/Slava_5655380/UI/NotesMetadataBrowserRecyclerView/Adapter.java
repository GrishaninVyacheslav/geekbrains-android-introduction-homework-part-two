package GeekBrians.Slava_5655380.UI.NotesMetadataBrowserRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.Note.NotesSource;
import GeekBrians.Slava_5655380.R;

public class Adapter
        extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private NotesSource dataSource;
    private OnItemClickListener itemClickListener;

    public Adapter(NotesSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_note_metadata, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(dataSource.getNoteData(i));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView creationData;
        private TextView modificationDate;
        private TextView notesTags;
        private TextView noteDescription;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            creationData = itemView.findViewById(R.id.note_creation_date);
            modificationDate = itemView.findViewById(R.id.note_modification_date);
            notesTags = itemView.findViewById(R.id.note_tags);
            noteDescription = itemView.findViewById(R.id.note_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }

        public void setData(Note note){
            title.setText(note.getMetadata().name);
            creationData.setText(new SimpleDateFormat("dd.MM.yyyy").format(note.getMetadata().creationDate));
            modificationDate.setText(new SimpleDateFormat("dd.MM.yyyy").format(note.getMetadata().modificationDate));
            notesTags.setText(Arrays.toString(note.getMetadata().tags));
            noteDescription.setText(note.getMetadata().description);
        }
    }
}