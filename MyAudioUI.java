// Name: Jace Lee, Student ID: 500884772
import java.util.*;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
    public static void main(String[] args)
    {
        // Simulation of audio content in an online store
        // The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
        AudioContentStore store = new AudioContentStore();

        // Create my music mylibrary
        Library mylibrary = new Library();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");

        // Process keyboard actions
        while (scanner.hasNextLine()) {
            try{

            String action = scanner.nextLine();
            if (action == null || action.equals("")) {
                System.out.print("\n>");
                continue;
            } else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
                return;

            else if (action.equalsIgnoreCase("STORE"))    // List all songs
            {
                store.listAll();
            } else if (action.equalsIgnoreCase("SONGS"))    // List all songs
            {
                mylibrary.listAllSongs();
            } else if (action.equalsIgnoreCase("BOOKS"))    // List all songs
            {
                mylibrary.listAllAudioBooks();
            } else if (action.equalsIgnoreCase("PODCASTS"))    // List all songs
            {
                mylibrary.listAllPodcasts();
            } else if (action.equalsIgnoreCase("ARTISTS"))    // List all songs
            {
                mylibrary.listAllArtists();
            } else if (action.equalsIgnoreCase("PLAYLISTS"))    // List all play lists
            {
                mylibrary.listAllPlaylists();
            }
            // Download audiocontent (song/audiobook/podcast) from the store
            // Specify the index of the content
            else if (action.equalsIgnoreCase("DOWNLOAD")) {
                int index1 = 0;
                int index2 = 0;

                System.out.print("From Store Content #: ");
                if (scanner.hasNextInt()) {
                    index1 = scanner.nextInt();
                    scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
                }
                System.out.print("To Store Content #: ");
                if (scanner.hasNextInt()) {
                    index2 = scanner.nextInt();
                    scanner.nextLine();
                }

                for (int i = index1; i < index2 + 1; i++) {
                    AudioContent content = store.getContent(i);
                    mylibrary.download(content);
                }
            } else if (action.equalsIgnoreCase("DOWNLOADA")) {
                String artist;
                ArrayList<Integer> indices = new ArrayList<>();
                System.out.print("Artist Name: ");
                if (scanner.hasNext()) {
                    artist = scanner.nextLine();
                    indices = store.artistMap(artist);
                    for (int i = 0; i < indices.size(); i++) {
                        AudioContent content = store.getContent(indices.get(i));
                        mylibrary.download(content);
                    }
                }
            } else if (action.equalsIgnoreCase("DOWNLOADG")) {
                String genre;
                ArrayList<Integer> indices = new ArrayList<>();
                System.out.print("Genre: ");
                if (scanner.hasNext()) {
                    genre = scanner.nextLine();
                    indices = store.genreMap(genre);
                    for (int i = 0; i < indices.size(); i++) {
                        AudioContent content = store.getContent(indices.get(i));
                        mylibrary.download(content);
                    }
                }
            }

            // Get the *library* index (index of a song based on the songs list)
            // of a song from the keyboard and play the song
            else if (action.equalsIgnoreCase("PLAYSONG")) {
                int index = 0;

                System.out.print("Song Number: ");
                if (scanner.hasNextInt()) {
                    index = scanner.nextInt();
                    scanner.nextLine();
                    mylibrary.playSong(index);
                }

                // Print error message if the song doesn't exist in the library
            }
            // Print the table of contents (TOC) of an audiobook that
            // has been downloaded to the library. Get the desired book index
            // from the keyboard - the index is based on the list of books in the library
            else if (action.equalsIgnoreCase("BOOKTOC")) {
                int index;

                System.out.print("Audio Book Number: ");
                if (scanner.hasNextInt()) {
                    index = scanner.nextInt();
                    if (index > 2 || index < 1) {
                        System.out.println("Book not found");
                    }
                    scanner.nextLine();
                    mylibrary.printAudioBookTOC(index);
                }


                // Print error message if the book doesn't exist in the library
            }
            // Similar to playsong above except for audio book
            // In addition to the book index, read the chapter
            // number from the keyboard - see class Library
            else if (action.equalsIgnoreCase("PLAYBOOK")) {
                int index;
                int chapter;

                System.out.print("Audio Book Number: ");
                if (scanner.hasNextInt()) {
                    index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Chapter: ");
                    chapter = scanner.nextInt();
                    scanner.nextLine();
                    mylibrary.playAudioBook(index, chapter);
                }

            }
            // Print the episode titles for the given season of the given podcast
            // In addition to the podcast index from the list of podcasts,
            // read the season number from the keyboard
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("PODTOC")) {
                int seasonNum;
                int index;

                System.out.println("Podcast Number: ");
                if (scanner.hasNextInt()) {
                    index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Season: ");
                    if (scanner.hasNextInt()) {
                        seasonNum = scanner.nextInt();
                        scanner.nextLine();
                        mylibrary.printPodcastEpisodes(index, seasonNum);
                    }
                }

            }
            // Similar to playsong above except for podcast
            // In addition to the podcast index from the list of podcasts,
            // read the season number and the episode number from the keyboard
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("PLAYPOD")) {
                int index = 0;
                int episodeNum;
                int seasonNum;

                System.out.print("Podcast Number: ");
                if (scanner.hasNextInt()) {
                    index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Season: ");
                    if (scanner.hasNextInt()) {
                        seasonNum = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Episode: ");
                        if (scanner.hasNextInt()) {
                            episodeNum = scanner.nextInt();
                            scanner.nextLine();
                            mylibrary.playPodcast(index, seasonNum, episodeNum);
                        }
                    }
                }
            }
            // Specify a playlist title (string)
            // Play all the audio content (songs, audiobooks, podcasts) of the playlist
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("PLAYALLPL")) {
                String title;
                System.out.print("Playlist Title: ");
                if (scanner.hasNext()) {
                    title = scanner.next();
                    scanner.nextLine();
                    mylibrary.playPlaylist(title);
                }
            }
            // Specify a playlist title (string)
            // Read the index of a song/audiobook/podcast in the playist from the keyboard
            // Play all the audio content
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("PLAYPL")) {
                String title;
                int index;
                System.out.print("Playlist Title: ");
                if (scanner.hasNext()) {
                    title = scanner.next();
                    scanner.nextLine();
                    System.out.print("Content Number: ");
                    if (scanner.hasNextInt()) {
                        index = scanner.nextInt();
                        scanner.nextLine();
                        mylibrary.playPlaylist(title, index);
                    }
                }

            }
            // Delete a song from the list of songs in mylibrary and any play lists it belongs to
            // Read a song index from the keyboard
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("DELSONG")) {
                int index;
                System.out.print("Library Song #: ");
                if (scanner.hasNextInt()) {
                    index = scanner.nextInt();
                    scanner.nextLine();
                    mylibrary.deleteSong(index);
                }
            }
            // Read a title string from the keyboard and make a playlist
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("MAKEPL")) {
                String playlistTitle;
                System.out.print("Playlist Title: ");
                if (scanner.hasNext()) {
                    playlistTitle = scanner.next();
                    scanner.nextLine();
                    mylibrary.makePlaylist(playlistTitle);
                }

            }
            // Print the content information (songs, audiobooks, podcasts) in the playlist
            // Read a playlist title string from the keyboard
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("PRINTPL"))    // print playlist content
            {
                String playListTitle;
                System.out.print("Playlist Title: ");
                if (scanner.hasNext()) {
                    playListTitle = scanner.next();
                    mylibrary.printPlaylist(playListTitle);
                    scanner.nextLine();
                }
            }
            // Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
            // Read the playlist title, the type of content ("song" "audiobook" "podcast")
            // and the index of the content (based on song list, audiobook list etc) from the keyboard
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("ADDTOPL")) {
                String title, type;
                int num;
                System.out.print("Playlist Title: ");
                if (scanner.hasNext()) {
                    title = scanner.next();
                    scanner.nextLine();
                    System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
                    if (scanner.hasNext()) {
                        type = scanner.next();
                        scanner.nextLine();
                        System.out.print("Library Content #: ");
                        if (scanner.hasNextInt()) {
                            num = scanner.nextInt();
                            scanner.nextLine();
                            mylibrary.addContentToPlaylist(type, num, title);
                        }
                    }
                }
            }
            // Delete content from play list based on index from the playlist
            // Read the playlist title string and the playlist index
            // see class Library for the method to call
            else if (action.equalsIgnoreCase("DELFROMPL")) {
                String playlistTitle;
                int index;
                System.out.print("Playlist Title: ");
                if (scanner.hasNext()) {
                    playlistTitle = scanner.next();
                    scanner.nextLine();
                    System.out.print("Playlist Content #: ");
                    if (scanner.hasNextInt()) {
                        index = scanner.nextInt();
                        scanner.nextLine();
                        mylibrary.delContentFromPlaylist(index, playlistTitle);
                    }
                }
            } else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
            {
                mylibrary.sortSongsByYear();
            } else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
            {
                mylibrary.sortSongsByName();
            } else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
            {
                mylibrary.sortSongsByLength();
            } else if (action.equalsIgnoreCase("SEARCH")) {
                String title;
                System.out.print("Title: ");
                if (scanner.hasNext()) {
                    title = scanner.nextLine();
                    ArrayList<Integer> list = store.titleMap(title);
                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.print(list.get(i) + ". ");
                            store.getContent(list.get(i)).printInfo();
                            System.out.println("");
                        }
                    } else if (list.isEmpty()) {
                        String errorMsg = "No matches for " + title;
                        System.out.println(errorMsg);
                    }
                }
            } else if (action.equalsIgnoreCase("SEARCHA")) {
                String artist;
                System.out.print("Artist: ");
                if (scanner.hasNext()) {
                    artist = scanner.nextLine();
                    ArrayList<Integer> list = store.artistMap(artist);
                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.print(list.get(i) + ". ");
                            store.getContent(list.get(i)).printInfo();
                            System.out.println("");
                        }
                    } else if (list.isEmpty()) {
                        String errorMsg = "No matches for " + artist;
                        System.out.println(errorMsg);
                    }
                }
            } else if (action.equalsIgnoreCase("SEARCHG")) {
                String genre;
                System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
                if (scanner.hasNext()) {
                    genre = scanner.nextLine();
                    ArrayList<Integer> list = store.genreMap(genre);
                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); i++) {
                            System.out.print(list.get(i) + ". ");
                            store.getContent(list.get(i)).printInfo();
                            System.out.println("");
                        }
                    } else if (list.isEmpty()) {
                        String errorMsg = "No matches for " + genre;
                        System.out.println(errorMsg);
                    }
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.print("\n>");
        }
    }
}
