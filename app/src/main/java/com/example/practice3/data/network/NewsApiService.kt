package com.example.practice3.data.network

import com.example.practice3.data.News
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NewsApiService {

    @GET("news")
    suspend fun getNews(): List<News>

    @PUT("news/{id}")
    suspend fun updateNews(@Path("id") id: String, @Body news: News): News

    companion object {
        fun createNewsApiService(): NewsApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://example.com/api/") // replace with an actual API url
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NewsApiService::class.java)
        }

        fun generateNewsList(): List<News> {
            return (1..10).map { generateMockedNews(it) }.toList()
        }

        private fun generateMockedNews(i: Int) = News(
            id = i,
            title = "News title $i",
            text = "This is the news article $i. This is the news article $i. This is the news article $i. " +
                    "This is the news article $i. This is the news article $i. This is the news article $i.",
            isFavorite = false,
            previewUrl = imagesList[i - 1].previewUrl,
            fullUrl = imagesList[i - 1].fullUrl,
        )
    }
}

private data class TestImage(val previewUrl: String, val fullUrl: String)

private val imagesList = listOf(
    TestImage(
        "https://cdn.pixabay.com/photo/2016/10/31/14/55/nothing-1785760_150.jpg",
        "https://pixabay.com/get/g3f1be67988182221c7954d6060091bb343f8c79291a4ba82e8fabcdb4eed357351455335c8588394a4c665f25490f6aed9eaf86328c00299654a0cc5e2e31a97_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2014/12/21/07/49/fireworks-574739_150.jpg",
        "https://pixabay.com/get/gaeb736e3ad474dfb35982161441e9210bed13257d44c7c14ce8f285521e81fa3341152aa0842a19779fd7b90f83e0d91d2e09ac71722a87e7b3f9d2cb9905f94_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2013/03/04/20/59/valley-90388_150.jpg",
        "https://pixabay.com/get/g950275dfb946950678a764c702b670b3c31c60f35a11ed1705bf39af96b2fdc73f3d11e2e9b3d11d5c656859d81bf8ab_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2016/03/06/05/03/rocks-1239727_150.jpg",
        "https://pixabay.com/get/g43f4183ec818605598af846bb2d948ee8b1b261b23dd235c8373523c474272be7d3a9942b20a1497b61432eb79bd7fbbdcc5a1ce3cfa1ca07a4789191ef20624_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2016/10/28/13/09/usa-1777986_150.jpg",
        "https://pixabay.com/get/g379afbc7c2f2d2c43d3b4b6a841243deb6c715e46c6a3e9a01fbb3820bbcea2ce9ab467be0fcaaff239dd2c80e023c821d7a9e7faee1b07893c179650d3ae432_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2015/12/12/22/35/snowman-1090261_150.jpg",
        "https://pixabay.com/get/ga9c2196a35a3b1e5c50cfc6e15811030504e219252f1a39ae844fddc5469a1a793527b934daa102a37b043abf97bb524d902cb0b6e607f2427a1232e26131a2c_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2013/07/27/05/13/lighthouse-168132_150.jpg",
        "https://pixabay.com/get/g08819b4323154b3504d86605a85c5b45b4baa5e9de4c094a267bb53982dee38e1b428e7cc798ee093add1aca89308f04c41f2952c8f00823e4ac24c06ec66d10_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2015/03/11/12/31/buildings-668616_150.jpg",
        "https://pixabay.com/get/gf7a4262c258b3ebe750ca3b65b6d1f9275153ee5330cc824c3a7d4cfc1fc0fc851852f3a2df2385be72ebd81d3c1fc0e4e4baf5f464e71f5ce6e3164770d4e92_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2017/09/05/11/37/baby-2717347_150.jpg",
        "https://pixabay.com/get/g0293930eb93f66c96dd22be7eae09610e50e680c6f2f459d2b771e7c73c8aa40959935bb9de360802e73d797dceea16f90630b5870c447c815680c3883811cab_1280.jpg"
    ),
    TestImage(
        "https://cdn.pixabay.com/photo/2018/03/07/17/15/balloon-3206530_150.jpg",
        "https://pixabay.com/get/g27290e9898cf4650d9ab32ccf15a6ac55db5c91ecf4cbe0e14639964a5a924eb34653662c18c5d9cd68e31c4c33fddb8409557162c7fad5ca4a6aac1c5e2c173_1280.jpg"
    )
)