package com.example.dac.network

import com.example.dac.model.*
import retrofit2.http.*

interface ApiService {
    // 1. Thêm user
    @FormUrlEncoded
    @POST("api.php")
    suspend fun addUser(
        @Field("action") action: String = "add_user",
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String
    ): ApiResponse

    // 1. Lấy user bằng username
    @GET("api.php")
    suspend fun getUser(
        @Query("action") action: String = "get_user",
        @Query("username") username: String
    ): UserResponse

    // 2. Lấy 10 từ vựng chưa học
    @GET("api.php")
    suspend fun getVocabularies(
        @Query("action") action: String = "get_vocabs",
        @Query("id_user") idUser: Int
    ): VocabularyListResponse

    // 3. Thêm learned_word
    @FormUrlEncoded
    @POST("api.php")
    suspend fun addLearnedWord(
        @Field("action") action: String = "add_learned_words",
        @Field("id_user") idUser: Int,
        @Field("id_vocabulary") idVocabulary: Int,
        @Field("level") level: Int,
        @Field("date") date: String
    ): ApiResponse

    // 3. Update level learned_word lên 1
    @FormUrlEncoded
    @POST("api.php")
    suspend fun updateLearnedLevel(
        @Field("action") action: String = "update_learned_level",
        @Field("id_user") idUser: Int,
        @Field("id_vocabulary") idVocabulary: Int
    ): ApiResponse

    // 3. Lấy tối đa 10 learned_word level < 5
    @GET("api.php")
    suspend fun getLearnedWordsUnder5(
        @Query("action") action: String = "get_learned_under_5",
        @Query("id_user") idUser: Int
    ): LearnedWordListResponse

    // 3. Lấy số lượng từ level 1-5
    @GET("api.php")
    suspend fun countLevel(
        @Query("action") action: String = "count_level",
        @Query("id_user") idUser: Int
    ): CountLevelResponse

    // 4. Lấy all test bằng id_user
    @GET("api.php")
    suspend fun getAllTests(
        @Query("action") action: String = "get_tests",
        @Query("id_user") idUser: Int
    ): TestListResponse

    // 4. Thêm test
    @FormUrlEncoded
    @POST("api.php")
    suspend fun addTest(
        @Field("action") action: String = "add_test",
        @Field("id_user") idUser: Int,
        @Field("score") score: Int,
        @Field("date") date: String
    ): ApiResponse
    @GET("api.php")
    suspend fun getVocabularyById(
        @Query("action") action: String = "get_vocabulary_by_id",
        @Query("id_vocabulary") idVocabulary: Int
    ): VocabularyByIdResponse

}

// Các response mở rộng
data class VocabularyListResponse(val success: Boolean, val data: List<Vocabulary>?)
data class LearnedWordListResponse(val success: Boolean, val data: List<LearnedWord>?)
data class TestListResponse(val success: Boolean, val data: List<Test>?)
data class CountLevelResponse(val success: Boolean, val data: Map<String, Int>?)
data class VocabularyByIdResponse(val success: Boolean, val data: Vocabulary?)
