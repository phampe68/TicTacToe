public class Song{

    //represents a song
    private String title;  //title of the song
    private String rating; //"*" ... "*****"

    //constructor
    public Song(String aSongTitle, String aRating){
        title = aSongTitle;
        rating = aRating;
    }
    //get and set methods
    public String getTitle(){return title;}
    public String getRating(){return rating;}
    public void setRating(String aRating){
        rating = aRating;
    }
}