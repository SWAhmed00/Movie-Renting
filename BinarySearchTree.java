
// Compiler:      IntelliJ
package Tickets;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BinarySearchTree <E> extends Node
{
Node root;//holds the root of the tree

    /**
     * Method: BinarySearchTree(int lenght,int mid,String name[],int rented[],int free[])
     * Description: Seeds the BST based on the movies and their attributes found in the inventory.dat,constructor
     * @param lenght holds the lenght of the array we are using to seed the tree
     * @param mid holds the mid index
     * @param name holds the names of all the movies
     * @param rented holds all the rented number for all the movies
     * @param free holds all the free copies for the movies.
     */

    BinarySearchTree(int lenght,int mid,String name[],int rented[],int free[])
    {
    //creating the root and setting the pointer to the root to build off of it
    root=new Node(name[mid],free[mid],rented[mid]);

    Node current=root;
    //seeding the movies in alphabetical order to the left of the root
    for (int i=(mid-1);i>=0;i--)
    {
        current.left=new Node(name[i],free[i],rented[i]);
        current=current.left;
    }//end of for loop for the left side
    current.left=null;
    current=root;
        //seeding the movies in alphabetical order to the right of the root
    for (int k=mid+1;k<lenght;k++)
    {
        current.right=new Node(name[k],free[k],rented[k]);
        current=current.right;
    }//emnd of for loop
        current.right=null;

    }//end of constructor
    public Node getRoot()
    {
        return root;
    }//end of getter

    /**
     * Method: public Node search(Node key,String name)
     * Description:This function searches the entire binary tree to see if the movie name that the system wants to act
     * upon existst in the current inventory/tree or not
     * @param key holds the node we are currently comparing against the movie name
     * @param name holds the movie name we are looking for
     * @return the node that has the movie name or null if one such node does not exist
     */
    public Node search(Node key,String name)
    {
        String one="";//holds current Nodes movie name
        String two="";//holds searching movies name
        boolean done=false;//flag to see if the name has been completeely parsed
        boolean smaller=false;//flag that tells us if the new element is smaller than the current nodes title
        int lengOne=0;//holds length of current nodes title name
        int lengTwo=0;//holds length of the name we are testing against



        //if key is null or equals we pass the key

        if(key==null||(key.title.equalsIgnoreCase(name)))
        {
            return key;
        }//end of if statement

        //getting rid of spaces for a more accurate evaluation
        two=name.replaceAll(" ","");
        one=key.title.replaceAll(" ","");
        //setting all char to upper case to avoid confusion when comparing alpahabetical order

        two=two.toUpperCase();
        one=one.toUpperCase();
        //if the nodes character is greater than the one we are searching fro we go left
        if(one.charAt(0)>two.charAt(0))
        {
           return search(key.left,name);
        }//end of if statement
        //else if they are equal we evaluate the whole string to find wheteher or not we go right or left
        else if(one.charAt(0)==two.charAt(0))
        {
            //getting the lengths
        lengOne=one.length();
        lengTwo=two.length();
        //if the string we are searching for has a smaller length we change the length so we dont go pas the index
            //and we change the smaller flag to true
        if (lengOne>lengTwo)
        {
            smaller=true;
            lengOne=lengTwo;
        }//end of if statement
            //for loop to run until we find a character that doesnt match in the string
        for (int i=0;i<lengOne;i++)
        {
            //if the current nodes char is greater than the searching string we go left
            if(one.charAt(i)>two.charAt(i))
            {
                return search(key.left,name);
            }//end of if statement

            //else if the opposite is true we go right
            else if (one.charAt(i)<two.charAt(i))
            {
                return search(key.right,name);
            }//end of else if statemnt
            //if we have reached the end of the string we set the done flag to true
            if(i==(lengOne-1))
            {
                done=true;
            }//end of if statement

        }//end of for loop
            //if we finished the smaller string and the searching stting is smaller we go right
          if(done && smaller)
          {
              return search(key.left,name);
          }//end of if statement
        }//end of else if statement

        //if we make it to here then the current nodes value was smaller than the new strings and we go right
        return search(key.right,name);

    }//end of search function


    /**
     *Method:   public Node insert(Node key,String name,int copies)
     * Description: Inserts a node alphabetically,based on the other movie names in the tree
     * @param key holds the node we are currently comparing the new movie name to
     * @param name holds the name of the movie we are trying to insert
     * @param copies holds the numnber of copies the new movie has available
     * @return returns the Node we have just inserted
     */
    public Node insert(Node key,String name,int copies)
    {
        String one="";//holds string for current node
        String two="";//holds the name of the new movie
        boolean done=false;//flag to see if we are done evaluating the Strings
        boolean smaller=false;//falg to indicate whether or not new name is smaller than the current nodes title
        int lengOne=0;//holds the length of the current nodes title
        int lengTwo=0;//holds the lenght of the new movie length


        if(key==null)
        {
        return new Node(name,copies,0);
        }//end of if statement

        //getting rid of all the spaces in the movie titles for accurate evaluation of the alphabetical order
        two=name.replaceAll(" ","");
        one=key.title.replaceAll(" ","");
        //setting them all to caps to avoid issues with different capitalizations
        two=two.toUpperCase();
        one=one.toUpperCase();

        //if the char of the current node is greater than the new names char we go left
        if(one.charAt(0)>two.charAt(0))
        {
            key.left= insert(key.left,name,copies);
        }//end of if statement
        //else if the opposite is true we go right
        else if (two.charAt(0)>one.charAt(0))
        {
            key.right= insert(key.right, name,copies);
        }//end of else if statement
        //else if they equal each other we evaluate the entire strings
        else if(one.charAt(0)==two.charAt(0))
        {
            lengOne=one.length();
            lengTwo=two.length();
            //check to see which String is smaller and use that length to avoid going out of bounds
            if (lengOne>lengTwo)
            {
                smaller=true;
                lengOne=lengTwo;
            }//end of if statement
            //for loop to evaluate every character in the two strings
            for (int i=1;i<lengOne;i++)
            {
                //if current nodes char is greater than the new names char we go left and break
                if(one.charAt(i)>two.charAt(i))
                {
                    key.left=insert(key.left,name,copies);
                    break;
                }//end of if statement

                //else if the opposite is true we go right and break
                else if (one.charAt(i)<two.charAt(i))
                {
                     key.right=insert(key.right,name,copies);
                        break;
                }//end of else if statement
                //if we are done evaluating one whole string we change the flag to true
                if(i==(lengOne-1))
                {
                    done=true;
                }//end of if statement
                //if both flags are true we go left as the new name is higher on the alphabet
                if(done && smaller)
                {
                    key.left=insert(key.left,name,copies);
                }//end of if statement

            }//end of for loop

        }//end of else if statement
    return key;

    }//end of search function

    /**
     * Method:   public  Node delete(Node root, String key)
     * @param root holds the current node we are evaluating
     * @param key holds the name we are trying to find and delete
     * @return
     */
    public Node delete(Node root, String key)
    {
        String one="";//holds string for current node
        String two="";//holds the name of the new movie
        boolean done=false;//flag to see if we are done evaluating the Strings
        boolean smaller=false;//falg to indicate whether or not new name is smaller than the current nodes title
        int lengOne=0;//holds the length of the current nodes title
        int lengTwo=0;//holds the lenght of the new movie length
        boolean movement=false;

        Node par = null;//holds the parent node of the current node being evaluated
       //we begin with the root and traverse it from there
        Node current = root;

        //We simply search the Binary Search Tree and update the parent node
        while (current!= null && !(current.title.equalsIgnoreCase(key)))
        {
            two=key.replaceAll(" ","");
            one=current.title.replaceAll(" ","");
            //setting them all to caps to avoid issues with different capitalizations
            two=two.toUpperCase();
            one=one.toUpperCase();


            par = current;

           //if the char is less than the current nodes we go left
            if (two.charAt(0) < one.charAt(0))
            {
                current = current.left;

            }//end of if statement
            //else we go right

            else if(two.charAt(0) ==one.charAt(0))
            {


                lengOne=one.length();
                lengTwo=two.length();
                //check to see which String is smaller and use that length to avoid going out of bounds
                if (lengOne>lengTwo)
                {
                    smaller=true;
                    lengOne=lengTwo;
                }//end of if statement
                //for loop to evaluate every character in the two strings
                for (int i=0;i<lengOne;i++)
                {
                    //if current nodes char is greater than the new names char we go left and break
                    if(one.charAt(i)>two.charAt(i))
                    {
                        current = current.left;


                        break;
                    }//end of if statement

                    //else if the opposite is true we go right and break
                    else if (one.charAt(i)<two.charAt(i))
                    {
                        current = current.right;


                        break;
                    }//end of else if statement
                    //if we are done evaluating one whole string we change the flag to true
                    if(i==(lengOne-1))
                    {
                        done=true;
                    }//end of if statement

                    //if both flags are true we go left as the new name is higher on the alphabet
                    if(done && smaller )
                    {
                        current = current.left;
                    }//end of if statement
                }//end of for loop

                done=false;
                smaller=false;
            }//end of else if statement
            else
            {
                current = current.right;
            }//end of else statement

        }//end of while loop

        //if we dont find what we are looking for, we return null
        if (current == null)
        {
            return root;
        }

        //if the node we need has no chilldren we follow this process
        if (current.left == null && current.right == null)
        {
            //if the current node is not a root we go ahead and set the node to null as we dont need it anymore
            if (current != root)
            {
                //if its the left node we delete that one
                if (par.left == current)
                {
                    par.left = null;
                }//end of if statement
                //else we delete teh one to the right
                else
                {
                    par.right = null;
                }//end of else statement
            }//end of if statement

            // if the root is all thats there we delete it and set it to null
            else
            {
                root = null;
            }//end of else stateemnt
        }//end of if statement

        //The second possibility is that the node we need to delete has 2 chilldren
        else if (current.left != null && current.right != null)
        {
            Node successor  = smallerOne(current.right);//store the node that will become the new main node


            String val = successor.title;//we store the value that needs to be changed

            //now we use recursion to delete the node once the node becomes something simple to changer
            delete(root, successor.title);

           //the chnaging value is saved as the currents movie name
            current.title = val;
        }//end of else if statement

        //The last possible case is that the node that needs to be deleted has exactly one child
        else
        {
           //if teh left one equals left we set the children node to left, else we set it to the right node
            Node children = (current.left != null)? current.left: current.right;

        //if the current node is not a root we set the parents child as the current node stored in chilldren
            if (current != root)
            {
                if (current == par.left)
                {
                    par.left = children;
                }//end of if statement
                else
                {
                    par.right = children;
                }//end of else statement
            }//end of if statement

            // if the node we need to delete is a root we set its child as the new root thus destryoing the original root
            else
            {
                root = children;
            }//end of else statement
        }//end of else statement

        return root;
    }//end of delete function

    /**
     * Method:  public Node smallerOne(Node key)
     * Description: finds the smallest value/element
     * @param key holds the node we are checking
     * @return returns smallest node/left most value
     */
    public Node smallerOne(Node key)
    {
        //while loop that runs until we get the leftmost node of the BST
        while(key.left != null)
        {
            key = key.left;
        }//end of while loop

        //returning the left most node
        return key;
    }//end of smallerOne function

    public void setRoot(Node root)
    {
        this.root = root;

    }//end of setter

    /**
     *Method:   public  void errors(List<String> problems) throws IOException
     * Description: Outputs the erors n transaction onto the error file
     * @param problems has list of errors in the transac file
     * @throws IOException to deal with a no file eror
     */
    public  void errors(List<String> problems) throws IOException
    {
        PrintWriter edit=new PrintWriter("error.log");//creates the new file and writes to it
        //getting size of the array list
        int size=problems.size();
        //loop prints the errors to the file in order
        for (int i=0;i<size;i++)
        {
            edit.write(problems.get(i));
            edit.write("\n");
        }//end of for loop

        //closing the file
        edit.close();
    }//end of errors function

}//end of BinarySearchTree class
