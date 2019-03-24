import java.io.*;
public class HtmlCreator
{
    private static String header = "<!DOCTYPE HTML>\n" + "<html>\n" + "<head>\n" +
            "  <meta charset=\"utf-8\">\n" +
            "  <title>Output info</title>\n" +
            " </head>\n" +
            " <body>\n" ;

    private static String footer ="</body>\n" +
            "</html>";

    private static  StringBuilder body = new StringBuilder();

    public static void createHtmlFile (String name) throws  IOException
    {
        File htmlFile = new File (name);
        BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
        writer.write(header);
        writer.write(body.toString());
        writer.write(footer);
        writer.close();
    }
    public static void addToBody(String info)
    {
        body.append(info + "\n");
    }

    public static void clearBody()
    {
        body = new StringBuilder();
    }

    public static  String getBody()
    {
        return body.toString();
    }

}