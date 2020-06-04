package sg.edu.rpc346.id19016011.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnClear,btnDelete;
    EditText etTodo;
    ListView lvTodo;
    Spinner spn;
    ArrayAdapter<String> aaTodo;
    ArrayList<String> alTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTodo = findViewById(R.id.taskToDo);
        btnAdd = findViewById(R.id.buttonAddToDo);
        btnDelete = findViewById(R.id.buttonDeleteToDo);
        btnClear = findViewById(R.id.buttonClearToDo);
        lvTodo = findViewById(R.id.listViewToDo);
        spn = findViewById(R.id.addDeleteSpinner);

        alTodo = new ArrayList<String>();
        aaTodo = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, alTodo);
        lvTodo.setAdapter(aaTodo);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        etTodo.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etTodo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = etTodo.getText().toString();
                alTodo.add(newTodo);
                aaTodo.notifyDataSetChanged();
                etTodo.setText(null);
                Toast.makeText(MainActivity.this, "Item To Do added!", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(etTodo.getText().toString());
                if(pos > alTodo.size()){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }else if(alTodo.size() == 0){
                    Toast.makeText(MainActivity.this, "You don't have any task to be removed", Toast.LENGTH_SHORT).show();
                }else{
                    alTodo.remove(pos);
                    aaTodo.notifyDataSetChanged();
                    etTodo.setText(null);
                    Toast.makeText(MainActivity.this, "To Do Item deleted.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "To Do List cleared!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
