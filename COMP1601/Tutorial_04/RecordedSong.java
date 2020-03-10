
public class RecordedSong extends Song{
    //represents a song
    private String artist; //performer
    private String mp3Filename;
    private String rating; //"*" ... "****"

    //constructor
    public RecordedSong(String aSongTitle, String aComposer, String anArtist, String anMp3Filename, String aRating){
        super(aSongTitle, aComposer);
        artist = anArtist;
        mp3Filename = anMp3Filename;
        rating = aRating;
    }

    //get and set methods
    public String getArtist(){return artist;}
    public String getMp3Filename(){return mp3Filename;}
    public String getRating(){return rating;}
    public void setRating(String aRating){
        rating = aRating;
    }

    public void print(){
        super.print();
        System.out.print(" artist: " + artist + " mp3: " + mp3Filename + " rating: " + rating);
        System.out.println(""); //add linebreak




    }


}