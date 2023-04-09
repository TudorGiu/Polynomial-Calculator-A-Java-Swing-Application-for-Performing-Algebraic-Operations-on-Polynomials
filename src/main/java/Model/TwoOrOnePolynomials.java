package Model;

public class TwoOrOnePolynomials {

    private boolean hasConstant = false;
    private Polynomial pol1;
    private Polynomial pol2;

    TwoOrOnePolynomials(Polynomial pol1, Polynomial pol2){
        this.pol1 = pol1;
        this.pol2 = pol2;
    }

    TwoOrOnePolynomials(Polynomial pol1){
        this.pol1 = pol1;
        this.pol2 = null;
    }

    TwoOrOnePolynomials(Polynomial pol1, boolean hasConstant){
        this.pol1 = pol1;
        this.pol2 = null;
        this.hasConstant = hasConstant;
    }

    public Polynomial getPol1() {
        return pol1;
    }

    public Polynomial getPol2() {
        return pol2;
    }

    public String toString(){

        String string = "";

        if(pol2 == null){

            string = pol1.toString();

            if(hasConstant == true){
                string = string.concat(" + constant");
            }
        }else {
            string = string.concat("<html>Quotient: ");
            string = string.concat(pol1.toString());
            string = string.concat("<br/>");
            string = string.concat("Remainder: ");
            string = string.concat(pol2.toString());
            string = string.concat("</html>");
        }

        return string;
    }
}
