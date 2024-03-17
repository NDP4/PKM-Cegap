package id.pkm.cegap

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.aplikasi_cegap.R
import com.example.aplikasi_cegap.databinding.ActivityMainBinding // Import file binding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Deklarasi variabel binding
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Bersama kita ciptakan lingkungan yang aman.",
                "Mulailah dengan langkah kecil hari ini.",
                R.drawable.massageonboarding
            ),
            IntroSlide(
                "Lapor kejadian kriminalitas atau kesehatan langsung dari ponsel Anda!",
                "Dapatkan bantuan dengan cepat dan tanpa ribet.",
                R.drawable.kemananonboarding
            ),
            IntroSlide(
                "Kami peduli pada keselamatan Anda.",
                "Manfaatkan aplikasi ini untuk kesehatan Anda.",
                R.drawable.kolaborasionboarding
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate layout menggunakan View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Atur adapter untuk viewPager menggunakan binding
        binding.introSliderViewPager.adapter = introSliderAdapter

        setupIndicators()
        setCurrentIndicator(0)
        binding.introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        // Perbaikan: Menggunakan binding untuk akses elemen UI
        binding.buttonnext.setOnClickListener {
            if (binding.introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                binding.introSliderViewPager.currentItem += 1
            } else {
                Intent(applicationContext, StartedActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
        binding.textskip.setOnClickListener {
            Intent(applicationContext, StartedActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorsContainer.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}
