//Creator: Jamie (JT) O'Roke
//Java Program to create a linked list using recursion.
//Strictly Object oriented, only using classes.
//Error handling is dealt with explicitly via Try/catch block.
import java.util.*;
public class my_list {
    private static node head;

    //------------   MAIN   ------------------//
    public static void main(String[] args) {
        System.out.println("Hello and Welcome to the linked list creator!\nMake a list of integers as big as you'd like.\n");
        menu();
    }
    //-----------   End Main   -----------------//
//menu method of "my_list class" allows the user to select which action the want.
    // this repeats until the user wishes to exit or "quit" (by entering 'q').
    static void menu() {
        System.out.println("What do you want to do with the list?");
        char ans;
        try {       //<---Try and catch block to handle the case that too many characters entered.
            do {    //<---do-while loop to allow the user to continue building, manipulating
                        //the list until they quit.
                System.out.println("a - add\nd - display\nr - remove\nq - quit\n");
                Scanner scan = new Scanner(System.in);
                ans = scan.next(".").charAt(0);
                switch (ans) {  //<---switch statement providing the options
                    case 'a':
                        add();
                        break;
                    case 'd':
                        display();
                        break;
                    case 'q':
                        System.out.println("Goodbye.");
                        break;
                    case 'r':
                        remove();
                        break;
                    default:
                        System.out.println("Invalid Input.");
                }
            } while (ans != 'q');
        } catch (InputMismatchException e) {
            System.out.println("Too many characters entered, or wrong data type.\n");
            menu();
        }
    }

    private static void add() {     //<---the add method simply gathers data then sends it to the
        try {                           //insert function
            System.out.println("Enter integer to add to list: ");
            Scanner scan = new Scanner(System.in);
            int a = scan.nextInt();
            insert(head, a);
        } catch (InputMismatchException e) {
            System.out.println("Wrong data type.\n");
            add();
        }
    }

    static node insert(node curr, int val) {        //<---this insert function sorts the data as it
        if (head == null) {                             //inserts the new node in the list
            head = new node(val);
            return head;
        }
        if (head.comp_data(val) == 1) {         //<---node.comp_data(int val) returns '1' if node.data is bigger than
            node temp = new node(val);              //the given value.  It can be found in the node class.
            temp.connect_next(head);
            head = temp;
            return head;
        }
        if (curr.go_next() != null) {
            if (curr.comp_data(val) != 1) curr.connect_next(insert(curr.go_next(), val)); //<---this statement traverses
            else {                                                                            //the list until the correct
                node temp = new node(val);                                                    //position is found.
                temp.connect_next(curr);
                return temp;
            }
        } else {
            if (curr.comp_data(val) == 1) {
                node temp = new node(val);
                temp.connect_next(curr);
                if (curr == head) head = temp;
                return temp;
            } else {
                curr.connect_next(new node(val));
                return curr;
            }
        }
        return curr;
    }

    static void display() { //<---this display kickstarts the recursive display method (which takes the head node).
        System.out.println("\n----  LIST  ----");
        display(head);
        System.out.println("--- END LIST ---\n");
    }

    static void display(node curr) {//<---Recursive display method
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        curr.display(); //<---calling the node's display method (can be found in node class).
        if (curr.go_next() != null) display(curr.go_next());//<Statement to traverse the list
    }
    static void remove() {      //<---remove method to simply gather data and send it to the
        try {                       //other remove method which takes parameters, specifically
                                    //a node(the first node of the list) and integer value(to be removed).
            System.out.println("Enter integer to remove from list: ");
            Scanner scan = new Scanner(System.in);
            int a = scan.nextInt();
            remove(head, a);
        } catch (InputMismatchException e) {
            System.out.println("Wrong data type.\n");
            remove();
        }
    }

    static node remove(node curr, int val) {
        if(head == null) return null;
        if(curr != null) {
            if (curr.comp_data(val) == 2) {     //<---node.comp_data(int val) returns 2 if node.data is equal to
                if(curr == head){                   //the given value. It can be found in the node class.
                    head = remove(curr.go_next(), val);
                }
                else curr = remove(curr.go_next(), val);
            } else curr.connect_next(remove(curr.go_next(), val));
        }
        return curr;

    }
}
//--------- Node Class ----------//
class node {
    private node next;
    private final int data;
    public node(int val) {
        data = val;
        next = null;
    }
    //node class handles its own data other than giving the next pointer via "go_next()" method.
        public node go_next () {return next;}       //<---this is the class version of a getter.
        public void connect_next(node a_node){next = a_node;}//<---this is the class version of a setter.
        //------------getters and setters are ok for nodes in OOP because nodes are only used in data structures -------------
        public void display(){System.out.println(data);}
        public int comp_data(int a){     //<---This function is possibly the most used function for the data structure
            if(data > a) return 1;          // It is the function that will compare the user input data to the nodes
            if(data == a) return 2;         // currently in the list.
            else return 0;
        }
}