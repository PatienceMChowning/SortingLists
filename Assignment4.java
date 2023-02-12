/** 
* Author: Patience Chowning
* Section: A
* November 23rd, 2022
* This code reads an input file and reacts accordingly to the users request.
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Assignment4 {
    //Initialize global input scanner object & boolean values
    private static Scanner in = new Scanner(System.in); 
    private static boolean loaded = false;
    private static boolean sorted = false;
    
    public static void main(String[] args) throws FileNotFoundException {
        //Declare and initialize global ArrayList & ArrayList copy
        ArrayList<Shipping> lst = new ArrayList<Shipping>();
        ArrayList<Shipping> lstCopy = new ArrayList<Shipping>(); 
 
        //Declare and initialize option
        int option = 0;      
           
        do { 
            menu();
            option = in.nextInt();
            //Creating if statement(s) for when the user enters an num
            if (option == 1) {
                loadFile(lst);
                lstCopy = clone(lst);
            }
            if (option == 6) {
                System.out.printf("End!");  
            } else if (option == 2 && loaded) { 
                lstDisplay(lst);
            } else if (option == 3 && loaded) {
                sort1(lstCopy);
            } else if (option == 4 && loaded) {
                sort2(lstCopy);
            } else if (!loaded) {
                System.out.printf("No data has been loaded yet!\n");
            } else if (option == 5 && !sorted) {
                System.out.printf("Nothing sorted yet! \n");
            } else if (option == 5 && sorted) {
                lstDisplay(lstCopy);
            }
        } while (option != 6); 
    } //End of main 

    /**
    * Prints a menu with options on the display. 
    */
    public static void menu() {     
        System.out.printf("1. Load from a file \n");
        System.out.printf("2. Print from the loaded list \n");
        System.out.printf("3. Sort the list based on shipment IDs \n");
        System.out.printf("4. Sort the list based on the tracking numbers \n");
        System.out.printf("5. Print the sorted list \n");
        System.out.printf("6. Exit \n");
        //Prompt user to enter a num
        System.out.printf("Enter a number [1-6]: ");      
    } //End of menu
    
    /**
    * Accepts an array of Shipping objects from an input file,
    * and adds objects to an ArrayList.
    * @param int object transportID.
    * @param int object shipmentID.
    * @param String object recieverName.
    * @param String object trackingNumber.
    */
    public static void loadFile(ArrayList<Shipping> lst) 
        throws FileNotFoundException {
        lst.clear();
        loaded = false;
        sorted = false;
        String fileName = " ";
        System.out.printf("Enter the name of the file: ");
        fileName = in.next();
        //To read from the file
        Scanner fileReader = new Scanner(new File(fileName));
        while (fileReader.hasNext()) {
            Shipping obj = new Shipping(fileReader.nextInt(),
                fileReader.nextInt(), 
                fileReader.next(), fileReader.next());
            lst.add(obj);
        }     
        loaded = true;
        System.out.println("Loading from the file is done! \n");
        fileReader.close();
    } //End of loadFile 
    
    /**
    * Accepts an array of Shipping objects 
    * from an input file, and displays 10 objects at a time.
    * @param Shipping object lst.  
    */
    public static void lstDisplay(ArrayList<Shipping> lst) {
        loaded = true;
        sorted = false;
        System.out.printf("**** Printing the list ****\n");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(lst.get(i));
            if ((i + 1) % 10 == 0) {
                System.out.printf("Enter something to continue"
                    + "/enter s to stop \n");
                String str = "s"; 
                if (in.next().toLowerCase().equals(str)) {
                    break;
                }
            }
        } 
        System.out.printf("\n");
    } //End of lstDisplay   
    
    /**
    * Accepts an array of Shipping objects, and creates a clone.
    * @param Shipping object lst.  
    * @return ArrayList lstCopy.
    */
    public static ArrayList<Shipping> clone(ArrayList<Shipping> lst) {
        loaded = true;
        sorted = false;
        ArrayList<Shipping> lstCopy = new ArrayList<Shipping>();
        for (int i = 0; i < lst.size(); i++) {
            lstCopy.add(lst.get(i));
        }
        return lstCopy;
    } //End of clone

    /**
    * Accepts an array of Shipping objects, and uses selection sort to 
    * sort the list based off of shipmentID.
    * @param Shipping object lst.  
    */
    public static void sort1(ArrayList<Shipping> lst) {
        loaded = true;
        for (int i = 0; i < lst.size() - 1; i++) {
            int intMin = i;
            for (int j = i + 1; j < lst.size(); j++) {
                if (lst.get(j).getShipmentID() 
                    < lst.get(intMin).getShipmentID()) { 
                    intMin = j;
                }
            }
            if (intMin != i) {
                Shipping temp = lst.get(intMin);
                lst.set(intMin, lst.get(i)); 
                lst.set(i, temp);
            }
        }  
        sorted = true;
        System.out.printf("Sorting is done!\n");
        System.out.printf("\n");
    } //End of sort1
    
    /**
    * Accepts an array of Shipping objects, and uses selection sort to 
    * sort the list based off of trackingNumber.
    * @param Shipping object lst.  
    */
    public static void sort2(ArrayList<Shipping> lst) {
        loaded = true;
        for (int i = 0; i < lst.size() - 1; i++) {
            int intMin = i;
            for (int j = i + 1; j < lst.size(); j++) {
                String trackingNumber = lst.get(j).getTrackingNumber();
                String trackingNumber2 = lst.get(intMin).getTrackingNumber();
                if (trackingNumber.compareTo(trackingNumber2) < 0) {
                    intMin = j;
                }
            }
            if (intMin != i) {
                Shipping temp = lst.get(intMin);
                lst.set(intMin, lst.get(i));
                lst.set(i, temp); 
            }
        }
        sorted = true;
        System.out.printf("Sorting is done!\n");
        System.out.printf("\n");
    } //End of sort2  
} //End of class      
