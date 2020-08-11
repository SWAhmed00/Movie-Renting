
// Compiler:      IntelliJ
//Redbox needs a program to track inventory and to generate a report for their DVD rental kiosks.
// Given a log of transactions including renting and returning DVDs as well as adding and removing DVD titles,
// the program will need to process each transaction and create a report after all transactions have been processed.
// The generated report will list all DVD titles stored in the kiosk as well as how many of each disc are in the kiosk.
//Use a BST and recursive functions
package Tickets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main
{
    public static void main(String [] args) throws IOException
        {
        File inv = new File("inventory.dat");//holds inventory
        File transac = new File("transaction.log");//holds transactions
        String temp[]=new String[1] ;//holds movie name for swapping
        String[] tempTwo;//holds the movie info for parsing
        boolean done=false;//checks to see if String comparison is over or not
        boolean secondOne=false;//checks to see if new name is smaller than node name
        int lenOne=0;//length of String one
        int lenTwo=0;//length of String 2
        String holdOne="";//holds the first String being compared
        String  holdSec="";//holds the second String being compared
        int length;//holds length of array holding the movies
        int rent;//holds the rented copies
        int have;//holds the available copiess
        List<String> name=new ArrayList<String>();//array list for names of movie
        List<String> eror=new ArrayList<String>();//array list for erors
        List <Integer> rented=new ArrayList<Integer>();//array list for copies rented
        List <Integer> available=new ArrayList<Integer>();//array list for available copies
        String line;//Holds the line to be parsed
        String lineTwo;//holds raw line
        String[] parse;//used for parsing transactions
        int copies=0;//holds copies from transactions
        Node current;//holds current node being worked on
        boolean valid=true;//valid input flag
        boolean erorExist=false;//eror flag
        BinarySearchTree tree;//BST
        //if file exsists
        if (inv.exists())
        {
        //scanner for file
        Scanner sc=new Scanner (inv);
        //while there is input from the file we run
        while(sc.hasNextLine())
        {
            //parsing the info
            temp[0] = sc.nextLine();
            tempTwo= temp[0].split(",");
            tempTwo[0]=tempTwo[0].substring(1,tempTwo[0].length()-1);
            //storing the data to seed later
            name.add(tempTwo[0]);
            rent=Integer.parseInt(tempTwo[2]);
            have=Integer.parseInt(tempTwo[1]);
            rented.add(rent);
            available.add(have);

        }//end of while loop
        length=name.size();//holds size
        String[] names=new String[length];//names array
        int [] avail=new int[length];//available copies array
        int [] taken=new int[length];//rented copies array
        //for loops to bubble swap the initial movies so they are alphabetical when we seed them
        for (int i=0;i<length;i++)
        {
            names[i]=name.get(i);


            avail[i]=available.get(i);
            taken[i]=rented.get(i);
        }//end of for loop
            String holder;
            int hold;
            int holdTwo;
            //for loop for bubble swap
            for(int i=0; i < length; i++)
            {
                //for loop for bubble swap
                for (int j = 1; j < (length); j++)
                {

                    holdOne=names[j-1];
                    holdOne=holdOne.substring(0,1);
                    lenOne=names[j-1].length();
                    holdSec=names[j];
                    holdSec=holdSec.substring(0,1);
                    lenTwo=names[j].length();
                    holdOne=holdOne.toUpperCase();
                    holdSec=holdSec.toUpperCase();
                    //if one is out of order we fix this
                    if ((holdOne.charAt(0)) > (holdSec.charAt(0)))
                    {

                        //swap elements
                        holder = names[j - 1];
                        hold = avail[j - 1];
                        holdTwo = taken[j - 1];

                        names[j - 1] = names[j];
                        avail[j - 1] = avail[j];
                        taken[j - 1] = taken[j];

                        names[j] = holder;
                        avail[j] = hold;
                        taken[j] = holdTwo;
                    }//end of if statement

                    holdOne=names[j-1];

                    holdSec=names[j];


                    holdOne=holdOne.toUpperCase();
                    holdSec=holdSec.toUpperCase();
                    String contOne="";
                    String contTwo="";
                    //if the first char matches we evaluate the whole name
                    if ((holdOne.charAt(0)) == (holdSec.charAt(0)))
                    {
                        //updating flag to tell us second one is smaller
                    if(lenTwo<lenOne)
                    {
                        secondOne=true;
                        lenOne=lenTwo;
                    }//end of if statement
                        //we compare each element until one doesnt match or the string is parsed completeley
                    for (int p=0;p<lenOne;p++)
                    {
                        //converting char to string
                    contOne=Character.toString(holdOne.charAt(p));
                    contTwo=Character.toString(holdSec.charAt(p));
                    //if two is higher on the alphabet than one, we swap and break from the loop
                    if(contTwo.charAt(0)<contOne.charAt(0))
                    {
                        holder = names[j - 1];
                        hold = avail[j - 1];
                        holdTwo = taken[j - 1];

                        names[j - 1] = names[j];
                        avail[j - 1] = avail[j];
                        taken[j - 1] = taken[j];

                        names[j] = holder;
                        avail[j] = hold;
                        taken[j] = holdTwo;
                        break;
                    }//end of if statement
                    //else if the opposite is true we end the evaluation as no swap is needed
                    else if(contTwo.charAt(0)>contOne.charAt(0))
                    {
                        break;
                    }//end of else if statement
                        //if the short string is done we change the flag
                        if(p==(lenOne-1))
                        {
                            done=true;
                        }//end of if statement
                    }//end of for loop
                        //if its smaller and the whole string is parsed, it means that the string is smaller and thus
                        //higher based alphabeticaly than the longer string so we swap it
                    if(done && secondOne)
                    {
                        holder = names[j - 1];
                        hold = avail[j - 1];
                        holdTwo = taken[j - 1];

                        names[j - 1] = names[j];
                        avail[j - 1] = avail[j];
                        taken[j - 1] = taken[j];

                        names[j] = holder;
                        avail[j] = hold;
                        taken[j] = holdTwo;
                    }//end of if statement

                    }//end of if statement
                    //resetting the flags
                    done=false;
                     secondOne=false;
                }//end of for loop
            }//end of for loop

            //getting midpoint for root of tree
            int mid=length/2;
            int rootA=0;
            int rootR=0;
            //creating the tree
            tree=new  BinarySearchTree<Node>(length,mid,names,taken,avail);
            //using the transaction file
            if(transac.exists())
            {
                //scanner for transaction file
                Scanner input=new Scanner(transac);
                //while the file has input to be taken in
                while(input.hasNextLine())
                {
                    current=tree.root;
                line=input.nextLine();
                lineTwo=line;
                //splitting the string to get the elements we want
                parse=line.split("\"");
                //if it doesnt have the correct sizes of elements we report it as an error
                if(parse.length!=2 && parse.length!=3)
                {
                  erorExist=true;
                eror.add(lineTwo);
                }//end of if statement
                //else if the length is 3 we evaluate
                else if (parse.length==3)
                {
                 //if the number value is in the right format we try to parse it as an int
                if(parse[2].charAt(0)==',')
                {
                    parse[2] = parse[2].substring(1, parse[2].length());
                }//end of if statement
                //else update flag and report as eror
                    else
                    {
                        erorExist=true;
                     valid=false;
                     eror.add(lineTwo);
                    }//end of else statement
                    //if formatting is valid we try to parse it as an int
                    if(valid)
                    {
                        //try to see if an actual int was entered
                        try
                        {
                            copies = Integer.parseInt(parse[2]);

                        }//end of try statement
                        //catch if it isnt valid and we repotrt it as an error
                        catch (Exception e)
                        {
                            erorExist = true;
                            eror.add(lineTwo);
                            valid = false;
                        }//end of catch statement

                    }//end of if statement
                    //if format is valid and copies are less than 0 we report it as an error and update flag
                if (valid&& copies<0)
                {

                  valid=false;
                    erorExist=true;
                  eror.add(lineTwo);
                }//end of if statement
                //if everything is valid we carry on with the transaction
                if(valid)
                {
                    //if the choice is add we search for the movie in our tree
                if(parse[0].equals("add "))
                {
                rootA=tree.root.available;
                rootR=tree.root.rented;
                current=tree.search(tree.root,parse[1]);
                tree.root.available=rootA;
                tree.root.rented=rootR;

                //if we find it we add the copies number to the movie inventory
                if(current!=null)
                {

                    int change;
                    change=current.available;
                    copies+=change;
                    current.available=copies;

                }//end of if statement

                //else if copies are greater than 0 we insert the movie into the tree
                else if(copies>0)
                {

                    rootA=tree.root.available;
                    rootR=tree.root.rented;
                current=tree.insert(tree.root,parse[1],copies);
                current.available=copies;
                current.rented=0;
                    tree.root.available=rootA;
                    tree.root.rented=rootR;
                }//end of else statement

                //else we report it as an error
                else
                {
                    eror.add(lineTwo);
                    erorExist=true;
                }//end of else statement

                }//end of if statement
                //else if the command is to remove we search for the movie in the BST
                else if (parse[0].equals("remove "))
                {
                    rootA=tree.root.available;
                    rootR=tree.root.rented;
                 current=tree.search(tree.root,parse[1]);
                    tree.root.available=rootA;
                    tree.root.rented=rootR;
                    //if its found we do the arithmetic onto it
                 if(current!=null)
                 {
                    int change;
                    change=current.available;

                     change-=copies;
                     current.available=change;

                     //if both availabel and rented are 0 we delete the movie from inventory
                     if(current.available==0 && current.rented==0)
                     {
                         current=tree.delete(tree.root,parse[1]);
                     }//end of if statement
                        current=null;
                 }//end of if statemnt

                 //else we report it as an eror if movie is not found
                 else
                 {

                     erorExist=true;
                     eror.add(lineTwo);
                 }//end of else statement

                }//end of else if statemnt
                //else if the command is not found we report it as an error
                else
                {
                    erorExist=true;
                eror.add(lineTwo);
                }//end of else statement

                }//end of if statement

                }//end of else if statement
                //else if the length is 2 we act upon certain commands
                else if (parse.length==2)
                {
                    //if the command rent is found we search the BST for the movie name
                if (parse[0].equals("rent "))
                {
                    rootA=tree.root.available;
                    rootR=tree.root.rented;
                    current=tree.search(tree.root,parse[1]);
                    tree.root.available=rootA;
                    tree.root.rented=rootR;
                    //if movie is found we update values for available and rented
                    if(current!=null)
                    {
                    current.rented++;
                    current.available--;
                    }//end of if statement
                    //else we report it as an eror if the movie name is not found
                    else
                    {
                    erorExist=true;
                    eror.add(lineTwo);
                    }//end of else statement

                }//end of if statemnt

                //else if the command is return we search to see if the movie exists
                else if(parse[0].equals("return "))
                {
                    rootA=tree.root.available;
                    rootR=tree.root.rented;
                current=tree.search(tree.root,parse[1]);
                    tree.root.available=rootA;
                    tree.root.rented=rootR;
                //if the movie is found we evaluate it
                if (current!=null)
                {
                    //if there was a copy rented out we update the available and rented values
                    if(current.rented>0)
                    {
                        current.rented--;
                        current.available++;
                    }//end of if statement

                    //else we report it as an error
                    else
                    {
                    erorExist=true;
                    eror.add(lineTwo);
                    }//end of else statement

                }//end of if statement
                //else there is no movie in the tree with that name we report it as an error
                else
                {
                    erorExist=true;
                    eror.add(lineTwo);
                }//end of else stateemnt

                }//end of else if statement

                //else it matches nothing and is an error
                else
                {
                    erorExist=true;
                eror.add(lineTwo);
                }//end of else statement

                }//end of else if statament

                // resetting flags and variables
                valid=true;
                current=tree.root;
                }//end of while loop

                //if there are errors we call the function to output them to a file
                if(erorExist)
                {
                    tree.errors(eror);
                }//end of if statement

                //Writer to output the updated inventory to a file
                PrintWriter edit=new PrintWriter("redbox_kiosk.txt");
                edit.write(String.format("%-40s","Movies:")+  String.format("%10s","Available:"+String.format("%10s","Rented:")));
                edit.write("\n");
                //function to print all the stuff out to the file
                print(tree.root,edit);
                //closing the scanner
                edit.close();
            }//end of if statement

        }//end of if statement

    }//end of main function

    /**
     * Method: public static  void print(Node key,PrintWriter edit)
     * @param key holds the current node
     * @param edit holds the file scanner
     */
    public static  void print(Node key,PrintWriter edit)
    {
        //if the node is null we return
        if (key == null)
            return;
        //recurr to the left
        print(key.left,edit);
        //writitng the current nodes information
        edit.write(String.format("%-40s",key.title) + String.format("%10s",key.available)+String.format("%10s",key.rented));
            edit.write("\n");
            //recurr to the right
        print(key.right,edit);
    }//end if print function

}//end of main class
