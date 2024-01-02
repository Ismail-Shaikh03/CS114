import java.net.*;
import java.io.*;

public class URLReader {
    public static void main(String[] args) throws Exception {

        URL oracle = new URL("https://celestrak.org/NORAD/elements/gp.php?GROUP=visual&FORMAT=tle");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String name;
        String tle1;
        String tle2;
        while ((name = in.readLine()) != null&&(tle1= in.readLine())!=null&&(tle2= in.readLine())!=null) {
            ///System.out.println(name); Added to check raw data
            ///System.out.println(tle1); Added to check raw data
            ///System.out.println(tle2); Added to check raw data
            String catalog_number=tle1.substring(2,7);
            int launch_year= Integer.parseInt(tle1.substring(9,11));
            double orbital_inclination=Double.parseDouble(tle2.substring(8,16));
            System.out.printf("Catalog_Number:%s   Launch_Year:%d    Orbital_inclination:%f   Name:%s %n",catalog_number,launch_year,orbital_inclination,name);
        }
        in.close();

}}