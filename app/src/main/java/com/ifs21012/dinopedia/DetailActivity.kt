package com.ifs21012.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21012.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var famili: Dinos? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        famili = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILI,
                Dinos::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (famili != null) {
            supportActionBar?.title = "Buah ${famili!!.names}"
            loadData(famili!!)
        } else {
            finish()
        }

        binding.buttonDetailDIno.setOnClickListener {
            val intentWithData = Intent(this@DetailActivity,DinoMainActivity::class.java)
            intentWithData.putExtra(DinoMainActivity.EXTRA_FAMILI,famili!!)
            startActivity(intentWithData)
        }
    }
    private fun loadData(famili: Dinos) {
        binding.ivDetailIcon.setImageResource(famili.icons)
        binding.tvDetailName.text = famili.names
        binding.tvDetailDescription.text = famili.descriptions
        binding.tvDetailCharacteristic.text = famili.KarakteristikFisik
        binding.tvDetailHabitat.text = famili.HabitatdanLingkungan
        binding.tvDetailPerilaku.text = famili.Perilaku
        binding.tvDetailperiodehidup.text = famili.periodehidup
        binding.tvDetailKlasifikasi.text = famili.Klasifikasi
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_FAMILI = "extra_famil"
    }
}