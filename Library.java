// Name: Jace Lee, Student ID: 500884772
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks.
 */
public class Library {
    private ArrayList<Song> songs;
    private ArrayList<AudioBook> audiobooks;
    private ArrayList<Playlist> playlists;
    public AudioContentStore store = new AudioContentStore();


    //private ArrayList<Podcast> 	podcasts;

    // Public methods in this class set errorMesg string
    // Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
    // In assignment 2 we will replace this with Java Exceptions
//    String errorMsg = "";
//
//    public String getErrorMessage()
//    {
//        return errorMsg;
//    }


    public Library() {
        songs = new ArrayList<Song>();
        audiobooks = new ArrayList<AudioBook>();
        ;
        playlists = new ArrayList<Playlist>();
        AudioContentStore store = new AudioContentStore();

        //podcasts		= new ArrayList<Podcast>(); ;
    }

    /*
     * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
     * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
     * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
     * to determine which list it belongs to above
     *
     * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
     * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
     * See the video
     */
    public void download(AudioContent content) {
        try {
            if (content.getType().equals(Song.TYPENAME)) {
                for (Song song : songs) {
                    if (song.getTitle().equals(content.getTitle())) {
                        throw new AlreadyDownloadedException("Song " + content.getTitle() + " already downloaded");
                    }
                }
                songs.add((Song) content);
                System.out.println("SONG " + content.getTitle() + " added to library");

            } else if (content.getType().equals(AudioBook.TYPENAME)) {
                for (AudioBook audioBook : audiobooks) {
                    if (audioBook.getTitle().equals(content.getTitle())) {
                        throw new AlreadyDownloadedException("Audiobook " + content.getTitle() + " already downloaded");
                    }
                }
                audiobooks.add((AudioBook) content);
                System.out.println("AUDIOBOOK " + content.getTitle() + " added to library");

            }
        } catch (AlreadyDownloadedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Print Information (printInfo()) about all songs in the array list
    public void listAllSongs() {
        for (int i = 0; i < songs.size(); i++) {
            int index = i + 1;
            System.out.print("" + index + ". ");
            songs.get(i).printInfo();
            System.out.println();
        }
    }

    // Print Information (printInfo()) about all audiobooks in the array list !!!
    public void listAllAudioBooks() {
        for (int i = 0; i < audiobooks.size(); i++) {
            int index = i + 1;
            System.out.print("" + index + ". ");
            audiobooks.get(i).printInfo();
            System.out.println();
        }
    }

    // Print Information (printInfo()) about all podcasts in the array list !!!
    public void listAllPodcasts() {
//        for (int i = 0; i < podcasts.size(); i++) {
//            int index = i + 1;
//            System.out.println("" + index + ". ");
//            podcasts.get(i).printInfo();
//            System.out.println();
//        }

    }

    // Print the name of all playlists in the playlists array list
    // First print the index number as in listAllSongs() above
    public void listAllPlaylists() {
        for (int i = 0; i < playlists.size(); i++) {
            int index = i + 1;
            System.out.print("" + index + ". ");
            System.out.println(playlists.get(i).getTitle());
        }
    }

    // Print the name of all artists.
    public void listAllArtists() {


        // First create a new (empty) array list of string
        ArrayList<String> artists = new ArrayList<String>();
        for (int i = 0; i < songs.size(); i++) {
            int count = 0;
            if (artists.contains(songs.get(i).getArtist()) == false) {
                artists.add(songs.get(i).getArtist());
            }
        }
        for (int i = 0; i < artists.size(); i++) {
            int index = i + 1;
            System.out.println("" + index + ". " + artists.get(i));
        }
        // Go through the songs array list and add the artist name to the new arraylist only if it is
        // not already there. Once the artist arraylist is complete, print the artists names

    }

    // Delete a song from the library (i.e. the songs list) -
    // also go through all playlists and remove it from any playlist as well if it is part of the playlist
    public void deleteSong(int index) throws AudioContentNotFoundException {
        if (index < 0 || index > songs.size()) {
            throw new AudioContentNotFoundException("Song not found");
        } else {
            Song delsong = songs.get(index - 1);
            songs.remove(index - 1);
            for (int j = 0; j < playlists.size(); j++) {
                if (playlists.get(j).contains(index)) {
                    playlists.get(j).deleteContent(index);
                }
            }
        }
    }

    //Sort songs in library by year
    public void sortSongsByYear() {
        // Use Collections.sort()
        //Collections.sort();
        Collections.sort(songs, new SongYearComparator());

    }

    // Write a class SongYearComparator that implements
    // the Comparator interface and compare two songs based on year
    private class SongYearComparator implements Comparator<Song> {
        public int compare(Song a, Song b) {
            if (a.getYear() < b.getYear()) {
                return -1;
            } else if (a.getYear() > b.getYear()) {
                return 1;
            } else return 0;

        }
    }

    // Sort songs by length
    public void sortSongsByLength() {
        Collections.sort(songs, new SongLengthComparator());
    }

    // Write a class SongLengthComparator that implements
    // the Comparator interface and compare two songs based on length
    private class SongLengthComparator implements Comparator<Song> {
        public int compare(Song a, Song b) {
            if (a.getLength() < b.getLength()) {
                return -1;
            } else if (a.getLength() > b.getLength()) {
                return 1;
            } else return 0;
        }
    }

    // Sort songs by title
    public void sortSongsByName() {

        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return o1.compareTo(o2);

            }
        });

        // Use Collections.sort()
        // class Song should implement the Comparable interface
        // see class Song code
    }



    /*
     * Play Content
     */

    // Play song from songs list
    public void playSong(int index) throws AudioContentNotFoundException {
        if (index < 1 || index > songs.size()) {
            throw new AudioContentNotFoundException("Song Not Found");
        }
        songs.get(index - 1).play();
    }

    // Play podcast from list (specify season and episode)
    // Bonus
    public boolean playPodcast(int index, int season, int episode) {
//            if (index < 1 || index > podcasts.size())
//            {
//                System.out.println("Podcast Not Found");
//                return false;
//            }
//            podcasts.get(index-1).play();
        return false;
    }

    // Print the episode titles of a specified season
    // Bonus
    public boolean printPodcastEpisodes(int index, int season) {
        return false;
    }

    // Play a chapter of an audio book from list of audiobooks
    public void playAudioBook(int index, int chapter) throws AudioContentNotFoundException {
        if (index < 1 || index > audiobooks.size()) {
            throw new AudioContentNotFoundException("Audiobook not found");
        }
        audiobooks.get(index - 1).selectChapter(chapter);
        audiobooks.get(index - 1).play();

    }

    // Print the chapter titles (Table Of Contents) of an audiobook
    // see class AudioBook
    public void printAudioBookTOC(int index) throws AudioContentNotFoundException {
        if (index < 1 || index > audiobooks.size()) {
            throw new AudioContentNotFoundException("Book not found");
        }
        audiobooks.get(index - 1).printTOC();
    }

    /*
     * Playlist Related Methods
     */

    // Make a new playlist and add to playlists array list
    // Make sure a playlist with the same title doesn't already exist
    public void makePlaylist(String title) throws PlaylistAlreadyExistsException {
        Playlist pl = new Playlist(title);
        if (playlists.size() == 0) {
            playlists.add(pl);
        } else if (playlists.size() > 0) {
            for (int i = 0; i < playlists.size(); i++) {
                if (title.equals(playlists.get(i).getTitle())) {
                    throw new PlaylistAlreadyExistsException("Playlist " + title + " already exists");
                } else {
                    playlists.add(pl);
                }
            }
        }
    }

    // Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
    public void printPlaylist(String title) throws PlaylistDoesNotExistException {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getTitle().equals(title)) {
                playlists.get(i).printContents();
            } else {
                throw new PlaylistDoesNotExistException("Playlist " + title + " already exists");
            }
        }
    }

    // Play all content in a playlist
    public void playPlaylist(String playlistTitle) throws PlaylistDoesNotExistException {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getTitle().equals(playlistTitle)) {
                playlists.get(i).playAll();
            } else {
                throw new PlaylistDoesNotExistException("Playlist " + playlistTitle + " already exists");
            }
        }
    }

    // Play a specific song/audiobook in a playlist
    public void playPlaylist(String playlistTitle, int indexInPL) throws PlaylistDoesNotExistException {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getTitle().equals(playlistTitle)) {
                if (indexInPL > 0 || indexInPL < playlists.size()) {
                    System.out.println(playlists.get(i).getTitle());
                    playlists.get(i).play(indexInPL);
                }
            } else {
                throw new PlaylistDoesNotExistException("Playlist " + playlistTitle + " already exists");
            }
        }
    }

    // Add a song/audiobook/podcast from library lists at top to a playlist
    // Use the type parameter and compare to Song.TYPENAME etc
    // to determine which array list it comes from then use the given index
    // for that list
    public void addContentToPlaylist(String type, int index, String playlistTitle) throws InvalidTypeException, PlaylistDoesNotExistException, AudioContentNotFoundException {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getTitle().equals(playlistTitle)) {
                if (type.equalsIgnoreCase(Song.TYPENAME)) {
                    if (!(index < playlists.get(i).getContent().size())) {
                        throw new AudioContentNotFoundException("Out of Index");
                    }
                    playlists.get(i).addContent(songs.get(index - 1));
                } else if (type.equalsIgnoreCase(AudioBook.TYPENAME)) {
                    if (!(index < playlists.get(i).getContent().size())) {
                        throw new AudioContentNotFoundException("Out of Index");
                    }
                    playlists.get(i).addContent(audiobooks.get(index - 1));
                } else {
                    throw new InvalidTypeException();
                }
            } else if (!playlists.get(i).getTitle().equals(playlistTitle)) {
                throw new PlaylistDoesNotExistException("Playlist" + playlistTitle + "does not exist");
            }
        }
    }

    // Delete a song/audiobook/podcast from a playlist with the given title
    // Make sure the given index of the song/audiobook/podcast in the playlist is valid
    public void delContentFromPlaylist(int index, String title) throws AudioContentNotFoundException {
        if (index < 0 || index > playlists.size()) {
            throw new AudioContentNotFoundException("Out of Index");
        } else {
            for (int i = 0; i < playlists.size(); i++) {
                if (playlists.get(i).getTitle().equals(title)) {
                    playlists.get(i).deleteContent(index);
                }
            }
        }
    }

//        public void searchByTitle(String title) throws AudioContentNotFoundException
//        {
//            for (int i = 0; i < store.getContentsList().size(); i++) {
//                if (store.getContentsList().get(i).getTitle().equalsIgnoreCase(title)) {
//                        System.out.print(i + 1 + ". ");
//                        store.getContentsList().get(i).printInfo();
//                        System.out.println("");
//                        return true;
//                }
//            }
//            errorMsg = "No matches for " + title;
//            System.out.println(errorMsg);
//            return false;
//        }

//        public boolean searchByArtist(String artist) {
//            ArrayList<Song> newSongList = new ArrayList<Song>();
//            for (int i = 0; i < store.getFullSongList().size(); i++) {
//                if (store.getFullSongList().get(i).getArtist().equalsIgnoreCase(artist)) {
//                        System.out.print(i + 1 + ". ");
//                        store.getFullSongList().get(i).printInfo();
//                        System.out.println("");
//                        newSongList.add(store.getFullSongList().get(i));
//                    }
//                } if (newSongList.size() == 0){
//                errorMsg = "No matches for " + artist;
//                System.out.println(errorMsg);
//                return false;
//            } return true;
//        }

//        public boolean searchByGenre(String genre){
//            for (int i = 0; i < store.getFullSongList().size(); i++) {
//                if (store.getFullSongList().get(i).getGenre().toString().equals(genre.toUpperCase())){
//                    System.out.print(i + 1 + ". ");
//                    store.getFullSongList().get(i).printInfo();
//                    System.out.println("");
//                }
//            } return false;
//        }
//    }
}
    class AlreadyDownloadedException extends RuntimeException {
        public AlreadyDownloadedException(String message) {
            super(message);
        }
    }

    class AudioContentNotFoundException extends RuntimeException {
        public AudioContentNotFoundException(String message) {
            super(message);
        }
    }

    class PlaylistAlreadyExistsException extends RuntimeException {
        public PlaylistAlreadyExistsException(String message) {
            super(message);
        }
    }

    class PlaylistDoesNotExistException extends RuntimeException {
        public PlaylistDoesNotExistException(String message) {
            super(message);
        }
    }

    class InvalidTypeException extends RuntimeException {

        public InvalidTypeException() {
            super("Invalid Type");
        }
    }


