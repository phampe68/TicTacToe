import java.util.ArrayList;


class MyApp{
    public static void main(String[] args){
        //list of songs
        ArrayList<Song> songs = new ArrayList<Song>();


        //create 2 instances of song and add it to our list of songs
        songs.add(new RecordedSong("Girl From Ipanema", "Antonio Carlos Jobim", "Stan Getz", "file1.mp3", "*****"));
        songs.add(new RecordedSong("Hotel California", "Don Henley Glenn Frey Don Felder", "Eagles", "hotel_california.mp3", "****"));

        System.out.println("Songs:");
        //print info for each song
        for(Song s : songs){
            s.print();
        }
    }
}