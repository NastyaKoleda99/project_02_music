import com.mpatric.mp3agic.*;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class PathParser
{
    private static ArrayList<File> files = new ArrayList<File>();
    private static ArrayList<File> mp3Files = new ArrayList<File>();

    //Функция получения информации об аудио
    public  static Audio getAudioInfo(File file) throws IOException,UnsupportedTagException,InvalidDataException
    {
        Mp3File mp3File =  new Mp3File(file);
        long length =  mp3File.getLengthInSeconds();
        String title ="No title is here!", album = "No album is here!", artist= "No artist is here!",path;
        path = file.getPath();


        if (mp3File.hasId3v2Tag())
        {
            ID3v2 id3v2Tag = mp3File.getId3v2Tag();
            if(id3v2Tag.getTitle()!=null && !id3v2Tag.getTitle().equals("")) {
                title= id3v2Tag.getTitle();
            }
            else
            {
                title = "No title is here!";
            }
            if(id3v2Tag.getArtist()!=null && !id3v2Tag.getArtist().equals("") ) {
                artist= id3v2Tag.getArtist();
            }
            else
            {
                artist = "No artist is here!";
            }
            if(id3v2Tag.getAlbum()!=null && !id3v2Tag.getAlbum().equals("")) {
                album= id3v2Tag.getAlbum();
            }
            else
            {
                album = "No album is here!";
            }

        }
        Audio audio = new Audio(title,artist,album,length,path);
        return  audio;
    }

    //Функция получения всех mp3 аудио c информацией в данной директории
    public static  ArrayList<Audio> getAllAudiosFromPath(String path) throws IOException,UnsupportedTagException,InvalidDataException
    {
        ArrayList<Audio> audios = new ArrayList<Audio>();
        File defaultPath = new File(path);
        mp3Files = new ArrayList<File>();
        getAllMp3FilesInDirectory(defaultPath);
        for (File file : mp3Files)
        {
            Audio audio = getAudioInfo(file);
            audios.add(audio);
        }

        return audios;
    }
    //Функция получения всех файлов в директории
    public static void getAllFilesInDirectory(File file)
    {
        for (File fileInDir : file.listFiles()) {
            if (fileInDir.isFile()) {
                files.add(fileInDir);
            } else if (fileInDir.isDirectory()) {
                getAllFilesInDirectory(fileInDir);
            }
        }
    }

    /* public static void getAllMp3FilesInDirectory(File file)
    {
        for (File fileInDir : file.listFiles()) {
            if (fileInDir.isFile() && getExtension(currentFile).equals(".mp3")) {
                mp3Files.add(fileInDir);
            } else if (fileInDir.isDirectory()) {
                getAllMp3FilesInDirectory(fileInDir);
            }
        }
    }*/

    //Функция получения всех mp3 файлов в директории
    public static void getAllMp3FilesInDirectory(File file)
    {
        Queue<File> fileTree = new PriorityQueue<File>();

        Collections.addAll(fileTree, file.listFiles());

        while (!fileTree.isEmpty())
        {
            File currentFile = fileTree.remove();
            if(currentFile.isDirectory()){
                Collections.addAll(fileTree, currentFile.listFiles());
            } else {
                if (getExtension(currentFile).equals(".mp3")) {
                    mp3Files.add(currentFile);
                }
            }
        }
    }
    //Функция получения расширения файла
    public  static String getExtension(File file)
    {
        String extension = "";

        if(file != null && file.exists())
        {
            String name = file.getName();
            extension = name.substring((name.lastIndexOf(".")));
        }
        return extension;
    }


}
