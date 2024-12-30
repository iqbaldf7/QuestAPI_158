package com.example.pertemuan12.repository
import com.example.pertemuan12.model.Mahasiswa
import com.example.pertemuan12.service.MahasiswaService
import java.io.IOException

interface MahasiswaRepository{

    suspend fun getAllMahasiswa(): List<Mahasiswa>

    suspend fun getMahasiswa(nim: String): Mahasiswa

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(nim: String)
}

class  NetworkKontakRepository(
    private val kontakApiService: MahasiswaService
) : MahasiswaRepository{


    override suspend fun getAllMahasiswa(): List<Mahasiswa> =
        kontakApiService.getAllMahasiswa()

    override suspend fun getMahasiswa(nim: String): Mahasiswa {
        return kontakApiService.getMahasiswabyNim(nim)
    }

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa){
        kontakApiService.insertMahasiswa(mahasiswa)
    }
    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa){
        kontakApiService.updateMahasiswa(nim, mahasiswa)
    }
    override suspend fun deleteMahasiswa(nim: String) {
        try{
            val response = kontakApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful){
                throw Exception("Failed to delete mahasiswa. HTTP Status code: " + "${response.code()}")
            }else{
                response.message()
                println(response.message())
            }
        }catch (e:Exception){
            throw e
        }
    }
}
