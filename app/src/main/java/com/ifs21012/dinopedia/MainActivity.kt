package com.ifs21012.dinopedia
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21012.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFamilis = ArrayList<Famili>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFamili.setHasFixedSize(false)
        dataFamilis.addAll(getListFamilis())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListFamilis(): ArrayList<Famili> {
        val dataName =
            resources.getStringArray(R.array.famili_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.famili_icon)
        val dataDescription =
            resources.getStringArray(R.array.famili_description)
        val dataperiodehidup =
            resources.getStringArray(R.array.famili_periodehidup)
        val dataKarakteristikFisik =
            resources.getStringArray(R.array.famili_KarakteristikFisik)
        val dataHabitatdanLingkungan =
            resources.getStringArray(R.array.famili_HabitatdanLingkungan)
        val dataPerilaku =
            resources.getStringArray(R.array.famili_Perilaku)
        val dataKlasifikasi =
            resources.getStringArray(R.array.famili_Klasifikasi)

        val listFamili = ArrayList<Famili>()
        for (i in dataName.indices) {
            val famili = Famili(
                dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i], dataperiodehidup[i], dataKarakteristikFisik[i],
                dataHabitatdanLingkungan[i],dataPerilaku[i], dataKlasifikasi[i])
            listFamili.add(famili)
        }
        return listFamili
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE
        ) {
            binding.rvFamili.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFamili.layoutManager =
                LinearLayoutManager(this)
        }
        val listFamiliAdapter = ListFamiliAdapter(dataFamilis)
        binding.rvFamili.adapter = listFamiliAdapter
        listFamiliAdapter.setOnItemClickCallback(object :
            ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Famili) {
                showSelectedFamili(data)
            }
        })
    }

    private fun showSelectedFamili(famili: Famili) {
        val intentWithData = Intent(this@MainActivity, DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILI, famili)
        startActivity(intentWithData)
    }

}