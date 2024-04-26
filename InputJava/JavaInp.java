/* 
 * This is the work of...
 * 𝔗𝔥𝔢 𝔤𝔯𝔢𝔞𝔱 
 * 𝔼𝕥𝕙𝕒𝕟 𝕊𝕥𝕒𝕣𝕜-ℍ𝕣𝕪𝕟𝕜𝕚𝕨
 * April 24, 2024
 * JavaSE-11
 */
package InputJava;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>Methods to better get java input. </p>
 * <p>Automatically deals with possible type errors, </p>
 * <p>Program dictates how input gets transformed into desired type to avoid errors. </p>
 *  @author <i>Ethan Stark-Hrynkiw</i>
 *  @hidden 𝔈𝔱𝔥𝔞𝔫 𝔖𝔱𝔞𝔯𝔨-ℌ𝔯𝔶𝔫𝔨𝔦𝔴
 *  @version 1
  */
public class JavaInp {

    /**
     * <p>Dictates how to transform user input to desired type. </p>
     * 
     */
    public static interface convertToType<T> {
    
        public T Transform(String UserInp);
    }

    private static Scanner inpScanner = new Scanner(System.in);
    private final static String StandardErrorMsg = "Invalid response. Please try again.";
    
    public final static convertToType<Double> DoubleConvert = new convertToType<Double>() {
        @Override
        public Double Transform(String UserInp) {

            Double newDoub = Double.valueOf(UserInp);

            return newDoub;
        }
    };

    public final static convertToType<Integer> IntegerConvert = new convertToType<Integer>() {

        @Override
        public Integer Transform(String UserInp) {
            return Integer.valueOf(UserInp);
        }
        
    };

    public final static convertToType<String> StringConvert = new convertToType<String>() {
        @Override
        public String Transform(String UserInp) {
            return UserInp;
        }
    };

    public final static convertToType<Boolean> BooleanConvert = new convertToType<Boolean>() {
        @Override
        public Boolean Transform(String UserInp) {
            Boolean IsBool = null;
            if (UserInp.equals("true") || UserInp.equals("True")) {
                IsBool = true;
            }
            else if (UserInp.equals("false") || UserInp.equals("False")) {
                IsBool = false;
            }
            else{
                // throw error or something, to activate try-catch
                throw new Error("Not a Boolean");
            }
            return IsBool;
        }
    };


    public JavaInp(InputStream stream){
        inpScanner = new Scanner(stream);
    }
    public JavaInp(){
        
    }
    /**
     *  <p>Gets the scanners next line as string </p>
     * @return User inputed line
      */
    public static String getNextLine(){
        //Scanner inpScanner = new Scanner(System.in);
        //inpScanner.nextLine();
        String response = inpScanner.nextLine();
        //System.out.println(response);
        // inpScanner.close();
        return response;
    }

    /**
     * <p> Closes the scanner, only to be used when no more input is required.</p>
     */
    public static void close(){
        inpScanner.close();
    }

    /**
     * <p>Prints the wanted message with new line, then gets the input </p>
     * @param Message for the user
     * @return User inputed line
      */
    public static String inputln(String Message){
        System.out.println(Message);
        return getNextLine();
    }
    /**
     * <p>Gets the input, prints no message </p>
     * 
     * @return User inputed line
      */
    public static String inputln(){
        return inputln("");
    }
    /**
     * <p>Prints the wanted message, then gets the input </p>
     * @param Message for the user
     * @return User inputed line
      */
    public static String input(String Message){
        System.out.print(Message);
        return getNextLine();
    }
    /**
     * <p>Gets the input, prints no message </p>
     * 
     * @return User inputed line
      */
    public static String input(){
        return input("");
    }

    /**
     * <p> Checks if {@code ob} is within the array list {@code Arr }.</p>
     * <p> assumes {@code defaultValue} if the array list is empty.</p>
     * @param Arr array list to check within
     * @param ob object to find
     * @param defaultValue boolean to assume if empty
     * @return whether or not it was {@code ob} was found
      */
    private static boolean foundInArrayList(ArrayList<?> Arr, Object ob, boolean defaultValue){
        boolean found = defaultValue;
        if(!Arr.isEmpty()) found = Arr.contains(ob);
        //System.out.println(found);
        return found;
    }
    
    // need interface to tell how to convert from string (as option)
    //      should be seperate function
    // add limit optional
    // add valid responses - invalid responses list
    // error message
    /**
     *  <p> Automatically validates users input</p>
     * @param <T> Type of input to return
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param convert determins how to transform user input to type
     * @param validResponses array list of valid responses for the user
     * @param invalidResponses array list of in valid responses for the user
     * @param AskLimit <p> number of times to ask for input <br></br> 
     *  limits number of times to ask for imput when above 0</p>
     * @param defaultInp This value will be used if the ask limit is surpased
     *  
     * @return the validated user input
      */
    public static <T> T validateInput(String Message, String errorMessage, convertToType<T> convert, 
                                                        ArrayList<String> validResponses, ArrayList<String> invalidResponses, int AskLimit, T defaultInp){
        
        /* ArrayList<String> validResponses = new ArrayList<String>();
        ArrayList<String> invalidResponses = new ArrayList<String>();
        int AskLimit = 0;
        String Message = "";
        String errorMessage = "";
        toType<?> convert = new toType<T>() {
            @Override
            public T Transform(String UserInp) {
               
                return null;
            }
        }; */

        // no null pointer exections in my house!
        Message = ( (Message == null )?   "" : Message);
        errorMessage = ( (errorMessage == null )?   "" : errorMessage);
        ArrayList<String> emptyList = new ArrayList<String>();
        validResponses = ( (validResponses == null )?   emptyList : validResponses);
        invalidResponses = ( (invalidResponses == null )?   emptyList : invalidResponses);

        T FinalAns = defaultInp;
       
        int runs = 0;
        boolean isValid = false;
        while ( (runs < AskLimit || AskLimit <= 0) && isValid == false) {
            String inp = input(Message);
            
            // assume it's fine unless proven false
            isValid = true;
            
            isValid = !foundInArrayList(invalidResponses, inp, false); // check if in array, return false if empty or not contained
            isValid = foundInArrayList(validResponses, inp, isValid); // check if in array, return true if empty or contained

            // try to convert
            // System.out.println(" gets here");
            try{
                
                FinalAns = (T) convert.Transform(inp);
                
            }
            catch( Throwable e){
                //System.out.println(errorMessage);
                isValid = false;
            }

            if (!isValid) {
                System.out.println(errorMessage);
                FinalAns = defaultInp;
            }
            
            runs ++;
        }

        return FinalAns;
    }

    /**
     *  <p> Automatically validates users input</p>
     * @param <T> Type of input to return
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param convert determins how to transform user input to type
     * @param validResponses array list of valid responses for the user
     * @param invalidResponses array list of in valid responses for the user
     * 
     * @return the validated user input
      */
    public static <T> T validateInput(String Message, String errorMessage,  convertToType<T> convert, ArrayList<String> validResponses, ArrayList<String> invalidResponses){
        int AskLimit = 0;
         
        return validateInput(Message, errorMessage, convert, validResponses, invalidResponses, AskLimit, null);
    }

    /**
     *  <p> Automatically validates users input</p>
     * @param <T> Type of input to return
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param convert determins how to transform user input to type
     * @param validResponses array list of valid responses for the user
     * @param AskLimit <p> number of times to ask for input <br></br> 
     *  limits number of times to ask for imput when above 0</p>
     *  @param defaultInp This value will be used if the ask limit is surpased
     * 
     * @return the validated user input
      */
    public static <T> T validateInput(String Message, String errorMessage, convertToType<T> convert, ArrayList<String> validResp, int AskLimit, T defaultInp){
        ArrayList<String> validInps = new ArrayList<String>();
        
        return validateInput(Message, errorMessage, convert, validResp, validInps, AskLimit, defaultInp);
    }

    /**
     *  <p> Automatically validates users input</p>
     * @param <T> Type of input to return
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param convert determins how to transform user input to type
     * @param validResponses array list of valid responses for the user
     * 
     * @return the validated user input
      */
    public static <T> T validateInput(String Message, String errorMessage, convertToType<T> convert, ArrayList<String> validResp){
        ArrayList<String> validInps = new ArrayList<String>();
    
        
        return validateInput(Message, errorMessage, convert, validResp, validInps, 0, null);
    }
    /**
     *  <p> Automatically validates users input</p>
     * @param <T> Type of input to return
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param convert determins how to transform user input to type
     * @param AskLimit <p> number of times to ask for input <br></br> 
     *  limits number of times to ask for imput when above 0</p>
     *  @param defaultInp This value will be used if the ask limit is surpased
     * 
     * @return the validated user input
      */
    public static <T> T validateInput(String Message, String errorMessage, convertToType<T> convert, int AskLimit, T defaultInp){
        ArrayList<String> validInps = new ArrayList<String>();
        
        return validateInput(Message, errorMessage, convert, validInps, AskLimit, defaultInp);
    }
    
    /**
     *  <p> Automatically validates users input</p>
     * @param <T> Type of input to return
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param convert determins how to transform user input to type
     *  
     * @return the validated user input
      */
    public static <T> T validateInput(String Message, String errorMessage,  convertToType<T> convert){
        int AskLimit = 0;
         
        return validateInput(Message, errorMessage, convert, AskLimit, null);
    }

    /**
     *  <p> Automatically validates users input</p>
     * @param <T> Type of input to return
     * @param Message message shown to the user before input
     * @param convert determins how to transform user input to type
     *  
     * @return the validated user input
      */
    public static <T> T validateInput(String Message, convertToType<T> convert){
        
        return validateInput(Message, StandardErrorMsg, convert);
    }

    /**
     *  <p> Automatically validates users input when that input is string</p>
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param validResponses array list of valid responses for the user
     * @param invalidResponses array list of in valid responses for the user
     * @param AskLimit <p> number of times to ask for input <br></br> 
     *  limits number of times to ask for imput when above 0</p>
     *  @param defaultInp This value will be used if the ask limit is surpased
     * 
     * @return the validated user input
      */
    public static String validateInput(String Message, String errorMessage, 
                                                        ArrayList<String> validResponses, ArrayList<String> invalidResponses, int AskLimit, String defaultInp){

        String inp = validateInput(Message, errorMessage, StringConvert, validResponses, invalidResponses, AskLimit, defaultInp);

        return inp;
    }

    /**
     *  <p> Automatically validates users input when that input is string</p>
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param validResponses array list of valid responses for the user
     * @param invalidResponses array list of in valid responses for the user
     * 
     * @return the validated user input
      */
    public static String validateInput(String Message, String errorMessage, ArrayList<String> validResp, ArrayList<String> invalidResp){
        
        return validateInput(Message, errorMessage, validResp, invalidResp, 0, null);
    }

    /**
     *  <p> Automatically validates users input when that input is string</p>
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param validResponses array list of valid responses for the user
     * @param AskLimit <p> number of times to ask for input <br></br> 
     *  limits number of times to ask for imput when above 0</p>
     *  @param defaultInp This value will be used if the ask limit is surpased
     * 
     * @return the validated user input
      */
    public static String validateInput(String Message, String errorMessage, ArrayList<String> validResp, int AskLimit, String defaultInp){
        ArrayList<String> validInps = new ArrayList<String>();
        
        return validateInput(Message, errorMessage, validResp, validInps, AskLimit, defaultInp);
    }

    /**
     *  <p> Automatically validates users input when that input is string</p>
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     * @param AskLimit <p> number of times to ask for input <br></br> 
     *  limits number of times to ask for imput when above 0</p>
     *  @param defaultInp This value will be used if the ask limit is surpased
     * 
     * @return the validated user input
      */
    public static String validateInput(String Message, String errorMessage, int AskLimit, String defaultInp){
        ArrayList<String> validInps = new ArrayList<String>();
        
        return validateInput(Message, errorMessage, validInps, validInps, AskLimit, defaultInp);
    }

    /**
     *  <p> Automatically validates users input when that input is string</p>
     * @param Message message shown to the user before input
     * @param errorMessage message shown if invalid input
     *  
     * @return the validated user input
      */
    public static String validateInput(String Message, String errorMessage){
        int AskLimit = 0;
         
        return validateInput(Message, errorMessage, AskLimit, null);
    }
    /**
     *  <p> Automatically validates users input when that input is string</p>
     * @param Message message shown to the user before input
     *  
     * @return the validated user input
      */
    public static String validateInput(String Message){
         
        return validateInput(Message, StandardErrorMsg);
    }

    public static void main(String[] args){
        // examples of how to use
        String Name = inputln("Hello HUMAN, what's your name?");
        System.out.println("Welcome " + Name);

        
        ArrayList<String> validResps = new ArrayList<String>();
        validResps.add("2.718281828");
        validResps.add("2.718281828459045");
        validResps.add("5");

        ArrayList<String> invalidResps = new ArrayList<String>();
        invalidResps.add("6");
        invalidResps.add("yes");
        invalidResps.add("no");
        invalidResps.add("No");
        invalidResps.add("5"); // this is fine, valid responses are prioritized

        double UserDouble = validateInput("Hello, enter a number\n>", "There has been an error", DoubleConvert, validResps, invalidResps);
        System.out.println("You chose " + UserDouble);

        int UserInt = validateInput("What's your favorite Int?\n", "That's not an Int", IntegerConvert, 3, 5);
        System.out.println("Your int is " + UserInt);

        String FavNum = validateInput("Enter your favorite number\n", null, validResps, null);
        System.out.println("That's right! It's " + FavNum);

        String THEANSWER = validateInput("What is the answer to everything?\n", "You can't say that :/", new ArrayList<>(), invalidResps, 5, "42");
        System.out.println("I guess '" + THEANSWER + "' is right. I don't acutally know.");

        boolean THEBEST = validateInput("AM I THE BEST?\n'true' or 'false'\nyou have 5 attempts\n> ", null, BooleanConvert, 5, true);
        System.out.println("YOU THINK IT'S " + THEBEST + "!\n I don't know what to think.");
    }
}
