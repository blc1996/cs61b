/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class Nuke2 {
    
    public static void main(String[] arg) throws Exception {
        
        String in;
        BufferedReader keyboard;
        
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        in = keyboard.readLine();
        
        in = in.substring(0,1) + in.substring(2);
        System.out.print(in);
      
        
    }
}
