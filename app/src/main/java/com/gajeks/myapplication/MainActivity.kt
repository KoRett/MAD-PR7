package com.gajeks.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.gajeks.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val KEY_MESSAGE = "MESSAGE"

    private val mStartForResult: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback<ActivityResult>{
        if (it.resultCode == Activity.RESULT_OK){
            val intent = it.data
            val accessMessage = intent!!.getStringExtra(KEY_MESSAGE).toString()
            binding.editTextSome.setText(accessMessage)
        }
        else
            binding.editTextSome.setText("Ошибка доступа")
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.button.setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra(KEY_MESSAGE, binding.editTextHobby.text.toString())
            intent.putExtra(
                UserSerializableParams::class.simpleName,
                UserSerializableParams(
                    binding.editTextFirstName.text.toString(),
                    binding.editTextLastName.text.toString()
                )
            )
            intent.putExtra(
                UserParcelableParams::class.java.simpleName,
                UserParcelableParams(binding.editTextSome.text.toString())
            )
            mStartForResult.launch(intent)
        }

        setContentView(binding.root)
    }
}