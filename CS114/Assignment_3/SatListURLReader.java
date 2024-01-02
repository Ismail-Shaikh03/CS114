import java.net.*; // URL
import java.io.*; // BufferedReader, InputStreamReader
import java.util.*; // List, ArrayList
public class SatListURLReader
{
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://celestrak.org/NORAD/elements/gp.php?GROUP=visual&FORMAT=tle");
                BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        List<Satellite> sat_list = new ArrayList<>();
// Construct Satellite Objects and add to List.
        String name, line1, line2;
        while ((name = in.readLine()) != null)
        {
            line1 = in.readLine();
            line2 = in.readLine();
            sat_list.add(new Satellite(name, line1, line2));
        }
        in.close();
// Print Satellite data from the Satellites in the List.
        System.out.printf("%8s %8s %12s %8s %9s %9s %s\n", "incline", "eccent", "mean motion", "period", "apogee", "perigee", "catalog\tname");
        for(Satellite s : sat_list) // print the Satellite Objects
            System.out.printf("%8.2f %8.6f %12.6f %8.2f %9.2f %9.2f " + s.toString() + "\n", s.inclination(), s.eccentricity(), s.mean_motion(), s.period_min(), s.apogee(), s.perigee());
    }
}