
//====================================================================
// Main Class
//

public class FeetInches_02
{
    public static void main (String[] args)
    {
        FeetInches f = new FeetInches(2, 6);
        FeetInches g = new FeetInches(3, 8);
        System.out.println(f + " + " + g + " = " + f.add(g) + '\n');
        System.out.println("f = " + f + '\n');
        f.setFeet(4);
        System.out.println("called f.setFeet(4)");
        System.out.println("f = " + f + '\n');
        System.out.println("g = " + g + '\n');
        g.setInches(7);
        System.out.println("called g.setInches(7)");
        System.out.println("f = " + f);
        System.out.println("g = " + g + '\n');
        System.out.println("f + g = " + f.add(g) + '\n');
        System.out.println("f.equals(g) returns " + f.equals(g));
        g = f;
        System.out.println("executing g = f");
        System.out.println("f.equals(g) returns " + f.equals(g));
    }
}
//===================================================
/**
 The FeetInches class holds distances measured in
 feet and inches.
 */
class FeetInches
{
private int all_inches;
//------------------------------------------------
    /**
     This constructor assigns 0 to the feet
     and inches fields.
     */
    public FeetInches(int all_inches)
    {
        this.all_inches =all_inches;
    }
//------------------------------------------------
    /**
     This constructor accepts two arguments which
     are assigned to the feet and inches fields.
     The simplify method is then called.
     @param f The value to assign to feet.
     @param i The value to assign to inches.
     */
//------------------------------------------------
    public FeetInches(int f, int i)
    {
        all_inches += (f*12)+i;

    }
//------------------------------------------------
    /**
     The following is a copy constructor. It accepts a
     reference to another FeetInches object. The feet
     and inches fields are set to the same values as
     those in the argument object.
     @param object2 The object to copy.
     */
    public FeetInches(FeetInches object2)
    {
        all_inches = object2.all_inches;
    }
//------------------------------------------------
    /**
     The setFeet method assigns a value to
     the feet field.
     @param f The value to assign to feet.
     */
    public void setFeet(int f)
    {
        all_inches = f*12+getInches();
    }
//------------------------------------------------
    /**
     The setInches method assigns a value to
     the inches field.
     @param i The value to assign to inches.
     */
    public void setInches(int i)
    {
        all_inches = i+(getFeet()*12);

    }
//------------------------------------------------
    /**
     getFeet method
     @return The value in the feet field.
     */
    public int getFeet()
    {
        return (all_inches/12);
    }
//------------------------------------------------
    /**
     getInches method
     @return The value in the inches field.
     */
    public int getInches()
    {
        return (all_inches%12);
    }
//------------------------------------------------
    /**
     toString method
     @return a reference to a String stating
     the feet and inches.
     */
    public String toString()
    {
        return (getFeet() + " ft " + (getInches()) + " in");
    }
//------------------------------------------------
    /**
     The add method returns a FeetInches object
     that holds the sum of this object and another
     FeetInches object.
     @param object2 The other FeetInches object.
     @return A reference to a FeetInches object.
     */
    public FeetInches add(FeetInches object2)
    {
        return new FeetInches(all_inches+object2.all_inches);
    }
//------------------------------------------------
    /**
     The equals method compares this object to the
     argument object. If both have the same values,
     the method returns true.
     @return true if the objects are equal, false
     otherwise.
     */
    public boolean equals(FeetInches object2)
    {
        boolean status;
        if (object2 == null)
            status = false;
        else if (all_inches==object2.all_inches)
            status = true;
        else
            status = false;
        return status;
    }
//------------------------------------------------
    /**
     The copy method makes a copy of the
     the calling object.
     @return A reference to the copy.
     */
    public FeetInches clone()
    {
// Make a new FeetInches object and
// initialize it with the same data
// as the calling object.
        FeetInches newObject =
                new FeetInches(all_inches);
// Return a reference to the new object.
        return newObject;
    }
}