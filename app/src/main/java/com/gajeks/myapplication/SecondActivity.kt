package com.gajeks.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.gajeks.myapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val KEY_MESSAGE = "MESSAGE"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        val arguments = intent.extras

        if (arguments != null) {
            binding.text.text = arguments.getString(KEY_MESSAGE) + "\n"
            val userSerializableParams =
                arguments.getSerializable(UserSerializableParams::class.simpleName) as UserSerializableParams
            binding.text.text =
                binding.text.text.toString() + userSerializableParams.firstName + "\n" + userSerializableParams.lastName + "\n"
            val userParcelableParams: UserParcelableParams? =
                arguments.getParcelable(UserParcelableParams::class.java.simpleName)!!
            binding.text.text =
                binding.text.text.toString() + userParcelableParams!!.someText
        }

        binding.button1.setOnClickListener {
            val data = Intent()
            data.putExtra(KEY_MESSAGE, "OK")
            setResult(RESULT_OK, data)
            finish()
        }

        binding.button2.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}