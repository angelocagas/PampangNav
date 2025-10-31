package com.pampang.nav.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.pampang.nav.constants.SharedPrefsConst
import com.pampang.nav.models.StoreModel
import com.pampang.nav.utilities.SharedPrefs
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val sharedPrefs: SharedPrefs
) {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _stores = MutableLiveData<List<StoreModel>>()
    val stores: LiveData<List<StoreModel>> get() = _stores

    suspend fun getStores() {
        _isLoading.postValue(true)

        try {
            val currentUser = firebaseAuth.currentUser
            if (currentUser == null) {
                _stores.postValue(emptyList())
                return
            }

            val snapshot = firestore.collection("stores")
                .whereEqualTo("owner_id", currentUser.uid)
                .get()
                .await()

            val storeList = snapshot.documents.mapNotNull { document ->
                document.toObject(StoreModel::class.java)?.apply {
                    id = document.id
                }
            }

            _stores.postValue(storeList)

        } catch (e: Exception) {
            Log.e("MainRepository", "Error getting stores: ${e.message}", e)
            _stores.postValue(emptyList())
        } finally {
            _isLoading.postValue(false)
        }
    }



}
