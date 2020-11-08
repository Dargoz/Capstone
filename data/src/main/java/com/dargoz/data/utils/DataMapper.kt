package com.dargoz.data.utils

import android.util.Log
import com.dargoz.data.source.local.entity.AnimeEntity
import com.dargoz.data.source.local.entity.ReviewEntity
import com.dargoz.data.source.remote.responses.*
import com.dargoz.domain.models.*

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

    fun mapResponseToEntities(animeList: List<AnimeResponse>, cache: List<Anime>?): List<AnimeEntity> {
        val animeEntities = ArrayList<AnimeEntity>()
        Log.v("DRG", "result : ${animeList[0].id}")
        var index = 0
        animeList.map {
            Log.v("DRG", "result : ${it.id}")
            val tempIsFavorite = cache?.get(index++)?.isFavorite ?: false
            val animeEntity = AnimeEntity(
                malId = it.id,
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
                genres = mapResponseToModel(it.genres),
                characters = null,
                openingThemes = it.openingThemes,
                endingThemes = it.endingThemes,
                isFavorite = tempIsFavorite
            )
            animeEntities.add(animeEntity)
        }
        return animeEntities
    }

    private fun mapDomainToEntity(anime: Anime): AnimeEntity {
        return AnimeEntity(
            malId = anime.id,
            title = anime.title,
            titleEnglish = anime.titleEnglish,
            titleJapanese = anime.titleJapanese,
            imageUrl = anime.imageUrl,
            synopsis = anime.synopsis,
            type = anime.type,
            source = anime.source,
            status = anime.status,
            episodes = anime.episodes,
            duration = anime.duration,
            rating = anime.rating,
            popularity = anime.popularity,
            members = anime.members,
            score = anime.score,
            genres = anime.genres,
            characters = anime.characters,
            openingThemes = anime.openingThemes,
            endingThemes = anime.endingThemes,
            isFavorite = anime.isFavorite
        )
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

    fun mapVoiceActorResponseToModel(voiceActorResponse: List<VoiceActorResponse>): List<VoiceActor> {
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
            isFavorite = animeEntity.isFavorite
        )
    }

    fun mapResponseToEntity(data: AnimeResponse, cacheId: Long?): AnimeEntity {
        return AnimeEntity(
            id = cacheId,
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
            genres = mapResponseToModel(data.genres),
            characters = null,
            openingThemes = data.openingThemes,
            endingThemes = data.endingThemes,
            isFavorite = false
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
}