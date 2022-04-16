package application;

import java.util.EmptyStackException;
import java.util.Stack;

public class Parser {
	
	private static boolean estX(char c) {
		return c == 'x';
	}
	
	private static boolean estPi(String s) {
		return s.equals("pi") || s.equals("Pi") || s.equals("PI");
	}
 
    private static boolean estChiffre(char c) {
    	return Character.isDigit(c) || c == '.' || c == ',';
    }

    private static boolean estDouble(String s) {
    	if(s.isEmpty()) {
    		return false;
    	}
    	try {
    		Double.parseDouble(s);
    	}
    	catch(NumberFormatException e) {
    		return false;
    	}
    	return true;
    }
   
    private static boolean estChiffre(String s) {
    	for(int i = 0; i<s.length(); i++) {
    		
    		if(!estChiffre(s.charAt(i))) {
    			return false || estDouble(s);
    		}
    	} return true;
    	

    }
    
    private static boolean estOpTrigo(String s) {
    	return s.equals("cos") || s.equals("sin") ||
    			s.equals("tan");
    }
    
    private static boolean estOpTrigoInv(String s) {
    	return s.equals("acos") || s.equals("asin") ||
    			s.equals("atan");
    }
    
    private static boolean estLn(String s) {
    	return s.equals("ln");
    }
    
    private static boolean estLog(String s) {
    	return s.equals("log");
    }
    
    private static boolean estExp(String s) {
    	return s.equals("exp");
    }
    
    private static boolean estSqrt(String s) {
    	return s.equals("sqrt");
    }
    
    private static boolean estCbrt(String s) {
    	return s.equals("cbrt");
    }
    
    public static boolean estAbs(String s) {
    	return s.equals("abs");
    }

    private static boolean estFonction(String s) {
    	return estOpTrigo(s) || estLn(s) || estExp(s)
    			|| estAbs(s) || estSqrt(s) || estLog(s) || estOpTrigoInv(s) || estCbrt(s);
    }
    
    private static String ajouteSigneFois(String expr, int i) {
		return expr.substring(0, i+1)+"*"+expr.substring(i+1, expr.length());
    }
    
    private static boolean estDebutChiffreNeg(String s) {
    	return s.equals("(-");
    }
    
    private static boolean estOpArithm(char c) {
    	return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
   
    //espacer correctement la formule rentree pour pas confondre chiffres et entiers
    
    private static String exprEspacee(String expr) {
    	String res = "";
    	for(int i = 0; i<expr.length(); i++) {
    		
    		if(i<expr.length()-1) {
    			if(estChiffre(expr.charAt(i)) && estX(expr.charAt(i+1))) {
    				expr = ajouteSigneFois(expr,i);
    			}
    		}

    		if(!estChiffre(expr.charAt(i))) {
    			if(i == 0 && expr.charAt(i) == '-') {
    				res += "-";	
    			}
    			
    			else if(i != 0 && i<expr.length()-2 && estDebutChiffreNeg(expr.substring(i-1,i+1))) {
    				res+="-";
    				
    			}
    			else if(i<expr.length()-2 && estPi(expr.substring(i,i+2))) {
    				res += " "+expr.substring(i, i+2)+" ";
    				i+= 1;
    			}
    			
    			else if(i<expr.length()-2 && estLn(expr.substring(i,i+2))) {
    				res += " "+expr.substring(i,i+2)+" ( ";
    				i+=2;
    			}
    			else if(i<expr.length()-3 && estFonction(expr.substring(i, i+3))) {
        			
        				res += " "+expr.substring(i, i+3)+" ( ";
        				i+=3;
        		}
    			
    			else if(i<expr.length()-4 && 
    					(estSqrt(expr.substring(i, i+4)) 
    							|| estCbrt(expr.substring(i,i+4)) || estOpTrigoInv(expr.substring(i, i+4)) 
    					)) {
        				res += " "+expr.substring(i, i+4)+" ( ";
        				i+=4;
        		}
        			
        		
    			else if(i<expr.length()-4 && (estSqrt(expr.substring(i, i+4)))) {
        				res += " "+expr.substring(i, i+4)+" ( ";
        				i+=4;
        		}
        		 
    			else if(estX(expr.charAt(i)) || estOpArithm(expr.charAt(i)) 
    					|| expr.charAt(i) == '(' || expr.charAt(i) == ')')
        		res += " "+expr.charAt(i)+" ";
    			
    		}
    	
    		else {
    			if(expr.charAt(i) == ',') {
    				res += '.';
    			}
    			
    			else {
    				res += expr.charAt(i) ;
    			}
    		}
    	}
    	return res;
    }
    
    //Ordre des fonctions a rajouter si utile
    private static int OrdreDesOperations(String s) {
    	if(s.equals("+") || s.equals("-")) {
    		return 1;
    	}
    	else if(s.equals("*") || s.equals("/")) {
    		return 2;
    	}
    	else if(s.equals("^")) {
    		return 3;
    	}
    	else if(estFonction(s)) {
    		return 4;
    	}
        else
            return -1;
    }
    
    private static String postfixe(String expr) {
        Stack<String> stack = new Stack<String>();
        String [] liste = expr.split(" ");
        String output = new String("");
        try {
        for(String s : liste) {
        	if(estChiffre(s) || s.equals("x") || estPi(s)) {
        		output += s+" ";
        	}
        	
            else if (s.equals("("))
                stack.push(s);
        	
            else if (s.equals(")")) {   // on pop et on ajoute jusqu'a trouver le premiere parenthese fermante
                while (!stack.isEmpty()
                        && !stack.peek().equals("("))
                    output += stack.pop()+" ";

                stack.pop();
            }
        
        	else {
        		while (
                    !stack.isEmpty() && 
                    OrdreDesOperations(s) <= OrdreDesOperations(stack.peek())) {
                output += stack.pop()+" ";
        		}
        		stack.push(s);
        	}
        	

        }
        
        while (!stack.isEmpty()) { 
        	// enlever tous les operateurs restants de la pile et les ajouter au resultat
            if (stack.peek().equals("("))
                return "Expression invalide";
            output += stack.pop()+" ";
        }
        }
        catch(EmptyStackException e) {
        	
        }
    
    	return output;
    }

    private static double evaluatePostfix(String exp,double val) {
        String [] liste = exp.split(" ");
        Stack<Double> stack=new Stack<Double>();
        
        for(String s : liste) {
            if(estChiffre(s)) {
              try {
                stack.push(Double.parseDouble(s));
              }
              catch(NumberFormatException e) {
            	  
              }
            }
            else if(s.equals("x")) {
              stack.push(val);
            }
            else if(estPi(s)) {
            	stack.push(Math.PI);
            }
            
            else if(estFonction(s)) { //resultat en degré
           
        		try {
        			double val1 = stack.pop();
        			switch(s) {
        			case "cos":
        				stack.push(Math.cos(val1));
        				break;
        			case "sin":
        				stack.push(Math.sin(val1));
        				break;
        			case "tan":
        				stack.push(Math.tan(val1));
        				break;
        				
        			case "acos":
        				stack.push(Math.acos(val1));
        				break;
        			case "asin":
        				stack.push(Math.asin(val1));
        				break;
        			case "atan":
        				stack.push(Math.atan(val1));
        				break;
        			
        			case "ln":
        				stack.push(Math.log(val1));
        				break;
        				
        			case "log":
        				stack.push(Math.log10(val1));
        				break;
        				
        			case "exp":
        				stack.push(Math.exp(val1));
        				break;
        			
        			case "abs" :
        				stack.push(Math.abs(val1));
        				break;
        				
        			case "sqrt":
        				stack.push(Math.sqrt(val1));
        				break;
        				
        			case "cbrt":
        				stack.push(Math.cbrt(val1));
        				break;
        			}
        		}
        		catch(EmptyStackException e) {
        			
        		}
            }

            else
            {
            	try {
                double val1 = stack.pop();
                double val2 = stack.pop();
                switch(s)
                {
                    case "+":
                    stack.push(val2+val1);
                    break;
                    
                    case "-":
                    stack.push(val2- val1);
                    break;
                    
                    case "/":
                    
                    	stack.push(val2/val1);
                    
                    break;
                    
                    case "*":
                    stack.push(val2*val1);
                    break;
                    
                    case "^":
                    stack.push(Math.pow(val2,val1));
                    break;

                }
            	}
                catch(EmptyStackException e) {
                	
                }
            }
        }
        return stack.pop();    
    }
    
    public static double parser(String expr,double x) {
    	return evaluatePostfix(postfixe(exprEspacee(expr)),x);
    	
    }


}
