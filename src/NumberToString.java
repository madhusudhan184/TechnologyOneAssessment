

import java.util.Scanner;

public class NumberToString {
	
	// String output; 
	
	public static void main(String[] args) {
		
		try (
			// Take input from user via Scanner obj
			Scanner sc = new Scanner(System.in)) {
			// Read number from user
			System.out.print("Enter the number: ");
			float inputNumber = sc.nextFloat();
			
			int integerPart = (int) inputNumber; 
			float fractionalPart = inputNumber - integerPart;
			String fractionString = "";
			
			String integerString = ConvertNumberToWords(integerPart);
			if(isFractional(inputNumber)) {
				String formattedValue = String.format("%.2f", fractionalPart); 
				//System.out.println("-->"+Float.parseFloat(formattedValue.substring(2)));	    	
				fractionString = "and " +ConvertNumberToWords(Float.parseFloat(formattedValue.substring(2))) +" Cents";
				
			}
			// Print output
			System.out.println(integerString + " Dollars " + fractionString);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	    
	} // main() ends
	
	// Check if entered number is decimal ?
	static boolean isFractional(float num) {
        return num != (int) num; 
    }
	
	// function to covert number to words
	static String ConvertNumberToWords(float num) {
		
		String words= "";
		
		String[] unitsWord = { "Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", 
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
		String[] tensWord = { "Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
		
		// number is zero
		if (num == 0) {
			return "zero";
		}
		// number is positive
		if(num > 0) {
			//System.out.println("Number is : "+num);
		    if (words != "") {
		    	words += "and ";
	            //System.out.println("Words not empty : "+words);
            }
		    
            if (num < 20) {            	
                words += unitsWord[(int) num];
                //System.out.println("Num less than 20 : "+words);
            }	
            else  // > 20
            {	
            	if( (num>99999) &&  (num/1000000) > 0)
                {
                    words += ConvertNumberToWords(num / 1000000) + " Million ";
                    num %= 1000000;
                }
            	
                if( (num>999) && (num/1000) > 0)
                {
                    words += ConvertNumberToWords(num / 1000) + " Thousand ";
                    num %= 1000;
                }
            	
            	if( (num>99) && ((num/100)>0) )            		
                {
                    words += ConvertNumberToWords(num / 100) + " Hundred ";
                    num %= 100;
                    //System.out.println("Number in Hundreds -: "+words);
                    //System.out.println("Number in 100s  -: "+num);                    
                }            	
            	                
                if( (num > 19) && (num / 10 > 0) ) {
                	if(words != "")
                		words += "and "; 
                	words += tensWord[(int) (num / 10)];
                	num %= 10;
                    //System.out.println("Num greater than 20 : "+words);
                    //System.out.println("Num 10s : "+num);
                }
                if(num > 0 ) {
                	
                	words += "-"+ unitsWord[(int) (num)];
                    //System.out.println("Num Units : "+words);                    
                }
            }
	        			
		}
		
		// number is negative
		if (num < 0) {
        	return "Minus "+ ConvertNumberToWords(Math.abs(num));
        }
		
		return words.trim();
	}
	
	

}
