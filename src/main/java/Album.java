import java.util.ArrayList;

public class Album
{
    private String name;
    private ArrayList<Audio> audios = new ArrayList<Audio>();

    Album (String name)
    {
        this.name = name;
    }

    Album (String name, ArrayList<Audio> audios)
    {
        this.name = name;
        this.audios.addAll(audios);
    }

    public String getName()
    {
        return name;
    }

    public  ArrayList<Audio> getAudios()
    {
        return audios;
    }

    public  void addAudio(Audio audio)
    {
        audios.add(audio);
    }


}