public class Audio {

    private String title;
    private String artist;
    private String album;
    private long length;
    private String path;


    Audio(String title, String artist, String album, long length, String path)
    {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.length = length;
        this.path = path;

    }

    public String getLengthInString ()
    {
        long minutes = length / 60;
        long seconds = length % 60;
        return  minutes +" m " + seconds + " s";
    }

    public String getTitle()
    {
        return title;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getAlbum()
    {
        return album;
    }

    public long getLengthInSeconds()
    {
        return  length;
    }

    public String getPath()
    {
        return  path;
    }

    public void  describe()
    {
        System.out.println("Title: " + getTitle() + "\n" + "Artist: " + getArtist() + "\n" + "Album: " + getAlbum() +
                "\n" + "Length: " + getLengthInString() + "\n" + "Path: " + getPath());
    }

    public boolean isSame(Audio audio)
    {
        return  (title == audio.title ||( title!= null && title.equals(audio.title)))
                && (artist == audio.artist ||( artist!= null && artist.equals(audio.artist)))
                && (album == audio.album ||( album!= null && album.equals(audio.album)));
    }
    @Override
    public  boolean equals(Object object)
    {
        if(object == this)
        {
            return true;
        }
        if(object == null  || object.getClass() != this.getClass())
        {
            return  false;
        }
        Audio audio = (Audio) object;

        return  this.hashCode() == audio.hashCode() && length == audio.length && (title == audio.title ||( title!= null && title.equals(audio.title)))
                && (artist == audio.artist ||( artist!= null && artist.equals(audio.artist)))
                && (album == audio.album ||( album!= null && album.equals(audio.album)));
    }
    @Override
    public  int hashCode()
    {
        final int prime = 5;
        int result = 1;
        result = prime * result + (int) length;
        result= prime * result + ((title == null) ? 0 : title.hashCode());
        result= prime * result + ((artist == null) ? 0 : artist.hashCode());
        result= prime * result + ((album == null) ? 0 : album.hashCode());
        return  result;
    }
}