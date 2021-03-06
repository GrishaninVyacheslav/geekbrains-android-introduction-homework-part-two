package GeekBrians.Slava_5655380.UI.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import GeekBrians.Slava_5655380.Note.Note;
import GeekBrians.Slava_5655380.R;
import GeekBrians.Slava_5655380.UI.Fragments.NoteFragment;

import static GeekBrians.Slava_5655380.UI.Fragments.NoteFragment.ARG_SELECTED_NOTE;

public class MainActivity extends AppCompatActivity {
    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.note_browser_toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (navigateFragment(id)){
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            }
        });
    }

    private boolean navigateFragment(int id) {
        switch (id) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_about:
                Toast.makeText(MainActivity.this, "action_about", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    private void createNewNote() {
        Intent intent = new Intent();
        intent.setClass(this, NoteEditorActivity.class);
        intent.putExtra(ARG_SELECTED_NOTE, new Note());
        startActivityForResult(intent, NoteFragment.REQUEST_CODE_EDITOR_ACTIVITY);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sort:
                Toast.makeText(MainActivity.this, "action_sort", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_create:
                createNewNote();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notes_browser_toolbar, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchText = (SearchView) search.getActionView();
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case NoteFragment.REQUEST_CODE_EDITOR_ACTIVITY:
                recreate();
                break;
        }
    }
}
