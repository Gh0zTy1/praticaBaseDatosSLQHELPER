package garcia.carlosdamian.practicabasedatos;


import android.os.Bundle
import android.widget.Button


import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import garcia.carlosdamian.practicabasedatos.R
import garcia.carlosdamian.practicabasedatos.data.Alumno
import garcia.carlosdamian.practicabasedatos.data.AlumnoSQLHelper

class MainActivity : AppCompatActivity() {
    private lateinit var db: AlumnoSQLHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db= AlumnoSQLHelper(this)

        val etNombre = findViewById<EditText>(R.id.etName)
        val etApellidoPaterno = findViewById<EditText>(R.id.etFirstLastName)
        val etApellidoMaterno = findViewById<EditText>(R.id.etSecondLastName)
        val etProgramaEducativo = findViewById<EditText>(R.id.educativeProgram)
        val guardar = findViewById<Button>(R.id.save)

        guardar.setOnClickListener {

            var nombre = etNombre.text.toString()
            var apPaterno = etApellidoPaterno.text.toString()
            var apMaterno = etApellidoMaterno.text.toString()
            var programaEducativo = etProgramaEducativo.text.toString()

            val alumno = Alumno(nombre, apPaterno, apMaterno, programaEducativo)
            db.insertarAlumno(alumno)

            Toast.makeText(this, "Alumnos Guardados", Toast.LENGTH_SHORT).show()

            etNombre.text.clear()
            etApellidoPaterno.text.clear()
            etApellidoMaterno.text.clear()
            etProgramaEducativo.text.clear()
            


        }
    }

}