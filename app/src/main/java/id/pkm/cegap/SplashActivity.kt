package id.pkm.cegap

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.example.aplikasi_cegap.R
import com.example.aplikasi_cegap.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animatedLogo = AnimatedVectorDrawableCompat.create(this, R.drawable.avd_cegap)

        binding.animasiLogo.setImageDrawable(animatedLogo)

        animatedLogo?.start()

        Handler(Looper.getMainLooper()).postDelayed({
            goToMainActivity()
        }, 3000L)
    }

    private fun goToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}
