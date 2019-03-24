import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static  ArrayList<String> pathes = new ArrayList<String>();
    final String separator = File.separator;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Start of the program");
        System.out.println("Enter some path,and if you want to finish input, enter in the new line word \"Done.\"  ");
        String input = "";
        while (!input.equals("Done."))
        {
            input = scanner.nextLine();
            input.replace("\\","/");
            if(!input.equals("Done."))
            pathes.add(input);

        }
        try
        {
            Catalog catalog = new Catalog("My first catalog");
            catalog.createCatalog(pathes);
            HtmlCreator.addToBody(catalog.describeForHtml());
            HtmlCreator.createHtmlFile("exit.html");
            catalog.findAllSameAudios();
            catalog.findAllEqualHashCodesAudios();
            catalog.logAllSameAudio();
            catalog.logEqualHashCodesAudios();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("End of the program.");


    }
}

