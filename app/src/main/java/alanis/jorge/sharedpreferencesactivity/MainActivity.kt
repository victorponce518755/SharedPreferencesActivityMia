package alanis.jorge.sharedpreferencesactivity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var notificationsSwitch: Switch
    private lateinit var themeSpinner: Spinner
    private lateinit var frequencySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationsSwitch = findViewById(R.id.notificationsSwitch)
        themeSpinner = findViewById(R.id.themeSpinner)
        frequencySpinner = findViewById(R.id.frequencySpinner)

        // TODO: Cargar las preferencias guardadas al iniciar la actividad


        loadPreferences()

        // Guardar preferencias cuando el usuario haga cambios

        themeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                savePreferences()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Aquí puedes manejar el caso (raro) en que no se selecciona nada.
                // Sin embargo, en la mayoría de los casos, puedes dejarlo vacío.
            }
        }

        frequencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                savePreferences()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Similar al anterior, puedes manejar el caso en que no se selecciona nada o dejarlo vacío.
            }
        }
        notificationsSwitch.setOnCheckedChangeListener { _, _ -> savePreferences() }
        //themeSpinner.setOnItemSelectedListener { savePreferences() }
        //frequencySpinner.setOnItemSelectedListener { savePreferences() }


    }

    private fun loadPreferences() {
        // TODO: Obtener el objeto SharedPreferences
        val sharedPref = getSharedPreferences("news_app_preferences", Context.MODE_PRIVATE)

        // TODO: Cargar las preferencias y establecer los valores en las vistas correspondientes
        notificationsSwitch.isChecked = sharedPref.getBoolean("notifications_enabled", false)
        themeSpinner.setSelection(sharedPref.getInt("theme", 0))
        frequencySpinner.setSelection(sharedPref.getInt("frequency", 0))
    }

    private fun savePreferences() {
        // TODO: Obtener el objeto SharedPreferences y su Editor
        val sharedPref = getSharedPreferences("news_app_preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // TODO: Guardar las preferencias actuales usando el Editor
        editor.putBoolean("notifications_enabled", notificationsSwitch.isChecked)
        editor.putInt("theme", themeSpinner.selectedItemPosition)
        editor.putInt("frequency", frequencySpinner.selectedItemPosition)

        // TODO: Aplicar y guardar los cambios realizados
        editor.apply()
    }
}
