package com.example.borutoandroidcompose.data.remote

import com.example.borutoandroidcompose.domain.model.ApiResponse
import com.example.borutoandroidcompose.domain.model.Hero
import java.io.IOException

class FakeBorutoApiService: BorutoApiService {

    private val heroes: Map<Int, List<Hero>> by lazy {
        mapOf(
            1 to page1,
            2 to page2
        )
    }

    private var page1 = listOf(
        Hero(
            id = 1,
            name = "Sasuke",
            image = "/images/sasuke.jpg",
            about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
            rating = 5.0,
            power = 98,
            birthMonth = "July",
            birthDay = "23rd",
            family = listOf(
                "Fugaku",
                "Mikoto",
                "Itachi",
                "Sarada",
                "Sakura"
            ),
            abilities = listOf(
                "Sharingan",
                "Rinnegan",
                "Sussano",
                "Amateratsu",
                "Intelligence"
            ),
            natureTypes = listOf(
                "Lightning",
                "Fire",
                "Wind",
                "Earth",
                "Water"
            )
        ),
        Hero(
            id = 2,
            name = "Naruto",
            image = "/images/naruto.jpg",
            about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
            rating = 5.0,
            power = 98,
            birthMonth = "Oct",
            birthDay = "10th",
            family = listOf(
                "Minato",
                "Kushina",
                "Boruto",
                "Himawari",
                "Hinata"
            ),
            abilities = listOf(
                "Rasengan",
                "Rasen-Shuriken",
                "Shadow Clone",
                "Senin Mode"
            ),
            natureTypes = listOf(
                "Wind",
                "Earth",
                "Lava",
                "Fire"
            )
        ),
        Hero(
            id = 3,
            name = "Sakura",
            image = "/images/sakura.jpg",
            about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
            rating = 4.5,
            power = 92,
            birthMonth = "Mar",
            birthDay = "28th",
            family = listOf(
                "Kizashi",
                "Mebuki",
                "Sarada",
                "Sasuke"
            ),
            abilities = listOf(
                "Chakra Control",
                "Medical Ninjutsu",
                "Strength",
                "Intelligence"
            ),
            natureTypes = listOf(
                "Earth",
                "Water",
                "Fire"
            )
        )
    )
    private val page2 = listOf(
        Hero(
            id = 4,
            name = "Boruto",
            image = "/images/boruto.png",
            about = "Boruto Uzumaki (うずまきボルト, Uzumaki Boruto) is a shinobi from Konohagakure's Uzumaki clan and a direct descendant of the Hyūga clan through his mother. While initially resentful of his father and his absence since becoming Hokage, Boruto eventually comes to respect his father and duties.",
            rating = 4.9,
            power = 95,
            birthMonth = "Mar",
            birthDay = "27th",
            family = listOf(
                "Naruto",
                "Hinata",
                "Hanabi",
                "Himawari",
                "Kawaki"
            ),
            abilities = listOf(
                "Karma",
                "Jogan",
                "Rasengan",
                "Intelligence"
            ),
            natureTypes = listOf(
                "Lightning",
                "Wind",
                "Water"
            )
        ),
        Hero(
            id = 5,
            name = "Sarada",
            image = "/images/sarada.jpg",
            about = "Sarada Uchiha (うちはサラダ, Uchiha Sarada) is a kunoichi from Konohagakure's Uchiha clan. Because she was raised only by her mother without having her father around, Sarada initially struggles to understand who she is or what she's supposed to be. After meeting him with the help of Naruto Uzumaki, Sarada comes to believe that she is defined by the connections she has with others, and as a member of Team Konohamaru, she seeks to someday become Hokage so that she can connect with as many people as possible.",
            rating = 4.9,
            power = 95,
            birthMonth = "Mar",
            birthDay = "31st",
            family = listOf(
                "Sasuke Uchiha",
                "Sakura Uchiha"
            ),
            abilities = listOf(
                "Sharingan",
                "Strength",
                "Intelligence"
            ),
            natureTypes = listOf(
                "Lightning",
                "Wind",
                "Fire"
            )
        ),
        Hero(
            id = 6,
            name = "Mitsuki",
            image = "/images/mitsuki.jpg",
            about = "Mitsuki (ミツキ, Mitsuki) is a synthetic human that was created as a partial clone of Orochimaru. Immigrating to Konohagakure to confirm whether or not Boruto Uzumaki was his \"sun\", he became a shinobi and was placed on Team Konohamaru. Mitsuki was created as a clone of Orochimaru, being cultivated from the same embryo as at least one older \"Mitsuki\", and raised in a test tube.",
            rating = 4.9,
            power = 95,
            birthMonth = "Jul",
            birthDay = "25th",
            family = listOf(
                "Orochimaru",
                "Log"
            ),
            abilities = listOf(
                "Senin Mode",
                "Transformation",
                "Intelligence"
            ),
            natureTypes = listOf(
                "Lightning",
                "Wind"
            )
        )
    )

    fun clearData() {
        page1 = emptyList()
    }

    private var exception = false
    fun setException() {
        exception = true
    }
    override suspend fun getAllHeroes(page: Int): ApiResponse<Hero> {
        if (exception) throw IOException()
        require(page in 1..5)
        return ApiResponse(
            success = true,
            message = "Ok",
            previousPage = calculatePage(page = page)["prevPage"],
            nextPage = calculatePage(page = page)["nextPage"],
            result = heroes[page]!!
        )
    }

    override suspend fun searchHeroes(name: String): ApiResponse<Hero> = ApiResponse(success = false)

    private fun calculatePage(page: Int): Map<String, Int?> {
        if (page1.isEmpty())return mapOf("prevPage" to null, "nextPage" to null)
        var previousPage: Int? = page
        var nextPage: Int? = page

        if (page == 1) previousPage = null
        if (page == 5) nextPage = null
        if (page in 1..4) nextPage = nextPage?.plus(1)
        if (page in 2..5) previousPage = previousPage?.minus(1)
        return mapOf("prevPage" to previousPage, "nextPage" to nextPage)
    }
}