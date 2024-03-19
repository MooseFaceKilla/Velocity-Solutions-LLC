import java.util.ArrayList;
import java.util.Scanner;

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString() {
        return title + " by " + artist;
    }
}

class Playlist {
    private String name;
    private ArrayList<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void displayPlaylist() {
        System.out.println("Playlist: " + name);
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}


public class PlaylistManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Playlist> playlists = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Create Playlist");
            System.out.println("2. Add Song to Playlist");
            System.out.println("3. Display Playlists");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter playlist name: ");
                    String playlistName = scanner.nextLine();
                    playlists.add(new Playlist(playlistName));
                    System.out.println("Playlist created: " + playlistName);
                    break;
                case 2:
                    if (playlists.isEmpty()) {
                        System.out.println("No playlists created yet. Create a playlist first.");
                        break;
                    }
                    System.out.print("Enter playlist name to add song: ");
                    String playlistToAdd = scanner.nextLine();
                    Playlist selectedPlaylist = null;
                    for (Playlist playlist : playlists) {
                        if (playlist.getName().equalsIgnoreCase(playlistToAdd)) {
                            selectedPlaylist = playlist;
                            break;
                        }
                    }
                    if (selectedPlaylist == null) {
                        System.out.println("Playlist not found.");
                        break;
                    }
                    System.out.print("Enter song title: ");
                    String songTitle = scanner.nextLine();
                    System.out.print("Enter artist name: ");
                    String artistName = scanner.nextLine();
                    selectedPlaylist.addSong(new Song(songTitle, artistName));
                    System.out.println("Song added to playlist: " + selectedPlaylist.getName());
                    break;
                case 3:
                    if (playlists.isEmpty()) {
                        System.out.println("No playlists created yet.");
                        break;
                    }
                    System.out.println("Displaying playlists:");
                    for (Playlist playlist : playlists) {
                        playlist.displayPlaylist();
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

