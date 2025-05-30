package com.example.dac.network

import com.example.dac.model.*
import retrofit2.http.*

interface ApiService {
    // User
    @GET("user.php")
    suspend fun findUser(@Query("user_name") userName: String): User

    @POST("user_app.php")
    suspend fun addUser(@Body user: Map<String, String>): ApiResponse

    // Vocabulary
    @GET("vocabulary_all.php")
    suspend fun getAllVocabulary(): List<Vocabulary>

    // L1-L5
    @GET("vocabulary_l1_user.php")
    suspend fun getVocabularyL1ByUser(@Query("id_user") idUser: Int): List<VocabularyL1>
    @POST("vocabulary_l1_add.php")
    suspend fun addVocabularyL1(@Body data: Map<String, Int>): ApiResponse
    @POST("vocabulary_l1_delete.php")
    suspend fun deleteVocabularyL1(@Body data: Map<String, Int>): ApiResponse

    // L2, L3, L4, L5: Tạo các hàm tương tự (chỉ đổi L1 thành L2/L3/L4/L5)
    @GET("vocabulary_l1_user.php")
    suspend fun getVocabularyL2ByUser(@Query("id_user") idUser: Int): List<VocabularyL2>
    @POST("vocabulary_l1_add.php")
    suspend fun addVocabularyL2(@Body data: Map<String, Int>): ApiResponse
    @POST("vocabulary_l1_delete.php")
    suspend fun deleteVocabularyL2(@Body data: Map<String, Int>): ApiResponse

    @GET("vocabulary_l1_user.php")
    suspend fun getVocabularyL3ByUser(@Query("id_user") idUser: Int): List<VocabularyL3>
    @POST("vocabulary_l1_add.php")
    suspend fun addVocabularyL3(@Body data: Map<String, Int>): ApiResponse
    @POST("vocabulary_l1_delete.php")
    suspend fun deleteVocabularyL3(@Body data: Map<String, Int>): ApiResponse

    @GET("vocabulary_l1_user.php")
    suspend fun getVocabularyL4ByUser(@Query("id_user") idUser: Int): List<VocabularyL4>
    @POST("vocabulary_l1_add.php")
    suspend fun addVocabularyL4(@Body data: Map<String, Int>): ApiResponse
    @POST("vocabulary_l1_delete.php")
    suspend fun deleteVocabularyL4(@Body data: Map<String, Int>): ApiResponse

    @GET("vocabulary_l1_user.php")
    suspend fun getVocabularyL5ByUser(@Query("id_user") idUser: Int): List<VocabularyL5>
    @POST("vocabulary_l1_add.php")
    suspend fun addVocabularyL5(@Body data: Map<String, Int>): ApiResponse
    @POST("vocabulary_l1_delete.php")
    suspend fun deleteVocabularyL5(@Body data: Map<String, Int>): ApiResponse
    // Test
    @GET("test_user.php")
    suspend fun getTestsOfUser(@Query("id_user") idUser: Int): List<Test>
    @POST("test_add.php")
    suspend fun addTest(@Body data: Map<String, Any>): ApiResponse

    @GET("get_new_words.php")
    suspend fun getNewWords(@Query("user_id") userId: Int): List<String>

    @GET("get_review_words.php")
    suspend fun getReviewWords(@Query("user_id") userId: Int): List<String>
}
