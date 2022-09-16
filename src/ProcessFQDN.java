/**
 * This Program prompts the user for a list of URLs, an output document, and a list type. The program then reads
 * through the list of URLs and reports the number of Fully Qualified Domain names, and then the number of unique fully
 * qualified domain names, second level domain names, and top level domain names while writing them to the output
 * document. The program also records the time needed to complete this task and reports, in milliseconds, the amount
 * of time that passed.
 *
 * @author Christos Kydonieus {@literal < kydoce20@wfu.edu>}
 * @version 0.1, Spetember 15th, 2022
 */

import java.io.*;
import java.util.*;
import java.util.NoSuchElementException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.FileWriter;

public class ProcessFQDN{
    //@param args String [] , -[d|s] names . txt results . txt
    public static void main(String[] args) throws IOException{
        List<String> uniqueHost = null; // unique hostnames
        List<String> unique2LD = null; // unique 2 LD names
        List<String> uniqueTLD = null; // unique TLD names
        int numFQDN; // number of FQDNs


        if (!argsOK(args))
            System.exit(1);


        // use the list specified by the user ...
        if (args[0].equals("-d")) {
            uniqueHost = new DList<String>();
            unique2LD = new DList<String>();
            uniqueTLD = new DList<String>();
        } else if (args[0].equals("-s")) {
            uniqueHost = new SortedList<String>();
            unique2LD = new SortedList<String>();
            uniqueTLD = new SortedList<String>();
        } else {
            System.out.println(" list type " + args[0] + " is incorrect ");
            return;
        }



        final long startTime = System.currentTimeMillis();

        numFQDN = readNameFile (args [1], uniqueHost , unique2LD , uniqueTLD );
        displayNameInfo (args [2] , numFQDN , uniqueHost , unique2LD , uniqueTLD );


        final long endTime = System.currentTimeMillis();

        // just subtract the two times
        long difference = endTime - startTime;
        System.out.println("Time to complete : " + difference + " msec ");
    }

    /**
     * This method reads in a file of domain names and sorts the unique values into the proper lists of domain names
     * and then reports the number of FQDNs.
     *
     * @param fileName designated input file
     * @param uniqueHost list of FQDNs
     * @param unique2LD list of 2LDs
     * @param uniqueTLD list of TLDs
     * @return number of FQDNs
     * @throws IOException in case the input file is not found
     */
    public static int readNameFile(String fileName, List uniqueHost, List unique2LD, List uniqueTLD) throws IOException{
        // Creating an input stream for the requested file.
        InputStream inputFile = new FileInputStream(fileName);
        // creating a scanner to read the file.
        Scanner fileReader = new Scanner(inputFile);
        // keeping track of number of FQDNs.
        int numFQDN = 0;

        // this while loop goes through each line of the file and decides what type of domain name they are and adds
        // them to the correct list. It does this by counting the number of periods in each line.
        while (fileReader.hasNext()) {
            String curLine = fileReader.nextLine();
            int numPeriods = 0;

            // reading number of periods
            for (int i = 0; i < curLine.length(); i++){
                char curChar = curLine.charAt(i);
                if (curChar == '.'){
                    numPeriods++;
                }
            }

            // deciding what to do
            // if there are no periods, it must be a TLD name.
            if (numPeriods == 0){
                if (!uniqueTLD.contains(curLine)) {
                    uniqueTLD.add(curLine);
                }
            }

            // if there is one period, it is a TLD or a 2LD. Since 2LD are also count at FQDNs, it checks for that too
            if (numPeriods == 1){
                if (curLine.charAt(0) == '.') {
                    if (!uniqueTLD.contains(curLine)) {
                        uniqueTLD.add(curLine);
                    }
                } else {
                    if (!unique2LD.contains(curLine)) {
                        unique2LD.add(curLine);
                    }
                    if (!uniqueHost.contains(curLine)) {
                        uniqueHost.add(curLine);
                    }
                    numFQDN++;
                }
            } else if (numPeriods == 2){
                if (!uniqueHost.contains(curLine)) {
                    uniqueHost.add(curLine);
                }
                numFQDN++;
            }
        }

        return numFQDN;
    }

    /**
     * This method reports the number of FQDNs adn the number of unique FQDNs, 2DNs, and TLDNs, while writing them
     * to the output file
     * @param fileName designated output file
     * @param numFQDN number of FQDNs
     * @param uniqueHost list of FQDNs
     * @param unique2LD list of 2LDs
     * @param uniqueTLD list of TLDs
     * @throws IOException in case the result file is not found
     */
    public static void displayNameInfo(String fileName, int numFQDN, List uniqueHost, List unique2LD, List uniqueTLD) throws IOException {
        // creating output file
        FileWriter output = new FileWriter(fileName);

        // setting up unique numbers
        int numUFQDN = uniqueHost.size();
        int numU2LD = unique2LD.size();
        int numUTLD = uniqueTLD.size();

        // reporting numbers
        System.out.println("Found " + numFQDN + " FQDNs, " + numUFQDN + " unique FQDNs, " + numU2LD + " unique 2LDs, and " + numUTLD + " unique TLDs");

        // writing to results
        output.write("Unique FQDNs: ");
        output.write(uniqueHost.toString());

        output.write("\nUnique 2DLs: ");
        output.write(unique2LD.toString());

        output.write("\nUnique TDLs: ");
        output.write(uniqueTLD.toString());

        output.close();
        System.out.println("Unique FQDNs, 2LDs, and TLDs written to: results.txt");
    }

    /**
     * This method checks if the arguments passed through the command line are accurate for the program to work. It
     * checks if the list designation is right, and if the specified inputs and outputs are correct.
     * @param args the arguements passed through the command line
     * @return true if yes, false if the arguments are not correct.
     */
    public static boolean argsOK(String[] args){
        if (args.length != 3){
            System.out.println("Not enough arguments (You need 3 arguments: -[d|s], names.txt, and results.txt)");
            return false;
        }
        if (!args[1].equals("names.txt")){
            System.out.println("Not correct source file (Should be 'names.txt,)");
            return false;
        }

        if (!args[2].equals("results.txt")){
            System.out.println("Not correct output file (Should be 'results.txt,)");
            return false;
        }
        return true;
    }
}
