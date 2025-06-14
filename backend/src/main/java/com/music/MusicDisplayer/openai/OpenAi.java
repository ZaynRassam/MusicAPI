package com.music.MusicDisplayer.openai;

import com.music.MusicDisplayer.artist.Artist;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenAi {
    public String createArtistUsingAi(String artistObject, ChatClient chatClient) {
        String prompt = """
                You are part of a Java Spring Boot application, where you need to create an artist object.
                The artist object should have the following properties:
                artistName,
                artistUrl (600x400 image of the artist),
                artistBio (Short description of the artist),
                artistAlbums (List of Album objects).
                The artistAlbums should have the following properties:
                albumName,
                albumUrl (300x300 image of the album taken from wikipedia ONLY),
                description (Short description of the album),
                releaseYear,
                songs (List of Song objects).
                The songs should have the following properties:
                songName,
                duration (in seconds),
                trackNumber (signifying the order of the album).

                A artist object will be given to you for you to complete, it may only have a few properties filled in, your
                task is to fill in the rest of the properties with REAL WORLD information - do not make anything up.
                Add every single other album (and song) that is missing (for example, The above is missing the album object
                "Houses of the Holy", this needs to be added).
             
                Albums (and songs) should only be from the standard releases of the Artist.
                DO NOT create or include any IDs in your return object (for artists, albums and songs),
                as these will be auto generated by the Spring Boot application.
                
                Return ONLY the artist object, do not return ANY other text.
                
                Lastly, If all the albums are present and is nothing more to be added (like bios, and descriptions) to the artist object,
                return the artist object as is. BEFORE you return the artist object, you should check AND DOUBLE CHECK 
                if it is complete, factual and that ALL the links provided are working.
                If it is not complete, go back and fill in the missing properties.
                
                An example of an artist object is shown below, where the albums are incomplete,
                notice how there is nothing outside of the object's curly braces - this is EXTREMELY important:
                
                {
                    "artistName": "Led Zeppelin",
                    "artistUrl": "https://www.sportphotogallery.com/content/images/cmsfiles/product/17737/17737-list.jpg",
                    "bio": "Formed in 1968 in London, Led Zeppelin consisted of Robert Plant, Jimmy Page, John Paul Jones, and John Bonham. Known for pioneering hard rock and heavy metal, they fused blues, folk, and psychedelia into a powerful sound. With classic albums like IV and Physical Graffiti, they became one of the most influential and best-selling rock bands of all time.",
                    "albums": [
                        {
                            "albumName": "Led Zeppelin IV",
                            "albumUrl": "https://upload.wikimedia.org/wikipedia/en/2/26/Led_Zeppelin_-_Led_Zeppelin_IV.jpg?20150313082239",
                            "releaseYear": "1971",
                            "description": "Often referred to as \\"Untitled\\" or \\"Four Symbols,\\" this album features the iconic track \\"Stairway to Heaven\\" and blends hard rock, folk, and blues. It cemented Led Zeppelin's status as rock legends.\\n\\n",
                            "songs": [
                                {
                                    "songName": "Four Sticks",
                                    "duration": 284,
                                    "trackNumber": 6
                                },
                                {
                                    "songName": "Going to California",
                                    "duration": 215,
                                    "trackNumber": 7
                                },
                                {
                                    "songName": "Misty Mountain Hop",
                                    "duration": 278,
                                    "trackNumber": 5
                                },
                                {
                                    "songName": "Black Dog",
                                    "duration": 296,
                                    "trackNumber": 1
                                },
                                {
                                    "songName": "Rock and Roll",
                                    "duration": 223,
                                    "trackNumber": 2
                                },
                                {
                                    "songName": "The Battle of Evermore",
                                    "duration": 347,
                                    "trackNumber": 3
                                },
                                {
                                    "songName": "When the Levee Breaks",
                                    "duration": 427,
                                    "trackNumber": 8
                                },
                                {
                                    "songName": "Stairway to Heaven",
                                    "duration": 482,
                                    "trackNumber": 4
                                }
                            ]
                        },
                        {
                            "albumName": "Physical Graffiti",
                            "albumUrl": "https://upload.wikimedia.org/wikipedia/en/e/e3/Led_Zeppelin_-_Physical_Graffiti.jpg?20150313082619",
                            "releaseYear": "1975",
                            "description": "A double album showcasing the band's versatility, including epic tracks like \\"Kashmir.\\" The music ranges from hard rock to funk and progressive influences.\\n\\n",
                            "songs": [
                                {
                                    "songName": "The Wanton Song",
                                    "duration": 248,
                                    "trackNumber": 12
                                },
                                {
                                    "songName": "Sick Again",
                                    "duration": 276,
                                    "trackNumber": 15
                                },
                                {
                                    "songName": "Night Flight",
                                    "duration": 222,
                                    "trackNumber": 11
                                },
                                {
                                    "songName": "Houses of the Holy",
                                    "duration": 243,
                                    "trackNumber": 4
                                },
                                {
                                    "songName": "The Rover",
                                    "duration": 340,
                                    "trackNumber": 2
                                },
                                {
                                    "songName": "Custard Pie",
                                    "duration": 257,
                                    "trackNumber": 1
                                },
                                {
                                    "songName": "Down by the Seaside",
                                    "duration": 305,
                                    "trackNumber": 9
                                },
                                {
                                    "songName": "Black Country Woman",
                                    "duration": 264,
                                    "trackNumber": 14
                                },
                                {
                                    "songName": "Boogie with Stu",
                                    "duration": 219,
                                    "trackNumber": 13
                                },
                                {
                                    "songName": "Bron-Yr-Aur",
                                    "duration": 122,
                                    "trackNumber": 8
                                },
                                {
                                    "songName": "In My Time of Dying",
                                    "duration": 665,
                                    "trackNumber": 3
                                },
                                {
                                    "songName": "Ten Years Gone",
                                    "duration": 402,
                                    "trackNumber": 10
                                },
                                {
                                    "songName": "Kashmir",
                                    "duration": 515,
                                    "trackNumber": 6
                                },
                                {
                                    "songName": "Trampled Under Foot",
                                    "duration": 311,
                                    "trackNumber": 5
                                },
                                {
                                    "songName": "In The Light",
                                    "duration": 526,
                                    "trackNumber": 7
                                }
                            ]
                        }
                    ]
                }
                
                Artist object to complete:
                \n
                """ + artistObject;
        return chatClient.prompt(prompt).call().content();
    }
}
