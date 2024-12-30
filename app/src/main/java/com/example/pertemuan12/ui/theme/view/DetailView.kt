package com.example.pertemuan12.ui.theme.view
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.ui.theme.costumwidget.CostumeTopAppBar
import com.example.pertemuan12.ui.theme.navigation.DestinasiNavigasi
import com.example.pertemuan12.ui.theme.viewmodel.DetailUiState
import com.example.pertemuan12.ui.theme.viewmodel.DetailViewModel
import com.example.pertemuan12.ui.theme.viewmodel.PenyediaViewModel


object DestinasiDetail: DestinasiNavigasi {
    override val route = "item_detail"
    override val titleRes = "Detail Mhs"
    const val NIM = "nim"
    val routeWithArg = "$route/{$NIM}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    navigateBack: () -> Unit,
    onEditClick: (String) -> Unit = {}
){
    Scaffold (
        modifier = modifier,
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {onEditClick(viewModel.mhsUiState.toString())},
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(17.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit")
            }
        },
    ){
            innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            when (val state = viewModel.mhsUiState){
                is DetailUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Start))
                }
                is DetailUiState.Error -> {
                    Text("Error saat memuat data", modifier = Modifier.align(Alignment.Start))
                }
                is DetailUiState.Success -> {
                    ItemDetailMahasiswa(mahaasiswa = state.mahasiswa)
                }
            }
        }
    }
}

@Composable
fun ItemDetailMahasiswa(
    modifier: Modifier = Modifier,
    mahaasiswa: Mahasiswa
){
    Card(
        modifier = modifier.fillMaxWidth()
    ){
        Column(
            modifier = modifier.padding(16.dp)
        ){
            ComponentDetailMahasiswa(judul = "NIM", isinya = mahaasiswa.nim)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMahasiswa(judul = "Nama", isinya = mahaasiswa.nama)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMahasiswa(judul = "Alamat", isinya = mahaasiswa.alamat)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMahasiswa(judul = "Jenis Kelamin", isinya = mahaasiswa.jenisKelamin)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMahasiswa(judul = "Kelas", isinya = mahaasiswa.kelas)
            Spacer(modifier = Modifier.padding(5.dp))
            ComponentDetailMahasiswa(judul = "Angkatan", isinya = mahaasiswa.angkatan)
        }
    }
}


@Composable
fun ComponentDetailMahasiswa(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
){
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = "$judul :",
            modifier = modifier
        )
        Text(
            text = isinya,
        )
    }
}
