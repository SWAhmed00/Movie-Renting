
// Compiler:      IntelliJ
package Tickets;

public class Node
{
    public String title;//holds title
    public int available;//holds available movies
    public int rented;//holds rented copies
    Node left;//left node
    Node right;//right node

    Node()
    {

    }//end of default constructor

    /**
     *Method:   Node(String title,int available,int rented)
     * Description:Creates a node based on input  variables
     * @param title holds title of movie
     * @param available holds # of copies available for movie
     * @param rented holds # of copies rented of the movie
     *
     */
    Node(String title,int available,int rented)
    {
        this.title=title;
        this.available=available;
        this.rented=rented;
    }//end of constructor


    public String getTitle()
    {
        return title;
    }//end of getter

    public void setTitle(String title)
    {
        this.title = title;
    }//end of setter

    public int getAvailable()
    {

        return available;
    }//end of getter

    public void setAvailable(int available)
    {
        this.available = available;
    }//end of setter

    public int getRented()
    {
        return rented;
    }//end of getter

    public void setRented(int rented)
    {
        this.rented = rented;
    }//end of setter

    public Node getLeft()
    {
        return left;
    }//end of getter

    public void setLeft(Node left)
    {
        this.left = left;
    }//end of setter

    public Node getRight()
    {
        return right;
    }//end of getter

    public void setRight(Node right)
    {
        this.right = right;
    }//end of setter







}//end of Node class
