package com.dargoz.data.utils

import android.util.Log
import com.dargoz.data.source.local.entity.AnimeEntity
import com.dargoz.data.source.local.entity.MangaEntity
import com.dargoz.data.source.local.entity.ReviewEntity
import com.dargoz.data.source.remote.responses.*
import com.dargoz.domain.models.*
import java.util.*
import kotlin.collections.ArrayList

object DataMapper {

    fun mapEntitiesToDomain(animeEntities: List<AnimeEntity>): List<Anime> {
        val animeList = ArrayList<Anime>()

        animeEntities.map {

            val anime = Anime(
                id = it.id!!,
                malId = it.malId,
                title = it.title,
                titleEnglish = it.titleEnglish,
                titleJapanese = it.titleJapanese,
                imageUrl = it.imageUrl,
                synopsis = it.synopsis,
                type = it.type,
                source = it.source,
                status = it.status,
                episodes = it.episodes,
                duration = it.duration,
                rating = it.rating,
                popularity = it.popularity,
                members = it.members,
                score = it.score,
                genres = it.genres,
                characters = it.characters,
                openingThemes = it.openingThemes,
                endingThemes = it.endingThemes,
                isFavorite = it.isFavorite
            )
            animeList.add(anime)
        }
        return animeList
    }


    fun mapResponseToEntities(
        animeList: List<AnimeResponse>,
        cache: List<Anime>?,
        seasonName: String = "",
        year: Int = 0,
        day: String = "",
        subtype: String = "",
    ): List<AnimeEntity> {
        val animeEntities = ArrayList<AnimeEntity>()
        Log.v("DRG", "result : ${animeList[0].id}")
        var index = 0
        animeList.map {
            Log.v("DRG", "result : ${it.id}")

            val tempIsFavorite = try {
                cache?.get(index++)?.isFavorite ?: false
            } catch (e: Exception) {
                false
            }

            val animeEntity = AnimeEntity(
                malId = it.id,
                title = it.title,
                titleEnglish = it.titleEnglish,
                titleJapanese = it.titleJapanese,
                imageUrl = it.imageUrl,
                synopsis = it.synopsis,
                type = it.type,
                source = it.source,
                status = subtype.ifEmpty { it.status },
                episodes = it.episodes,
                duration = it.duration,
                rating = it.rating,
                popularity = it.popularity,
                members = it.members,
                score = it.score,
                genres = if(it.genres == null) null else mapResponseToModel(it.genres),
                characters = null,
                openingThemes = it.openingThemes,
                endingThemes = it.endingThemes,
                isFavorite = tempIsFavorite,
                seasonName = seasonName,
                seasonYear = year,
                releaseDay = day,
            )
            animeEntities.add(animeEntity)
        }
        return animeEntities
    }

    private fun mapResponseToModel(genreResponses: List<GenreResponse>): List<Genre> {
        val genreList = ArrayList<Genre>()
        genreResponses.map {
            val genre = Genre(
                malId = it.malId,
                type = it.type,
                name = it.name,
                url = it.url
            )
            genreList.add(genre)
        }
        return genreList
    }

    fun mapTopAnimeResponseToAnimeResponse(topAnimeResponse: List<TopAnimeResponse>) : List<AnimeResponse> {
        val animeResponseList = ArrayList<AnimeResponse>()
        topAnimeResponse.map {
            val animeResponse = AnimeResponse(
                id = it.malId,
                title = it.title,
                imageUrl = it.imageUrl,
                members =  it.members,
                type =  it.type,
                score = 0.0,
                titleJapanese = "",
                titleEnglish = "",
                status = "",
                source = "",
                synopsis = "",
                duration = "",
                episodes = 0,
                popularity = 0,
                rating = "",
                genres = ArrayList(),
                endingThemes = ArrayList(),
                openingThemes = ArrayList(),
            )
            animeResponseList.add(animeResponse)
        }
        return animeResponseList
    }

    fun mapResponseToModelChar(characterResponse: List<CharacterResponse>): List<Characters> {
        val charactersList = ArrayList<Characters>()
        characterResponse.map {
            val characters = Characters(
                malId = it.malId,
                url = it.url,
                imageUrl = it.imageUrl,
                name = it.name,
                role = it.role,
                voiceActors = mapVoiceActorResponseToModel(it.voiceActors)
            )
            charactersList.add(characters)
        }
        return charactersList
    }

    private fun mapVoiceActorResponseToModel(voiceActorResponse: List<VoiceActorResponse>)
            : List<VoiceActor> {
        val voiceActorList = ArrayList<VoiceActor>()
        voiceActorResponse.map {
            val voiceActor = VoiceActor(
                malId = it.malId,
                url = it.url,
                imageUrl = it.imageUrl,
                name = it.name,
                language = it.language
            )
            voiceActorList.add(voiceActor)
        }
        return voiceActorList
    }

    fun mapEntityToDomain(animeEntity: AnimeEntity): Anime {
        return Anime(
            id = animeEntity.id!!,
            malId = animeEntity.malId,
            title = animeEntity.title,
            titleEnglish = animeEntity.titleEnglish,
            titleJapanese = animeEntity.titleJapanese,
            imageUrl = animeEntity.imageUrl,
            synopsis = animeEntity.synopsis,
            type = animeEntity.type,
            source = animeEntity.source,
            status = animeEntity.status,
            episodes = animeEntity.episodes,
            duration = animeEntity.duration,
            rating = animeEntity.rating,
            popularity = animeEntity.popularity,
            members = animeEntity.members,
            score = animeEntity.score,
            genres = animeEntity.genres,
            characters = animeEntity.characters,
            openingThemes = animeEntity.openingThemes,
            endingThemes = animeEntity.endingThemes,
            seasonName = animeEntity.seasonName,
            seasonYear = animeEntity.seasonYear,
            releaseDay = animeEntity.releaseDay,
            isFavorite = animeEntity.isFavorite
        )
    }

    fun mapResponseToEntity(data: AnimeResponse, cache: Anime?): AnimeEntity {
        return AnimeEntity(
            id = cache?.id,
            malId = data.id,
            title = data.title,
            titleEnglish = data.titleEnglish,
            titleJapanese = data.titleJapanese,
            imageUrl = data.imageUrl,
            synopsis = data.synopsis,
            type = data.type,
            source = data.source,
            status = data.status,
            episodes = data.episodes,
            duration = data.duration,
            rating = data.rating,
            popularity = data.popularity,
            members = data.members,
            score = data.score,
            genres = if (data.genres == null) null else mapResponseToModel(data.genres),
            characters = null,
            openingThemes = data.openingThemes,
            endingThemes = data.endingThemes,
            seasonName = cache!!.seasonName,
            seasonYear = cache.seasonYear,
            releaseDay = cache.releaseDay,
            isFavorite = cache.isFavorite
        )
    }

    fun mapEntitiesToCharactersDomain(it: AnimeEntity): List<Characters>? {
        return it.characters
    }

    fun mapEntitiesToDomainReview(reviewEntities: List<ReviewEntity>): List<Review> {
        val reviewList = ArrayList<Review>()
        reviewEntities.map {
            val review = Review(
                id = it.malId,
                malId = it.malId,
                animeId = it.animeId,
                url = it.url,
                type = it.type,
                helpfulCount = it.helpfulCount,
                date = it.date,
                reviewer = it.reviewer,
                content = it.content,
            )
            reviewList.add(review)
        }
        return reviewList
    }

    fun mapReviewResponseToEntities(animeId: Long, data: List<ReviewResponse>): List<ReviewEntity> {
        val reviewEntities = ArrayList<ReviewEntity>()
        data.map {
            val reviewEntity = ReviewEntity(
                malId = it.malId,
                animeId = animeId,
                url = it.url,
                type = it.type,
                helpfulCount = it.helpfulCount,
                date = it.date,
                reviewer = mapReviewResponseToModel(it.reviewer),
                content = it.content,
            )
            reviewEntities.add(reviewEntity)
        }
        return reviewEntities
    }

    private fun mapReviewResponseToModel(reviewer: ReviewerResponse): Reviewer {
        return Reviewer(
            url = reviewer.url,
            imageUrl = reviewer.imageUrl,
            username = reviewer.username,
            episodeSeen = reviewer.episodeSeen,
            scores = mapScoreResponseToModel(reviewer.scores)
        )
    }

    private fun mapScoreResponseToModel(scores: ScoreResponse): Score {
        return Score(
            overall = scores.overall,
            story = scores.story,
            animation = scores.animation,
            sound = scores.sound,
            character = scores.character,
            enjoyment = scores.enjoyment
        )
    }

    fun mapMangaEntitiesToDomain(it: List<MangaEntity>): List<Manga> {
        val mangaList = ArrayList<Manga>()
        it.map {
            val manga = Manga(
                id = it.id!!,
                malId = it.malId,
                rank = it.rank,
                title = it.title,
                url = it.url,
                type = it.type.toLowerCase(Locale.ROOT),
                volume = it.volume,
                startDate = it.startDate,
                members = it.members,
                score = it.score,
                imageUrl = it.imageUrl
            )
            mangaList.add(manga)
        }
        return mangaList
    }

    fun mapMangaResponseToEntities(data: List<MangaResponse>): List<MangaEntity> {
        val mangaEntities = ArrayList<MangaEntity>()
        data.map {
            val mangaEntity = MangaEntity(
                malId = it.malId,
                rank = it.rank,
                title = it.title,
                url = it.url,
                type = it.type.toLowerCase(Locale.ROOT),
                volume = it.volume,
                startDate = it.startDate,
                members = it.members,
                score = it.score,
                imageUrl = it.imageUrl
            )
            mangaEntities.add(mangaEntity)
        }
        return mangaEntities
    }

    fun mapAnimeResponseToDomain(animeResponse: AnimeResponse): Anime {
        return Anime(
            id = 0,
            malId = animeResponse.id,
            title = animeResponse.title,
            titleEnglish = animeResponse.titleEnglish,
            titleJapanese = animeResponse.titleJapanese,
            imageUrl = animeResponse.imageUrl,
            synopsis = animeResponse.synopsis,
            type = animeResponse.type,
            source = animeResponse.source,
            status = animeResponse.status,
            episodes = animeResponse.episodes,
            duration = animeResponse.duration,
            rating = animeResponse.rating,
            popularity = animeResponse.popularity,
            members = animeResponse.members,
            score = animeResponse.score,
            genres = ArrayList(),
            characters = null,
            openingThemes = null,
            endingThemes = null,
            isFavorite = false
        )
    }
}