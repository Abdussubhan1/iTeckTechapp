package com.example.itecknologi_testing

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginScreen : AppCompatActivity() {

    private lateinit var edText: EditText
    private lateinit var doneButton: LinearLayout
    private lateinit var appVersionText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_screen)

        edText = findViewById(R.id.editTextPhone222)
        doneButton = findViewById(R.id.button)
        appVersionText = findViewById(R.id.txt_version)

        // Display app version
        appVersionText.text = getVersionName(this)

        // Apply CNIC formatting
        edText.addTextChangedListener(CNICFormatter())

        doneButton.setOnClickListener {
            val CNIC = edText.text.toString().trim()
            if (isCNICValid(this, CNIC))
            {
                startActivity(Intent(this@LoginScreen, MainActivity::class.java))
                edText.text.clear()
            }
        }
    }
}

// CNIC Formatting TextWatcher
class CNICFormatter : TextWatcher {
    private var isFormatting = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (isFormatting || s == null) return

        isFormatting = true
        val formatted = formatCNIC(s.toString().replace("-", ""))
        s.replace(0, s.length, formatted, 0, formatted.length)
        isFormatting = false
    }

    private fun formatCNIC(cnic: String): String {
        val clean = cnic.filter { it.isDigit() }
        val sb = StringBuilder()

        for (i in clean.indices) {
            if (i == 5 || i == 12) sb.append('-')
            sb.append(clean[i])
        }
        return sb.toString()
    }
}

// CNIC Validation
fun isCNICValid(context: Context, CNIC: String): Boolean {
    val regex = Regex("\\d{5}-\\d{7}-\\d") // Ensures correct format

    return when {
        CNIC.isEmpty() -> {
            Toast.makeText(context, "CNIC field is Blank", Toast.LENGTH_SHORT).show()
            false
        }
        !regex.matches(CNIC) -> {
            Toast.makeText(context, "Invalid CNIC Entered.", Toast.LENGTH_SHORT).show()
            false
        }
        else -> {
            Toast.makeText(context, "Success!!.", Toast.LENGTH_SHORT).show()
            true
        }
    }
}

// Function to get app version
fun getVersionName(context: Context): String {
    return try {
        val packageInfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        packageInfo.versionName ?: "Unknown"
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
        "Unknown"
    }
}
