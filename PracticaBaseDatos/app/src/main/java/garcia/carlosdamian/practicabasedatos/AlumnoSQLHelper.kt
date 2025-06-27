package garcia.carlosdamian.practicabasedatos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import garcia.carlosdamian.practicabasedatos.data.Alumno

class AlumnoSQLHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{

        private const val DATABASE_NAME = "alumnos.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "alumnos"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NOMBRE = "nombre"
        private const val COLUMN_AP_PATERNO = "ap_paterno"
        private const val COLUMN_AP_MATERNO = "ap_materno"
        private const val COLUMN_PROGRAMA_EDUCATIVO = "programa_educativo"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableAlumnos = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NOMBRE TEXT, $COLUMN_AP_PATERNO TEXT, $COLUMN_AP_MATERNO TEXT, $COLUMN_PROGRAMA_EDUCATIVO TEXT)"
        db?.execSQL(createTableAlumnos)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableAlumnos = ("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL(dropTableAlumnos)
        onCreate(db)
    }

    fun insertarAlumno(alumno: Alumno) {

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, alumno.nombre)
            put(COLUMN_AP_PATERNO, alumno.apPaterno)
            put(COLUMN_AP_MATERNO, alumno.apMaterno)
            put(COLUMN_PROGRAMA_EDUCATIVO, alumno.programaEducativo)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun obtenerAlumnos(): List<Alumno> {
        val alumnos = mutableListOf<Alumno>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE))
            val apPaterno = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AP_PATERNO))
            val apMaterno = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AP_MATERNO))
            val programaEducativo = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_PROGRAMA_EDUCATIVO
            ))
            val alumno = Alumno(nombre, apPaterno, apMaterno, programaEducativo)
            alumnos.add(alumno)
        }
        cursor.close()
        db.close()
        return alumnos
    }
}