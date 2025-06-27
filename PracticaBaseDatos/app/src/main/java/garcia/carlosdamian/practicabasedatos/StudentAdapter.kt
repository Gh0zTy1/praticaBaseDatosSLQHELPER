package garcia.carlosdamian.practicabasedatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import garcia.carlosdamian.practicabasedatos.data.Alumno

class StudentAdapter(private val students: List<Alumno>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView = itemView.findViewById(R.id.etStudentName)
        val studentId: TextView = itemView.findViewById(R.id.etStudentId)
        val educativeProgram: TextView = itemView.findViewById(R.id.etEducativeProgram)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_card, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currentStudent = students[position]
        holder.studentName.text = "${currentStudent.nombre} ${currentStudent.apPaterno} ${currentStudent.apMaterno}"
        holder.studentId.text = "" // No tienes un campo de ID de estudiante en tu modelo de datos
        holder.educativeProgram.text = currentStudent.programaEducativo
    }

    override fun getItemCount() = students.size
}