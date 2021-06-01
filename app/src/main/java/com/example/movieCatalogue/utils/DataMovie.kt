package com.example.movieCatalogue.utils

import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.TvShowEntity

object DataMovie {
    fun generateDataMovie(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                1,
                "A Star Is Born",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "3 October 2018",
                "Drama, Romance, Music",
                "wrFpXMNBRj2PBiN4Z5kix51XaIZ.jpg"
            )
        )
        movies.add(
            MovieEntity(
                2,
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "31 January 2019",
                "Action, Science Fiction, Adventure",
                "xRWht48C2V8XNfzvPehyClOvDni.jpg"
            )
        )
        movies.add(
            MovieEntity(
                3,
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "7 December 2018",
                "Action, Adventure, Fantasy",
                "xLPffWMhMj1l50ND3KchMjYoKmE.jpg"
            )
        )
        movies.add(
            MovieEntity(
                4,
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "24 October 2018",
                "Music, Drama, History",
                "lHu1wtNaczFPGFDTrjCSzeLPTKN.jpg"
            )
        )
        movies.add(
            MovieEntity(
                5,
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "7 February 2019",
                "Action, Crime, Thriller",
                "hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg"
            )
        )
        movies.add(
            MovieEntity(
                6,
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "21 November 2018",
                "Drama",
                "v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg"
            )
        )
        movies.add(
            MovieEntity(
                7,
                "How to Train Your Dragon: The Hidden World",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "3 January 2019",
                "Animation, Family, Adventure",
                "xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg"
            )
        )
        movies.add(
            MovieEntity(
                8,
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "25 April 2018",
                "Adventure, Action, Science Fiction",
                "7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"
            )
        )
        movies.add(
            MovieEntity(
                9,
                "Overlord",
                "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                "1 November 2018",
                "Horror, War, Science Fiction",
                "l76Rgp32z2UxjULApxGXAPpYdAP.jpg"
            )
        )
        movies.add(
            MovieEntity(
                10,
                "Ralph Breaks the Internet",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "20 November 2018",
                "Family, Animation, Comedy, Adventure",
                "qEnH5meR381iMpmCumAIMswcQw2.jpg"
            )
        )
        movies.add(
            MovieEntity(
                11,
                "Robin Hood",
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "21 November 2018",
                "Adventure, Action, Thriller",
                "AiRfixFcfTkNbn2A73qVJPlpkUo.jpg"
            )
        )
        movies.add(
            MovieEntity(
                12,
                "Spider-Man: Into the Spider-Verse",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "6 December 2018",
                "Action, Adventure, Animation, Science Fiction, Comedy",
                "iiZZdoQBEYBv6id8su7ImL0oCbD.jpg"
            )
        )

        return movies
    }

    fun generateDataTvShow(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                1,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "10 October 2012",
                "Crime, Drama, Mystery, Action & Adventure",
                "gKG5QGz5Ngf8fgWpBsWtlg5L2SF.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                2,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "15 February 2019",
                "Sci-Fi & Fantasy, Comedy, Drama",
                "drlfSCIlMKrEeMPhi8pqY4xGxj.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                3,
                "Dragon Ball Z",
                "Five years have passed since the fight with Piccolo Jr., and Goku now has a son, Gohan. The peace is interrupted when an alien named Raditz arrives on Earth in a spacecraft and tracks down Goku, revealing to him that that they are members of a near-extinct warrior race called the Saiyans.",
                "26 April 1989",
                "Animation, Sci-Fi & Fantasy, Action & Adventure",
                "dBsDWUcdfbuZwglgyeeQ9ChRoS4.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                4,
                "Fairy Tail: Phoenix Priestess",
                "The film revolves around a mysterious girl named Éclair who appears before Fairy Tail, the world's most notorious wizard's guild. She lost all of her memories, except for the imperative that she must deliver two Phoenix Stones somewhere. The stones may spell the collapse of the magical world, and Natsu, Lucy, and the rest of the Fairy Tail guild are caught up in the intrigue.",
                "18 August 2012",
                "Action, Adventure, Comedy, Fantasy, Animation",
                "aDuie7u8AL3J6ZH2LS5ySnPkX1W.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                5,
                "Family Guy",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "31 January 1999",
                "Animation, Comedy",
                "eWWCRjBfLyePh2tfZdvNcIvKSJe.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                6,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "7 October 2014",
                "Drama, Sci-Fi & Fantasy",
                "lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                7,
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "22 September 2014",
                "Drama, Crime, Sci-Fi & Fantasy",
                "5tSHzkJ1HBnyGdcpr6wSyw7jYnJ.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                8,
                "Hanna",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "28 March 2019",
                "Action & Adventure, Drama",
                "iYUtjx1EN4SVTgxd2TB4cZTGSQb.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                9,
                "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "17 March 2017",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "49XsMQpVsJEZfHiSipqXm9Wahf9.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                10,
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "26 January 2017",
                "Mystery, Drama, Crime",
                "wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                11,
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "26 October 2015",
                "Drama, Sci-Fi & Fantasy, Action & Adventure",
                "zsaiq8ZclPuneuN7loLEbsh1ANJ.jpg"
            )
        )
        tvShows.add(
            TvShowEntity(
                12,
                "Supernatural",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "13 September 2005",
                "Drama, Mystery, Sci-Fi & Fantasy",
                "KoYWXbnYuS3b0GyQPkbuexlVK9.jpg"
            )
        )

        return tvShows
    }
}