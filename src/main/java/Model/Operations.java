package Model;
public class Operations {
    public static TwoOrOnePolynomials addition(Polynomial pol1, Polynomial pol2){

        Polynomial result = new Polynomial();

        Polynomial bigger, smaller;
        if(pol1.getBiggerExp() > pol2.getBiggerExp()){
            bigger = pol1;
            smaller = pol2;
        }else{
            bigger = pol2;
            smaller = pol1;
        }

        for (int i = 0 ; i <= bigger.getBiggerExp() ; i++) {
            if(bigger.getMonomials().containsKey(i) && smaller.getMonomials().containsKey(i)) {
                result.getMonomials().put(i, bigger.getMonomials().get(i) + smaller.getMonomials().get(i));
            } else if (bigger.getMonomials().containsKey(i) && !smaller.getMonomials().containsKey(i)) {
                result.getMonomials().put(i, bigger.getMonomials().get(i));
            } else if (!bigger.getMonomials().containsKey(i) && smaller.getMonomials().containsKey(i)) {
                result.getMonomials().put(i, smaller.getMonomials().get(i));
            }
        }
        result.clearZeros();

        return new TwoOrOnePolynomials(result);
    }

    public static TwoOrOnePolynomials subtraction(Polynomial pol1, Polynomial pol2){

        Polynomial result = new Polynomial();

        Polynomial bigger, smaller;
        if(pol1.getBiggerExp() > pol2.getBiggerExp()){
            bigger = pol1;
            smaller = pol2;
        }else{
            bigger = pol2;
            smaller = pol1;
        }

        for (int i = 0 ; i <= bigger.getBiggerExp() ; i++) {
            if(bigger.getMonomials().containsKey(i) && smaller.getMonomials().containsKey(i)) {
                result.getMonomials().put(i, pol1.getMonomials().get(i) - pol2.getMonomials().get(i));
            } else if (bigger.getMonomials().containsKey(i) && !smaller.getMonomials().containsKey(i)) {
                if(bigger == pol1)
                    result.getMonomials().put(i, bigger.getMonomials().get(i));
                else if(bigger == pol2)
                    result.getMonomials().put(i, -bigger.getMonomials().get(i));
            } else if (!bigger.getMonomials().containsKey(i) && smaller.getMonomials().containsKey(i)) {
                if(smaller == pol1)
                    result.getMonomials().put(i, smaller.getMonomials().get(i));
                else if(smaller == pol2)
                    result.getMonomials().put(i, -smaller.getMonomials().get(i));
            }
        }
        result.clearZeros();

        return new TwoOrOnePolynomials(result);
    }

    public static TwoOrOnePolynomials multiplication(Polynomial pol1, Polynomial pol2){

        Polynomial result = new Polynomial();

        for(Integer exp1 : pol1.getMonomials().keySet())
            for(Integer exp2 : pol2.getMonomials().keySet()){
                // System.out.println(pol1.getMonomials().get(exp1) + "   " + pol2.getMonomials().get(exp2));
                Integer expSum = exp1 + exp2;
                Double coefProduct = pol1.getMonomials().get(exp1) * pol2.getMonomials().get(exp2);
                if(result.getMonomials().containsKey(expSum))
                    result.getMonomials().put(expSum, result.getMonomials().get(expSum) + coefProduct);
                else
                    result.getMonomials().put(expSum, coefProduct);
            }
        result.clearZeros();

        return new TwoOrOnePolynomials(result);
    }

    public static TwoOrOnePolynomials division(Polynomial pol1, Polynomial pol2) throws DivisionByZeroException {

        Polynomial quotient = new Polynomial();
        Polynomial remainder;

        Polynomial dividend;
        Polynomial divider;

        Polynomial partOfQuotient = new Polynomial();

        if(pol1.getBiggerExp() >= pol2.getBiggerExp()){
            dividend = pol1;
            divider = pol2;
        }
        else {
            dividend = pol2;
            divider = pol1;
        }


        if(divider.getMonomials().isEmpty() || divider.isJustZero())
            throw new DivisionByZeroException();

        while(dividend.getBiggerExp() >= divider.getBiggerExp()){
            Integer dividendFirstMonomialExp = dividend.getMonomials().lastKey();
            Double dividendFirstMonomialCoeff = dividend.getMonomials().get(dividendFirstMonomialExp);

            Integer dividerFirstMonomialExp = divider.getMonomials().lastKey();
            Double dividerFirstMonomialCoeff = divider.getMonomials().get(dividerFirstMonomialExp);

            quotient.getMonomials().put(dividendFirstMonomialExp-dividerFirstMonomialExp, dividendFirstMonomialCoeff/dividerFirstMonomialCoeff);

            partOfQuotient.getMonomials().clear();
            partOfQuotient.addMonomial(dividendFirstMonomialExp-dividerFirstMonomialExp, dividendFirstMonomialCoeff/dividerFirstMonomialCoeff);

            Polynomial aux = Operations.multiplication(partOfQuotient, divider).getPol1();
            dividend = Operations.subtraction(dividend, aux).getPol1();

            if(dividend.getMonomials().isEmpty() || dividend.isJustZero())
                break;
        }

        remainder = dividend;

        return new TwoOrOnePolynomials(quotient, remainder);
    }

    public static TwoOrOnePolynomials differentiation(Polynomial pol){

        Polynomial result = new Polynomial();

        for(Integer iterator : pol.getMonomials().keySet())
           if(iterator != 0)
               result.getMonomials().put(iterator-1, pol.getMonomials().get(iterator) * iterator);

        return new TwoOrOnePolynomials(result);
    }

    public static TwoOrOnePolynomials integration(Polynomial pol) {

        Polynomial result = new Polynomial();

        for(Integer exp : pol.getMonomials().keySet()){
            result.getMonomials().put(exp+1, pol.getMonomials().get(exp)/(exp+1));
        }

        result.clearZeros();

        return new TwoOrOnePolynomials(result, true);
    }
}
