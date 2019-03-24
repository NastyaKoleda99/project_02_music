import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Artist {

    private String name;
    private Map <String,Album> albums = new HashMap<String,Album>();

    Artist (String name)
    {
        this.name = name;
    }

    public void addAudio(Audio audio)
    {
        String albumName = audio.getAlbum();
        Album album = albums.get(albumName);
        if(album!= null)
        {
            album.addAudio(audio);
        }
        else
        {
            Album nweAlbum = new Album(albumName);
            nweAlbum.addAudio(audio);
            albums.put(albumName,nweAlbum);
        }

    }

    public  String getName()
    {
        return  name;
    }

    public  Map<String,Album> getAlbums()
    {
        return  albums;
    }

    public  void descrribe()
    {
        System.out.println("Artist: " + getName());
        for(Map.Entry <String, Album> entry : albums.entrySet())
        {
            System.out.println("Album: " + entry.getKey() + "\nSongs: ");
            ArrayList <Audio> audios = entry.getValue().getAudios();
            for(Audio audio : audios)
            {
                System.out.println("Title: " + audio.getTitle() + ". Length: " + audio.getLengthInString() + ". Path: " + audio.getPath());
            }
        }
        System.out.println("///////////////////");
    }

    public  String describeForHtml()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<h1> Artist: " + getName() +"</h1>");
        for(Map.Entry <String, Album> entry : albums.entrySet())
        {

            stringBuilder.append("<h2> Album: " + entry.getKey() + "</h2> <h3>Songs: </h3>");
            ArrayList <Audio> audios = entry.getValue().getAudios();
            for(Audio audio : audios)
            {
                stringBuilder.append("<p><strong>Title: </strong>" + audio.getTitle() + "<strong> Length: </strong>" + audio.getLengthInString()
                        + "\t<strong>Path: </strong><a href=\" " + audio.getPath() + "\"> " + audio.getPath()+ "</a> </p>\n");
            }
        }
        return stringBuilder.toString();
    }
}