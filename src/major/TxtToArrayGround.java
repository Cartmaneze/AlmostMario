package major;

import java.io.*;
import java.util.ArrayList;


/**
 * Created by Никита on 14.07.2016.
 */
public class TxtToArrayGround
{
    public ArrayList<String[]> stringsGetInf() throws IOException
    {
        ArrayList<String> strings = new ArrayList<String>();

        InputStream is = getClass().getResourceAsStream("/levels/level1.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null)
        {
            strings.add(line);
            line = "";
        }
        br.close();
        isr.close();
        is.close();

        ArrayList<String[]> exitStrings = new ArrayList<String[]>();

        for (String s : strings)
        {
            char[] chars = s.toCharArray();
            String[] strings1 = new String[chars.length];
            for (int i=0 ; i<chars.length ; i++)
            {
                strings1[i] = String.valueOf(chars[i]);
            }

            exitStrings.add(strings1);
        }

        return exitStrings;
    }
}
