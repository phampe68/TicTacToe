class Song{
    /**
     * title: name of song
     * rating: n stars out of 5
     * composer: who wrote the song
     */
    private String title;
    private String composer;
    //constructor
    public Song(String aSongTitle, String aComposer){
        title = aSongTitle;

        composer = aComposer;
    }

    //print method: prints out all the song's attributes in the console
    public void print(){
        System.out.print("Song: " + title + " by " + composer);
    }
    //get and set methods
    public String getTitle(){return title;}
    public String getComposer(){return composer;}
}