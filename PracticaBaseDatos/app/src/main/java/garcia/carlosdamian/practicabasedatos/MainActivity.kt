package garcia.carlosdamian.practicabasedatos

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import garcia.carlosdamian.practicabasedatos.data.Alumno

class MainActivity : AppCompatActivity() {
    private lateinit var db: AlumnoSQLHelper
    private lateinit var studentsList: RecyclerView
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AlumnoSQLHelper(this)
        studentsList = findViewById(R.id.studentsList)
        studentsList.layoutManager = LinearLayoutManager(this)

        val etNombre = findViewById<EditText>(R.id.etName)
        val etApellidoPaterno = findViewById<EditText>(R.id.etFirstLastName)
        val etApellidoMaterno = findViewById<EditText>(R.id.etSecondLastName)
        val etProgramaEducativo = findViewById<EditText>(R.id.educativeProgram)
        val guardar = findViewById<Button>(R.id.save)

        guardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val apPaterno = etApellidoPaterno.text.toString()
            val apMaterno = etApellidoMaterno.text.toString()
            val programaEducativo = etProgramaEducativo.text.toString()

            if (nombre.isNotEmpty() && apPaterno.isNotEmpty() && apMaterno.isNotEmpty() && programaEducativo.isNotEmpty()) {
                val alumno = Alumno(nombre, apPaterno, apMaterno, programaEducativo)
                db.insertarAlumno(alumno)
                Toast.makeText(this, "Alumno Guardado", Toast.LENGTH_SHORT).show()
                etNombre.text.clear()
                etApellidoPaterno.text.clear()
                etApellidoMaterno.text.clear()
                etProgramaEducativo.text.clear()
                loadStudents()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        loadStudents()
    }

    private fun loadStudents() {
        val students = db.obtenerAlumnos()
        studentAdapter = StudentAdapter(students)
        studentsList.adapter = studentAdapter
    }
}