public class Satellite {
    private String name,line1,line2;

    public Satellite(String name, String line1, String line2){

        this.name=name;
        this.line1=line1;
        this.line2=line2;
    }
    public static int year_4_digit(int year_2_digit){
        if(year_2_digit>=57 && year_2_digit<=99){
            return 1900+year_2_digit;
        }
        else{
            return 2000+year_2_digit;
        }
    }
    public int launch_year_yy(){
        int year_2_digit=Integer.parseInt(line1.substring(9,11));
        return year_2_digit;
    }
    public int launch_year_yyyy(){
        int last_digits=Integer.parseInt(line1.substring(9,11));
        if(last_digits>=57 && last_digits<=99){
            return 1900+last_digits;
        }
        else{
            return 2000+last_digits;
        }
    }
    public String name(){
        return name;
    }
    public String catalog_number(){
        return line1.substring(2,7);
    }
    public double mean_motion(){
        return Double.parseDouble(line2.substring(52,63));
    }
    public double inclination(){
        return Double.parseDouble(line2.substring(8,16));
    }
    public double eccentricity(){
        return (Double.parseDouble(line2.substring(26,33))/10000000.0);
    }
    public double period_min(){
        return (24.0*60.0)/(Double.parseDouble(line2.substring(52,63)));
    }
    public double apogee(){
        double a=Math.pow((8681663.653/mean_motion()),(2.0/3.0));
        return a* (1+eccentricity()) -6371.0;
    }
    public double perigee(){
        double a=Math.pow((8681663.653/mean_motion()),(2.0/3.0));
        return a * (1-eccentricity()) -6371.0;
    }
    public String toString(){
        return catalog_number()+"  "+name();
    }

}
