package com.why.movieCatalogue.utils

import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.data.source.remote.response.*

object DataMovie {
    fun generateDataMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                337404,
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                "Comedy, Crime",
                "A0knvX7rlwTyZSKj8H5NiARb45.jpg"
            )
        )
        movies.add(
            MovieEntity(
                399579,
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "31 January 2019",
                "Action, Science Fiction, Adventure",
                "xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )
        movies.add(
            MovieEntity(
                632357,
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "2021-03-31",
                "Horror",
                "bShgiEQoPnWdw4LBrYT5u18JF34.jpg"
            )
        )
        movies.add(
            MovieEntity(
                424694,
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "24 October 2018",
                "Music, Drama, History",
                "lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )
        movies.add(
            MovieEntity(
                438650,
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "7 February 2019",
                "Action, Crime, Thriller",
                "hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )
        movies.add(
            MovieEntity(
                480530,
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "21 November 2018",
                "Drama",
                "v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg"
            )
        )
        movies.add(
            MovieEntity(
                166428,
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "3 January 2019",
                "Animation, Family, Adventure",
                "xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )
        movies.add(
            MovieEntity(
                299536,
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "25 April 2018",
                "Adventure, Action, Science Fiction",
                "7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )


        return movies
    }

    fun getDetailMovie(): MovieEntity {
        return MovieEntity(
            337404,
            "Cruella",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            "2021-05-26",
            "Comedy, Crime",
            "A0knvX7rlwTyZSKj8H5NiARb45.jpg",
            false
        )
    }

    fun generateDataTvShow(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                1412,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "10 October 2012",
                "Crime, Drama, Mystery, Action & Adventure",
                "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                79501,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "15 February 2019",
                "Sci-Fi & Fantasy, Comedy, Drama",
                "drlfSCIlMKrEeMPhi8pqY4xGxj.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                60735,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                "Drama, Sci-Fi & Fantasy",
                "lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                1434,
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "31 January 1999",
                "Animation, Comedy",
                "eWWCRjBfLyePh2tfZdvNcIvKSJe.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                12971,
                "Dragon Ball Z",
                "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
                "26 April 1989",
                "Animation, Sci-Fi & Fantasy, Action & Adventure",
                "dBsDWUcdfbuZwglgyeeQ9ChRoS4.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                60708,
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "22 September 2014",
                "Drama, Crime, Sci-Fi & Fantasy",
                "5tSHzkJ1HBnyGdcpr6wSyw7jYnJ.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                54155,
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "28 March 2019",
                "Action & Adventure, Drama",
                "iYUtjx1EN4SVTgxd2TB4cZTGSQb.jpg"
            )
        )
        return tvShows
    }

    fun getDetailTvShow(): TvShowEntity {
        return TvShowEntity(
            60735,
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "2014-10-07",
            "Drama, Sci-Fi & Fantasy",
            "lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            false
        )
    }

    fun getRemoteMovies(): List<ResultMovie> {
        val movies = ArrayList<ResultMovie>()

        movies.add(
            ResultMovie(
                337404,
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "2021-05-26",
                listOf("Comedy", "Crime"),
                "A0knvX7rlwTyZSKj8H5NiARb45.jpg"
            )
        )
        movies.add(
            ResultMovie(
                399579,
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "31 January 2019",
                listOf("Action", "Science Fiction", "Adventure"),
                "xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )
        movies.add(
            ResultMovie(
                632357,
                "The Unholy",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "2021-03-31",
                listOf("Horror"),
                "bShgiEQoPnWdw4LBrYT5u18JF34.jpg"
            )
        )
        movies.add(
            ResultMovie(
                424694,
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "24 October 2018",
                listOf("Music", "Drama", "History"),
                "lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )
        movies.add(
            ResultMovie(
                438650,
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "7 February 2019",
                listOf("Action", "Crime", "Thriller"),
                "hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )
        movies.add(
            ResultMovie(
                480530,
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "21 November 2018",
                listOf("Drama"),
                "v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg"
            )
        )
        movies.add(
            ResultMovie(
                166428,
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "3 January 2019",
                listOf("Animation", "Family", "Adventure"),
                "xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )
        movies.add(
            ResultMovie(
                299536,
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "25 April 2018",
                listOf("Adventure", "Action", "Science Fiction"),
                "7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )

        return movies
    }

    fun getRemoteTvShows(): List<ResultTvShow> {
        val tvShows = ArrayList<ResultTvShow>()

        tvShows.add(
            ResultTvShow(
                1412,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "10 October 2012",
                listOf("Crime", "Drama", "Mystery", "Action & Adventure"),
                "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
            )
        )
        tvShows.add(
            ResultTvShow(
                79501,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "15 February 2019",
                listOf("Sci-Fi & Fantasy", "Comedy", "Drama"),
                "drlfSCIlMKrEeMPhi8pqY4xGxj.jpg"
            )
        )
        tvShows.add(
            ResultTvShow(
                60735,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "2014-10-07",
                listOf("Drama", "Sci-Fi & Fantasy"),
                "lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )
        tvShows.add(
            ResultTvShow(
                1434,
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "31 January 1999",
                listOf("Animation", "Comedy"),
                "eWWCRjBfLyePh2tfZdvNcIvKSJe.jpg"
            )
        )
        tvShows.add(
            ResultTvShow(
                12971,
                "Dragon Ball Z",
                "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
                "26 April 1989",
                listOf("Animation", "Sci-Fi & Fantasy", "Action & Adventure"),
                "dBsDWUcdfbuZwglgyeeQ9ChRoS4.jpg"
            )
        )
        tvShows.add(
            ResultTvShow(
                60708,
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "22 September 2014",
                listOf("Drama", "Crime", "Sci-Fi & Fantasy"),
                "5tSHzkJ1HBnyGdcpr6wSyw7jYnJ.jpg"
            )
        )
        tvShows.add(
            ResultTvShow(
                54155,
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "28 March 2019",
                listOf("Action & Adventure", "Drama"),
                "iYUtjx1EN4SVTgxd2TB4cZTGSQb.jpg"
            )
        )


        return tvShows
    }

    fun getRemoteDetailMovie(): MovieResponse {
        return MovieResponse(
            337404,
            "Cruella",
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            "2021-05-26",
            listOf(Genre(
                35,
                "Comedy"
            ), Genre(80,
                "Crime")


                ),
            "A0knvX7rlwTyZSKj8H5NiARb45.jpg"
        )
    }

    fun getRemoteDetailTvShow() : TvShowResponse {
        return TvShowResponse(
            60735,
            "The Flash",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "2014-10-07",
            listOf(TvShowResponse.Genre(18,
                "Drama"), TvShowResponse.Genre(10765,
                "Sci-Fi & Fantasy")),
            "lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
        )
    }
}