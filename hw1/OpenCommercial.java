/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class OpenCommercial {
    
    /** Prompts the user for the name X of a company (a single string), opens
     *  the Web site corresponding to www.X.com, and prints the first five lines
     *  of the Web page in reverse order.
     *  @param arg is not used.
     *  @exception Exception thrown if there are any problems parsing the
     *             user's input or opening the connection.
     */
    public static void main(String[] arg) throws Exception {
        
        BufferedReader keyboard;
        String inputLine;

        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter the name of a company (without spaces): ");
        System.out.flush();        /* Make sure the line is printed immediately. */
        inputLine = keyboard.readLine();
        inputLine = "http://www." + inputLine + ".com/";
        //System.out.print(inputLine);
        URL oracle = new URL(inputLine);
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        
        String content1;
        String content2;
        String content3;
        String content4;
        String content5;
       // while ((content = in.readLine()) != null)
        content1 = in.readLine();
        content2 = in.readLine();
        content3 = in.readLine();
        content4 = in.readLine();
        content5 = in.readLine();
        
        System.out.println(content5);
        System.out.println(content4);
        System.out.println(content3);
        System.out.println(content2);
        System.out.println(content1);
        in.close();
        /* Replace this comment with your solution.  */
        
    }
}
