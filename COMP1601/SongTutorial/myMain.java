class myMain {
   public static void main(String[] args){
      System.out.println("Hello World");

      Song song = new Song("Girl From Ipanema", "***");
      System.out.println("Song: " + song.getTitle() + " rating: " + song.getRating());
      song.setRating("*****");
      System.out.println("Song: " + song.getTitle() + " rating: " + song.getRating());
   }
}