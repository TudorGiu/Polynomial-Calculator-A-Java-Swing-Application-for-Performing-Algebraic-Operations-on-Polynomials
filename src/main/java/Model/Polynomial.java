package Model;


import javax.swing.*;
import java.util.Iterator;
import java.util.TreeMap;

public class Polynomial {
    private TreeMap<Integer, Double> monomials = new TreeMap<>();

    public void showPolynomial(){
        for (Integer key : monomials.keySet()) {
            System.out.println("Key: " + key + ", Value: " + monomials.get(key));
        }
    }

    public void addMonomial(Integer exponent, Double coefficient){
        this.monomials.put(exponent,coefficient);
    }

    public int getBiggerExp(){
        return monomials.lastKey();
    }

    public TreeMap<Integer, Double> getMonomials() {
        return monomials;
    }

    public void clearZeros() {

        Polynomial aux = new Polynomial();
        aux.getMonomials().putAll(this.getMonomials());

        for (Integer exp : aux.getMonomials().keySet())
            if (aux.getMonomials().get(exp) == 0 && exp != 0){
                this.getMonomials().remove(exp);
            }
    }

    public String toString(){

        String string = "";

        if(this.getMonomials().isEmpty())
        {
            string = "0";
            return string;
        } else if(this.isJustZero()){
                string = "0";
        } else {
            for(Integer exp : this.getMonomials().keySet()){
                if(exp != 0 || this.getMonomials().get(0) != 0) {
                    if (this.getMonomials().get(exp) > 0)
                        string = string.concat("+");

                    String coeff;
                    if((this.getMonomials().get(exp) % 1) == 0) // if the coefficient is an integer
                        coeff = String.valueOf(this.getMonomials().get(exp).intValue());
                    else
                        coeff = String.format("%.2f", this.getMonomials().get(exp));

                    if (exp == 0)
                        string = string.concat(coeff);
                    else if (exp == 1) {
                        string = string.concat(coeff);
                        string = string.concat("x");
                    } else {
                        string = string.concat(coeff);
                        string = string.concat("x^");
                        string = string.concat(exp.toString());
                    }
                }
            }
        }

        if(string.charAt(0) == '+')
            return string.substring(1, string.length());
        else
            return string;
    }

    public boolean isJustZero(){
        if(this.getBiggerExp() == 0 && this.getMonomials().get(this.getBiggerExp()) == 0)
            return true;
        else
            return false;
    }
}
