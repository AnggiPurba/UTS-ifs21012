package com.ifs21012.dinopedia
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21012.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFamilis = ArrayList<Dinos>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        binding.rvFamili.setHasFixedSize(false)
        dataFamilis.addAll(getListDinos())

        // Setup RecyclerView Adapter
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamili.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvFamili.layoutManager = LinearLayoutManager(this)
        }
        val listFamiliAdapter = ListFamiliAdapter(dataFamilis)
        binding.rvFamili.adapter = listFamiliAdapter

        // Handle item click in RecyclerView
        listFamiliAdapter.setOnItemClickCallback(object : ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinos) {
                showSelectedFamili(data)
            }
        })

        // Setup Button Click Listener
        val btnStart = findViewById<Button>(R.id.buttonalldinos)
        btnStart.setOnClickListener {
            val intent = Intent(this@MainActivity, DinoMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }


    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<Dinos> {
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
        val startIndex=
            resources.getStringArray(R.array.start_index_dino)
                val endIndex=
        resources.getStringArray(R.array.end_index_dino)
        val listFamili = ArrayList<Dinos>()
        for (i in dataName.indices) {
            val famili = Dinos(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataperiodehidup[i],
                dataKarakteristikFisik[i],
                dataHabitatdanLingkungan[i],
                dataPerilaku[i],
                dataKlasifikasi[i],
                startIndex[i].toInt(),
                endIndex[i].toInt()
            )
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
            override fun onItemClicked(data: Dinos) {
                showSelectedFamili(data)
            }
        })
    }

    private fun showSelectedFamili(famili: Dinos) {
        val intentWithData = Intent(this@MainActivity, DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILI, famili)
        startActivity(intentWithData)
    }

}