import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Catalog {

    static final Logger logger = LogManager.getLogger(Catalog.class);
    String name;
    ArrayList<Audio> audios;
    Map<String, Artist> artists;
    Map<String, ArrayList<String>> sameAudios;
    Map<Integer, ArrayList<String>> equalHashCodesAudios;

    Catalog (String name)
    {
        this.name = name;
        audios = new ArrayList<Audio>();
        artists = new HashMap<String, Artist>();
        sameAudios =  new HashMap< String, ArrayList<String>>();
        equalHashCodesAudios =  new HashMap< Integer, ArrayList<String>>();
    }

    public void createCatalog(ArrayList<String> ways)  throws IOException, UnsupportedTagException, InvalidDataException
    {
        System.out.println("Start creating a catalog");
        for (String path: ways)
        {
            audios.addAll(PathParser.getAllAudiosFromPath(path));
        }
        for(Audio audio: audios)
        {
            Artist artist = artists.get(audio.getArtist());
            if(artist!=null)
            {
                artist.addAudio(audio);
            }
            else
            {
                artist = new Artist(audio.getArtist());
                artist.addAudio(audio);
                artists.put(audio.getArtist(),artist);
            }
        }
        System.out.println("End of catalog creation");
        System.out.println("Audios in catalog: " + audios.size());
    }
    public void createCatalog(String path)  throws IOException, UnsupportedTagException, InvalidDataException
    {
        System.out.println("Start creating a catalog");
        audios.addAll(PathParser.getAllAudiosFromPath(path));
        for(Audio audio: audios)
        {
            Artist artist = artists.get(audio.getArtist());
            if(artist!=null)
            {
                artist.addAudio(audio);
            }
            else
            {
                artist = new Artist(audio.getArtist());
                artist.addAudio(audio);
                artists.put(audio.getArtist(),artist);
            }
        }
        System.out.println("End of catalog creation");
        System.out.println("Audios in catalog: " + audios.size());
    }

    public Map<String,Artist> getArtists()
    {
        return artists;
    }

    public ArrayList<Audio> getAudios()
    {
        return audios;
    }

    public  Map<String, ArrayList<String>> getSameAudios()
    {
        return sameAudios;
    }

    public Map<Integer, ArrayList<String>> getSameHashCodesAudios()
    {
        return equalHashCodesAudios;
    }

    public void findAllSameAudios() {
        if (audios.size() != 0) {
            for (int i = 0; i < audios.size(); i++) {
                Audio firstAudio = audios.get(i);
                for (int j = i + 1; j < audios.size(); j++) {
                    Audio secondAudio = audios.get(j);
                    if (firstAudio.isSame(secondAudio)) {
                        String info = "Artist: " + firstAudio.getArtist() + " Album: " + firstAudio.getAlbum()
                                + " Title: " + firstAudio.getTitle();
                        if (sameAudios.get(info) != null
                                && !sameAudios.get(info).contains(firstAudio.getPath())) {
                            sameAudios.get(info).add(firstAudio.getPath());
                        } else if (sameAudios.get(info) != null
                                && !sameAudios.get(info).contains(secondAudio.getPath())) {
                            sameAudios.get(info).add(secondAudio.getPath());
                        } else {
                            ArrayList<String> path = new ArrayList<String>();
                            path.add(firstAudio.getPath());
                            path.add(secondAudio.getPath());
                            sameAudios.put(info, path);
                        }
                    }
                }

            }
        } else {
            System.out.println("Catalog is empty");
        }
    }

    public void findAllEqualHashCodesAudios() {
        if (audios.size() != 0) {
            for (int i = 0; i < audios.size(); i++) {
                Audio firstAudio = audios.get(i);
                for (int j = i + 1; j < audios.size(); j++) {
                    Audio secondAudio = audios.get(j);

                    if (firstAudio.hashCode() == audios.get(j).hashCode()) {
                        int hashCode = firstAudio.hashCode();
                        if (equalHashCodesAudios.get(hashCode) != null
                                && !equalHashCodesAudios.get(hashCode).contains(firstAudio.getPath())) {
                            equalHashCodesAudios.get(hashCode).add(firstAudio.getPath());
                        } else if (equalHashCodesAudios.get(hashCode) != null
                                && !equalHashCodesAudios.get(hashCode).contains(secondAudio.getPath())) {
                            equalHashCodesAudios.get(hashCode).add(secondAudio.getPath());
                        } else {
                            ArrayList<String> path = new ArrayList<String>();
                            path.add(firstAudio.getPath());
                            path.add(secondAudio.getPath());
                            equalHashCodesAudios.put(hashCode, path);
                        }
                    }

                }

            }
        } else {
            System.out.println("Catalog is empty");
        }
    }

    public void logAllSameAudio() {
        if (sameAudios.size() != 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Same audios \n");
            for (Map.Entry<String, ArrayList<String>> entry : sameAudios.entrySet()) {

                String key = entry.getKey();
                builder.append(key + "\n");
                ArrayList<String> list = entry.getValue();
                for (String path : list) {
                    // System.out.println(path);
                    builder.append(path + "\n");
                }
                builder.append("------------------------------------------------------------------------------------\n");

            }
            logger.info(builder.toString());
        } else {
            logger.info("Map of same audios is empty");
        }
    }

    public void logEqualHashCodesAudios() {
        if (equalHashCodesAudios.size() != 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Equal hash codes \n");
            int iterator = 0;
            for (ArrayList<String> list : equalHashCodesAudios.values()) {
                iterator++;
                builder.append("Duplicate #" + iterator + "\n");
                for (String path : list) {
                    builder.append(path + "\n");
                }
            }
            logger.info(builder.toString());
        } else {
            logger.info("Map of equal hash codes is empty");
        }
    }

    public String describeForHtml()
    {

        StringBuilder builder = new StringBuilder();
        if(artists.size()!=0) {
            for (Artist artist : artists.values()) {
                builder.append(artist.describeForHtml());
            }
        }
        else
        {
            builder.append("<h3>Empty!</h3>");
        }
        return builder.toString();
    }
}
