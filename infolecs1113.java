//Lectures

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
/*Lec 1
//.java file ->javacompile-> .class file, containing bytecode.
keyword public means the class name must match the filename
    its the access modifier, the class can be accessed from any file
        while using private, it is a file-local class
static keyword means no objects are created for the class, eg a function
void indicates the return type, is there a return int or similar

    format: accessmodifier objectcreate returntype
args is just the name of the array

scope of a var is where it is accessible, if declared in nested curlies, low scope

*/

class Example{

    public boolean IsEven(int n){
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}

class animal {

    protected String name;
    private int animalAge;//this won't be accessible in dog

    public animal(String name) {
        this.name = name;
        this.animalAge = 3;
    }

    protected String getName() {
        return this.name;
    }
}
//LEC 5 -------------------555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555
class dog extends animal{

    protected boolean shedder;
    //public class subclassname extends parentclassname{}
    //all of parentClass methods and attributes are given to child

    //new keyword: protected
    //private is blocked from all, public is accessible to all, protected is accesible to subclasses
    //stuff that is private in parent class is not accessible to subclass
    
    //we can override methods with the same name, the subclass method is prioritised
    
    public String getName() {
        System.out.println("This is the dog " + name);
        return name;
    }
    //we cannot name the methods the same if they don't have the same return type, they need to be total replacements

    //the subclass will default to a superclass constructor if it can
    //also you can call a subclass constructor, it will use superclass

    //using a subclass constructor
    public dog() {
        super("doggo");
        this.shedder = true;
        //super must be the first line of the constructor
        //super("frined") will break
    }
    //creating a new dog class object will use this constructor second, and therefore it will override any inits by the superclass constructor
    //so age will be 3, even though it isn't in subclass

    //if the superconstructor takes args, you can't call the subconstructor, even if it doesn't take args
    //use the keyword: super
    //inside the subconstructor, use super(); to call back to the superconstructor and pass args
    //this means you can still call the subconstructor, even if you don't pass the args of the superconstructor from the main call
    //its as if there is always a super(); hidden at the first line of a subconstructor
}

class golden extends dog {
    //extending a subclass, this is the grandchild of class animal
    
    protected String colour;
    protected boolean fed;

    public golden(String col) {
        super();
        this.colour = col;
    }

    @Override//???
    public String getName() {
        return this.name;
    }
    //@Override keyword

    //overloading
    //you can have 2 methods with the same name, but the signature is different
    //same as having different constructors
    //if there is an ambiguous overload, eg golden.feed(null);
    //this will throw an error, cos JVM doesn't know which to pick
    public void feed(String food) {
        this.fed = true;
    }
    
    public void feed(int grams) {
        this.fed = true;
    }

    //overloading constructor with this();
    //instead of recreating a whole constructor, use this(arg1, arg2); to call another constructor from the constructor
    public golden(boolean food) {
        this("brown");
    }
    //technically calling almost everything is overloading
    //println() is a method that takes different args, you can give it int, string, object, it will work with it, but each type is technically different

    //exceptions
    public void thrower() throws Exception{
        throw new Exception("forced crash");
    }
    //checked exceptions are only found with try catch blocks
    //runtime and similar exceptions are checked by the the program, programmer doesn't so much have to check for it
    //if there is a custom method to throw an exception, it must be declared with throws
}

//LEC 6 -------------------6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666
//abstract classes cannot be instantiated
//can have attributes and methods, but cannot create an object
//methods can be specified and subclasses must define them
//eg shape is a generalisation of square and triangle, but you can't make a concrete instance of shape
//modifier abstract class className
abstract class furniture {
    //remember: attributes with no modifier default to public
    String name;
    List<String> parts;
    //can still make a constructor
    public furniture(String name) {
        this.name = name;
    }

    //we can declare a method, but we don't actually create it, the subclass will do that
    public abstract void stack(furniture f);

    public abstract boolean canSit();
}


class chair extends furniture {

    public chair() {
        super("chair");
    }
    //subclass must define the abstract method, including args
    //also note we created a chair object, but because it is a subtype of furniture we can pass it as a furniture 'object'
    public void stack(furniture f) {
        System.out.println("don't stack the chairs pls");
    }

    public boolean canSit() {
        return true;
    }
}

class table extends furniture {
    public table() {
        super("table");
    }

    public void stack (furniture f) {
        System.out.println("Stacking tables is kinda dumb");
    }
    public boolean canSit() {
        return false;
    }
}

//interfaces interfacesinterfaces interfacesinterfaces interfacesinterfaces interfacesinterfaces interfacesinterfaces interfaces
//modifier interface interfaceName
interface talk {

    //cos we know all methods are abstract, don't need to write abstract
    public String talk();
}

interface eat {
    public String eat();
}

//class that uses an interface needs keyword: implements interfaceName
class duck implements talk, eat {//you can implement multiple interfaces, but you have to fill the methods for all
    String name;
    
    public duck(String name) {
        this.name = name;
    }   

    public String talk(){
        return "quack";
    }
    public String eat() {
        return "worms";
    }
}
//make another so we can show how they can be grouped
class cow implements talk {
    String name;

    public cow(String name) {
        this.name = name;
    }   
    public String talk(){
        return "moo";
    }    
}

//now extend and implement
class dolphin extends animal implements talk, eat {
    //String name;
    public dolphin() {
        super("flipper");
    }

    public String talk () {
        return "clicks";
    }
    public String eat() {
        return "fish";
    }
}

interface itWasALie {
    //interfaces CAN have attributes
    //only when they are static -> belong to the interface
    //and if they are constant -> final modifier
    final static int ONE = 1;
}

//interface defaults
//java 8 and higher 
//modifier default returnType methodName(params)
//if two classes will implement the same interface, and have a common method with the exact same function, you could just leave it in the interface
//use the default keyword
interface evenMore extends itWasALie{
    public default void explainer() {
        System.out.println("any class that implements evenMore will by default have this");
    }
}

class tryingout implements evenMore {
    public tryingout(){}
    public void explainer() {
        System.out.println("although this interface method had a different default, you can override it in the class");
    }
}

//7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
//generic static methods
//accesModifier static<param1> returnType methodName(param1)
//see inside infolecs1113 class


//8888888888888888888888888888888888888888888888888888888888888888888888888888888888888
class RudeNameException extends Exception{
    public RudeNameException() {
        super("Name is fucking rude");
        //you must pass an error message to the superconstructor
    }
}

class NegativeMarkException extends RuntimeException{
    public NegativeMarkException(){
        super("Mark cannot be negative");
    }
}
class InvalidMarkException extends RuntimeException{
    public InvalidMarkException(){
        super("Mark can't be +100");
    }
}

enum category {//now we can use category.bad as a classifier for an object
    //these are encoded in order, bad == 0, good == 2
    //you can also use category.bad.ordinal(), this == 0
    //you can also put methods into the types
    bad {public category upgrade(){return average;}}, 
    average{public category upgrade(){return good;}}, 
    good{public category upgrade(){return bad;}};

    private int timeT;
    public void category (int t) {
        this.timeT = t;
    }
    //can make an abstract that all of the individual types need to implement
    public abstract category upgrade();
}
enum cards{
    //can also be setup like a class
    //must declare the enum types first
    hearts("red", 0), diamonds("red", 0), spades("black", 0), clubs("black", 0);

    public int number;
    public String colour;

    cards(String colour, int number){
        this.colour = colour;
        this.number = number;
    }
    //you can put methods inside an enum
    public String getColour(){
        return this.colour;
    }
    public void setNumber(int n) {
        this.number = n;
    }
}

//10101010101010101010 lec 10

interface subtractor {
    public int subtractify (int x, int y);
}

@FunctionalInterface
interface maths {
    public abstract int operation (int x, int y);
    //we're only allowed 1 abstract, but any other static or full
    public static int makeNegative(int x) {
        return (-x);
    }
}

@FunctionalInterface
interface greeting {
    public abstract String sayHi();

    
}

//11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11

class Person {

    public String name;
    public Person (String name ) {
        this.name = name;
    }

    public String toString() { return this.name; }

    //use wildcard to make this method accept all subclasses of Person
    public static void readPeople(List<? extends Person> people) {
        for (Person p : people) {
            System.out.println(p.toString());
        }
    }
}

class Employee extends Person {
    public int id;

    public Employee(String name, int id) {
        super(name);
        this.id = id;
    }
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class infolecs1113 {

    //lecture 77777777777777777777: generic static
    //generic static methods
    //accesModifier static<param1> returnType methodName(param1)
    public static<T> T returner(T t) {
        return t;
    }

    //8888888888888888888888888888888
    public static boolean passed(int n) throws NegativeMarkException, InvalidMarkException{
        //needs to have a throws exception if the method can throw an exception
        //this is needed for checked exceptions, but not runtime
        //you can also throw RuntimeException, because it is the superclass of both exceptions
        if (n < 1) {
            throw new NegativeMarkException();
        } else if ( n > 50) {
            return true;
        } else if (n > 100){
            throw new InvalidMarkException();
        } else {
            return false;
        }
    }
    //if you want to force using try-catch, declare the exception with extends Exception, otherwise Runtime

    //9999999999999999999999999999999999999999999999999999999999999999999999
    //recursion
    public static int factorial(int n) {
        int fact;
        if (n > 1) {
            fact = factorial(n-1)*n;
        } else {
            fact = 1;
        }
        return fact;
    }

    public static int fibonacci(int n) {
        int fib;
        if (n > 1) {
            fib = fibonacci(n-1) + fibonacci(n-2);
        } else {
            fib = n;
        }
        return fib;
    }

    public static int[] fiboGen(int n) {
        if (n == 0) {
            return new int[] {0};
        } else if (n == 1) {
            return new int[] {0,1};
        } else {
            int[] f1 = fiboGen(n-1);
            int[] f2 = fiboGen(n-2);
            int[] newf = new int[f1.length + 1];
            for (int i = 0; i < f1.length; i++ ) {
                newf[i] = f1[i];
            }
            newf[newf.length - 1] = f1[f1.length - 1] + f2[f2.length-1];
            return newf;
        } 
    }

    //eleven eleven eleven eleven eleven eleven eleven eleven eleven eleven 


    //1111111111111 one
    public static int Doubler(int n){
        return n*2;
    }

    static void a() {//only static, means its tied to this class, and will work without an instance?
        System.out.println("here we start");
        System.out.println("and keep going");
    }

    public static void main(String[] args) {
        System.out.println("Hello World");

        Scanner scan = new Scanner(System.in);
        System.out.println("Which lecture number?");
        int lectureNumber = scan.nextInt();
        System.out.println();
        if (lectureNumber == 1) {
            int x = 10;
            {
                int y = 10;//scope of y is limited to section in which it is declared
                System.out.println(x+y);
            }
            
            //String cmdarg1 = args[0]; this pulls command line arguments
            String y = "100";
            int trials = Integer.parseInt(y);
            
            Scanner namescan = new Scanner(System.in);//create a new scanner object, names
            System.out.println("Enter name:");//userprompt
            String fname = namescan.nextLine();//nextLine accepts any input and casts to relevant type
            //if 
            System.out.println((fname));

            if (true){
                //java doesn't use 1 or 0 to represent true/false
                // || is the or operator, && is and
            } else if (true) {
                //something?
            }

            //declare a final, this is a static variable which in this case is only an instance var
            //if use final static double pi = ... the final is accessible universally
            //this is a good way to declare and track any constants
            final double pi = 3.14159265359;
            final double decim = 0.1;
            //how to format strings, ints and doubles
            //use printf rather than println, printformat
            System.out.printf("%.2f\n", pi);//%f is the key modifier for doubles, and \n is the newline
            System.out.printf("%21f\n", decim);

        }
    

        if (lectureNumber == 2) {
            //four types of loops
            //while
            int i = 10;
            while (i > 0) {
                i--;
            }

            //do while
            do {
                System.out.println("");
            } while (i > 0);//this always runs once, bc the condition is checked after the first execution

            //for
            //you don't need a curly when using a for loop with only one line
            for (int kj = 0; kj < 10; kj++) 
                //System.out.println(args[kj]);
            

            //for each -> special: no conditional checking, only usable for collections, and not usable with indexes
                //for(binding:collection){ 
            //collections = array, list, dic
            //uses a colon
            //binding is a var that represents an element of the collection
            for(String str:args) {
                System.out.println(str);
            }

            Doubler(30);  
            //static methods aka functions
            //method is a set of instructions bound to an object. if the method is static, the method belongs to the class in which it is defined
            //static return_type name (parameters){
            //if there is no return then void
            //can also have static final, final means it cannot be changed

            /*public static int addThree(int a, int b, int c) {
                return a + b + c;
            }
            */

            //when a method is executed it gets added to the call-stack
            //main is the first thing in it, then also the last thing by definition
            //recap: stacks: last in, first out, most recent is processed first

            //if you create a new class within a file, you have to create an instance of that class before calling it
            //before the main class, find the example class
            //example class contains the IsEven method, which can only be accessed once Example has been created
            //line 19
            Example e = new Example();
            //now we can call method IsEven
            e.IsEven(9);

            //arrays are stored in a contiguous block of memory
            //ints take 4 bytes of memory
            //any element in an array can be accessed by index if we know the any index in the array
            //eg 0x1000 is array[0], 0x1004 is 4 bytes over, its the next index
            //this is how we can easily access arrays by index in O(1), while other data structures cannot
            int[] arrayname = new int[16];
            //type[] array_name = new type[number of indices]
            
            //can also static init
            int[] collection = {1, 2, 3, 4};

            //reference type array
            //aka strings
            //because they take up more memory. also the array stores the memory address, the reference
            String[] names = new String[4];

            //default array values: byte, short, int, long default to 0
            //bool defaults false
            //float and double default to 0
            //char string and other reference type default to null character

            //length of array
            int arraynamelength = arrayname.length;//NOTE no length();

            //int [][] matrix = new int[columns][rows]

            //multidimensional arrays
            int[][] matrix = new int[3][3];
            //two types, matrix or jagged
            int [][] jagged = new int[3][];
            jagged[0] = new int[5];
            jagged[1] = new int[14];
            System.out.println(jagged[1][6]);

            //strings are special
            //strings are basically an array of characters
            //strings are immutable, they cannot be changed
            //if you change them, they're new strings
            //concatenation is allowed
            String cat = "meow";
            cat += "meow meow";//this is a new memory address
            String notdog = "meow meow meow";

            System.out.println(cat == notdog);//this is false
            //because they are created separately, the location is not the same

            String fish1 = "glug";
            String fish2 = "glug";
            //in this case fish1 == fish2 = true, because they are the same text
            String fish3 = new String("glug");
            //fish3 is different from 1 and 2, because it is forced to create a new memory address
            System.out.println(fish1.equals(fish3)); //= true, this checks the contents

            //java holds a string pool
            //it checks if the string has already been created, and will point reference types at those existing addresses to save space
            //can be circumvented with new String("word");

            //string.hashCode();
            //gives the hash value of the string, the location. this is an int, not hexadeximal
            fish1.hashCode();
            if (fish1.hashCode() == fish2.hashCode()) {
                System.out.println("fish1 and fish2 have the same hashcode, which is " + fish1.hashCode());
            }

            //StringBuilder is an inbuilt class
            //allows mutating strings but saving the same memory loc
            StringBuilder b = new StringBuilder("Hello");
            int place1 = b.hashCode();
            b.append(" World");
            int place2 = b.hashCode();

            if (place1 == place2) {
                System.out.println("StringBuilder saved the place");

            
            //THIS IS SIDE STUFF, MY OWN
            //to convert str to int, use str.charAt(i) -'0';
            //charAt uses ASCII, which will throw out numbers you don't want
            //since they're in order starting at 53 i think, if you just subtract 0 in ascii then they'll all drop to real value

            String bongo = "110100";
            int kk = bongo.charAt(1) - '0';
            //can also just run through bongo with a for loop, using str.length()
            
            }            
        }

        if (lectureNumber == 3) {

            //reference types are just classes
            //"class defines a type of object, a blueprint for definition. all objects of the same class have the same kinds of data and behaviour/methods"
            //we've been using inbuilt classes, Scanner, String, Stringbuilder

            //Point bottomleft = new Point(-1,-1);
            //the new keyword allocates memory and instantiates the object/instance of class
//we can make our own class
/*
public class cupcake {
    public boolean delicious;
    public String flavour;
    public boolean chalavi;

    //every class has a constructor, even if it isn't explicitly defined
    public cupcake() {
        delicious = true;
        flavour = "choccy";
        chalavi = true;
    }
}
*/
//see cupcake and bakery files

    //UML unified modelling language is visual language for designing classes
    //UML class diagrams
    //pseudocode for classes
    //title for class name
    //box for attribute names and types
    //the method names, parameters, and return types
    //plus and minus on the methods do designate public or private
    
        //this keyword means the vars belonging to the class
        //this.number = number; in a constructor
        //python uses self, same idea

            // I/O - input and output from files
            //we can use Scanner objects, plus classes for I/O
            //we need imports: java.io.File, and scanner
            //there MUST be a try catch for FileNotFoundException
            try {
                File files = new File("filename.txt");
                Scanner scanny = new Scanner(files);
                String firstSegment = scanny.next();
                scanny.next();//this will get the next string 
                
                //to write to file, use the PrintWriter class
                //PrintWriter is a io.PrintWriter;
                PrintWriter pw = new PrintWriter(files);
                pw.println("this is how you write to file");
                pw.close();
            } catch (FileNotFoundException e) {
                System.out.println("No file found");
            }      
        }



        if (lectureNumber == 4) {

            //read and write to binary
            //not properly this semester, but next, especially in image
            //.class is not human readable, but java virtual machine can access them
            //we will use DataInputStream and DataOutputStream and readChar, writeInt
            //these are io imports
            try {
                FileOutputStream f = new FileOutputStream("newfile.bin");
                DataOutputStream output = new DataOutputStream(f);
                output.writeInt(50);//writes as binary
                output.writeUTF("writes strings");
                output.close();

                FileInputStream ff = new FileInputStream("newfile.bin");
                DataInputStream input = new DataInputStream(ff);
                int rand = input.readInt();//reads from binary
                System.out.println(rand);
                String binaryoutput = input.readUTF();
                System.out.println("UTF is character encoding, unicode transformation format: " + binaryoutput);

            } catch (FileNotFoundException e ) {

            } catch (IOException e) {//this one is for DataOutputStream
                //this one is like FileNotFound, must have it
                //cannot catch two exceptions in the same catch
            }
            System.out.println();
            //in UNIX commands you can use xxd -b filename.extension to printout the binary code
                
            //stacks
            //everytime we call a method it is placed on a stack, the call stack
            //call stack size limit is 1MB

            //heap is a separate memory space for objects that are dynamically allocated
            //stacks are generally limited by scope, heaps are more dynamic and can be retained through separate scopes
            //if you init and array and then newarray = array, newarray has the exact same memory address and therefore same item
            //if using primitive types, like int, you'll copy the value NOT the address
            int ax = 5;
            int ay = ax;
            //these have different memory locations, but same values
            int[] aaz = new int[2];
            int[] aya = aaz;
            //aaz and aya are the exact same objects
            
            //garbage collector, like python
            //frees any unused memory and so we don't have to care about where things are
            //used to have to use delete method
            //this is relevant in C, C++
            
            //if you call new, you create a new memory or address
            //call a function that uses new twice, and you'll have a memory leak, where there is an object created that has no reference
            //set the thing to null and it will get garbage collected
            //when there is no reference or pointer to a memory address, the thing is gone
            System.out.println("aaz = " + aaz);
            aaz = null;
            System.out.println("aaz = " + aaz);
            //now the memory location of aaz has no way to be accessed, so the memory of it will be cleaned up, and now we cannot call aaz


            //collections, ADTs
            //ArrayList
            //resizable array, more like python ones
            //side: crt+d makes terminal stop taking input
            //List<type>name = new listType<type>(length);
            ArrayList<String> list = new ArrayList<String>(1);
            list.add("string 1");
            list.add("String 2");
            System.out.println(list);
            list.remove(1);
            list.add("placeholder, cos you deleted index 1 you can't set it");
            list.set(1,"updated s 2");
            list.set(0, "index 1 has been updated");
            System.out.println(list.get(1));
            list.size();
            //list.set(2,"you cannot set an index that doesn't already exist");

            ArrayList<Integer> numbers = new ArrayList<Integer>(4);

            //ArrayList creates a starting array, and when we call .add() onto a full array, the array is doubled
            
            //LinkedList has almost all the exact same methods as ArrayList
            //works almost the same on methods and external workings as ArrayList
            //its a linked list, value and pointer to next, aka nodes
            //wooow such surprise crazy
            //benefits are speeds at accessing things, see comp2123
            LinkedList<String> cars = new LinkedList<String>();//cannot pass in a length value
            cars.add("Volvo");
            cars.add("bmw");
            cars.add("jazzy af");
            String gilcarindex = cars.get(2);
            System.out.println(cars);

            //hashmap = dic in python
            //Map<Keytype,Valuetype> name = new Maptype<keytype, valuetype>();
            //set<type> name = new setType<type>;
            //maps are ordered, sets are unordered
            HashMap<String, Integer> planes = new HashMap<String, Integer>();
            //this maps planes by names to their capacity, int
            planes.put("747", 1200);//put instead of add
            planes.put("cessna", 6);
            planes.put("mi6", 80);
            planes.remove("cessna");
            planes.put("747", 1400);//putting again updates the value
            planes.containsKey("dreamliner"); //this is false
            int capac_747 = planes.get("747");
            System.out.println("hashmap of len  " + planes.size() + " : " + planes);
            //prints in format with key val pairs: {747=1200, mi6=80}
            //can also pull the list of keys with keySet() 
            ArrayList<String> planekeys = new ArrayList<String>(planes.keySet());

            //hashset
            HashSet<Integer> buslines = new HashSet<Integer>();
            buslines.add(10);
            buslines.add(15);
            buslines.add(45);
            System.out.println(buslines + " these were added in the order 10,15,45, hashset randomises");

            //if you don't give a type when creating an arraylist or type set collection
            //this will compile, and you can pass stuff to it, but only of the same type to the same index
            //the compiler will accept it and warn you about unsafe or unchecked op
            /*
            ArrayList bongodo = new ArrayList();
            bongodo.add(15);//index 0 is int
            bongodo.add("magic");//1 is string
            */

            //SIDE, NOT ACTUALLY FROM LECS BUT IMPORTANT
            //overloading a method
            //when you create two methods with the same names but different parameters
            //makes it easier to call a method when working with different types
            //eg a square() method for ints, doubles, longs, floats whatever
        }

        if (lectureNumber == 5) {
            //inheritance
            //classes are in a hierarchy, you can make a parent and child class
            //attribute and method reusability
            //allows sub-class methods
            //sub = child
            //override inherited methods
        
            //see before main, line 30
            System.out.println("Check before main. We learned about inheritance: super- and sub-classes, extensions, and exceptions. Also some notes down in file \n");
            
            //relationships between super and subclasses must be is-a; subclass is a superclass
            //Lion is-a Cat
            //has-a relationships are no good, make dif classes 
            //vehicle has-a tyre, make a tyre class separately

            //UML diagrams
            //unified modelling language
            //# in UML means protected
            //+ and - are public and private

            //remember that superclasses don't know about their subclasses
            //you CAN create a superclass object with a subclass constructor
            animal puppy = new dog();
            //even though the animal superconstructor takes a String arg, you pass it in the subconstructor super call
            //so you can use the dog constructor even though animal takes an arg
            //because this used the dog constructor, calling any overidden methods will use the subclass methods, but you don't have access to any other subclass methods
            puppy.getName();
            //but this would break
            //dog floof = new animal();
            //this isn't what Lists, ArrayLists etc do, thats an interface, see lec 6

            //exceptions, also up top
        }

        if (lectureNumber == 6) {
            //abstract classes and interfaces
            //66666666666666666666
            System.out.println("learned about abstractclasses, \n");
            //see lec 6 at top
            chair deckchair = new chair();
            deckchair.stack(deckchair);

            furniture table = new table();
            //because table and deckchair are abstract siblings, we know they MUST both have canSit method, so we can easily check them all
            furniture[] fList = {deckchair, table};
            for (furniture f: fList) {
                System.out.println("Can sit on: " + f.name + "? " + f.canSit());
            }
            //in UML, italicise the className, or write abstract next to it
            //abstract methods are also called polymorphic, because they can change their shapes
            
            //interfaces
            //all the methods in an interface will be abstract, and an object cannot be instantiated
            //but interfaces can be implemented by classes infinitely
            //interfaces are the has-a, while super/subclasses are is-a
            //cannot have attributes
            //
            //see up
            duck mall = new duck("mallard");
            cow coow = new cow("saltie");
            //since they both implement move, we can group them by interface
            talk[] talkers = {mall, coow};
            
            for (talk t : talkers) {
                System.out.println(t.talk());
            }
            System.out.println(mall.name + " eats " + mall.eat());

            //dolphin is a subclass of animal with interfaces eat, talk
            //extend comes first, then you implement
            dolphin flipper = new dolphin();
            System.out.println(flipper.getName());
            //because this implements talk, we could group it with talk[], or eat[]

            //now it makes sense why you can't use List<> l = new List<>(); 
            //cos List is an interface, ArrayList, LinkedList are subclasses implementing the List
            //also makes sense you can use the make an instance of
            List<Integer> ns = new ArrayList<Integer>();
            
            //UML to represent interface
            //<<interface>>, it will just say it
            //and all methods will be italicised, because they're all abstract
            //realisation link between interface and implementer subclass is a dotted line, as opposed to solid line of super subclasses
            
            //use a default method and override it
            tryingout tt = new tryingout();
            tt.explainer();
            
            //classpath: if you're compiling a program that has other classes or interfaces, but they're in subfolders
            //javac -cp.:superfilename/subfilename/:anotherfilename/
            //class files are created in the subfiles
            //you can to run using the same format
            //java cp.:filename/:anotherfile/

            //can also use packages
            //in the file you want as a package, use package foldername
            //then in the main file, you can import foldername.filename or .*

            //archives command
            //lets us compress files to export them
            //jar -cf myProgram.jar mainprogram.class
            //can also bundle other classes, if there are dependencies, just add: subfolder/*.class file.class whatev.class fuckoff.class
            //a jar file can be run, but cannot be opened, so the code is safe inside

            //to run a jar
            //java -jar program.jar
            //if the JVM doesn't know where main() is, you have to set the application entry point
            //vim program.jar
            //add Main-Class: programname into the manifest version

            //otherwise you can just bundle a .txt file that contains that line
            //then use jar -cfm programname.class entrypoint.txt 

            //gradle
            //sets up a default structure of folders and test files for writing a program
            //gradle init
            //this builds an app folder in cd
        }

        if (lectureNumber == 7) {
            System.out.println("Today: generics, ");
            //a list can hold strings or ints or anything
            //its a generic class
            //if it wasn't, we'd need ArrayListInt and ArrayListString
            //if we treat everything as Object, then it would be fine
            Object thingummywat = new Object();
            //but you can't use type specific methods on Object, even if it contains the correct type
            //ie pass an int to Object, you can't double it
            //<> signifies generic

            class container<T> {
                //now T represents the object type
                private T element;
                public container (T element) {
                    this.element = element;
                }
                public T set(T element) {
                    this.element = element;
                    return this.element;
                }
                public T get(){
                    return this.element;
                }
            }

            container<Integer> withInt = new container<Integer>(3);
            //System.out.println(withInt.get()); == 3
            
            //can also pass multiple args to generic
            
            //generic static methods
            //accesModifier static<param1> returnType methodName(param1)
            //see above main
            
            //UML
            //instead of having the name:string it will be attributeName:T
            //and top corner will say T:object

            //bounded type
            //we can bound the type parameter of generics
            //this uses extends <T extends superType>
            //meaning any superType object or child of it
            class calculator <T extends Number>{
                //this class can only take numbers
                //number is the supertype of byte, int, float, double, long, short

                //you can define an generic array
                private T[] array;
                //but if you try create a generic array object, you can't
                //java cannot handle generic arrays
                //but we can make an Object array and cast it into T

                @SuppressWarnings("unchecked")
                public calculator() {
                    array = (T[]) new Object[10];
                }
                //this gives an unchecked operation warning, but it works just fine
                //the @SuppressWarnings gets rid of it for the method following it
            }
            //iterable
            //for use in a for-each loop
            //generic interface, has 2 defaults and one Iterator() method
            //Iterator is an interface, 2 methods: next() and hasNext()

            //class myList<T extends Number> implements Iterable<T>{}//this is a list for only numbers that is iterable
            //using a linkedlist iterator is faster to traverse than using a for loop
            //cos you retain a pointer to the head
        }

        if (lectureNumber == 8) {
            System.out.println("Today: exceptions, enums, assert and JUnit");
            //checked exceptions vs unchecked
            //checked at the time of compilation, unchecked are runtime or type input errors etc
            //eg runtime -> index out of bounds, div by 0, access null
            //errors are anything that prevents program correct completion that isn't caught through unchecked errors, eg looping
            //exceptions and errors should be thrown when the preconditon of a method isn't correct
            //eg you could use NegativeIntegerException when a negative int is passed

            //accessModifier class ExceptionName extends Exception{}
            //accessModifier class ErrorName extends Error{}
            
            //exceptions are objects, they have to be instantiated with new, and thrown to output and freeze the program
            if (false) throw new RuntimeException();
            //instead of bricking the program you can
            try{
            } catch (IllegalStateException e) {
                System.out.println(e);//this just prints the exception raised
            }
            String profileName = "";
            try {
                profileName = "fuck";
                throw new RudeNameException();
                //RudeName is declared at top
            } catch (RudeNameException e) {
                System.out.println(e);
            }
            //this will throw an exception from the static method
            //System.out.println(passed(-1));
            
            //enums - enumerated type
            //a set of instances of the same type
            //cannot create an instance of enum
            //we can use enums where the number of instances is known/finite, eg enum months of a year, playing cards
            //accessModifier enum enumName{}
            //enums cannot be local, so see up
            System.out.println(category.average.ordinal());
            System.out.println(cards.hearts + " " + cards.hearts.getColour());

            //assert keyword
            //by default assert is disabled, you need to enable assertion checking, assert lines are ignored
            assert profileName != null;
            //when you run, type java -ea programName (- enable assertion)
            //you need to include this command every time you run

            //JUnit testing
            //really common testing framework, allows checking all methods
            //black box vs white box, whether we know/care what the internals are doing
            //needs junit.jar and hamcrest.jar, these contain all the classes needed 
            //create a new file for testing, with imports: static org.junit.Assert.*; and org.junit.Test;
            //@Test before each test method
            //can use stuff like assertNull(), assertTrue(), assertEquals(expected,actual), assertSame(object, obj)
            //then compile with javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar testFileName.java
            //run with java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore testFileName
            //java -cp .: file:file2 className testFileName
            //this will output the time for tests and how many test methods passed, you can put multiple tests in 1 method obvi
        }

        if (lectureNumber == 9) {
            System.out.println("TODAY: recursion, caching, documentation");
            //base case, recursive case/s
            //inefficient with memory
            System.out.println("using factorial recursion 7! = " + factorial(7));
            System.out.println("7th val in fibonacci is: " + fibonacci(7));
            System.out.println("8th val in fibonacci is: " + fibonacci(8));
            int[] seq = fiboGen(7);
            for (int n: seq) 
                System.out.print(n + " ");
            System.out.println("");
            //recursion with OOP is common with linked data structures, like trees, linked list, graph stacks heaps
            //in a tree storing family member names and ArrayList of children
            //see fam.java for more

            //memoization
            //aka caching. we store a result to use it later, to make recursion/calculations more efficient
            //eg function(3) = 8 is stored if it comes up again
            //new object called cache
            //use a map or similar, and if cache.contains() then return the value
            //public Map<Integer, int[]> cache = new Map<Integer, int[]>();
            //then at the start of each recursive call:
            //if (cache.contains(n)) return the result; else { do the calc
            //and when a new item is calculated then put it in the cache cache.put(n, result)
            //sacrifices space for time
            long startTime = System.currentTimeMillis();
            System.out.println(System.currentTimeMillis() - startTime);
            
            //documentation
            //comments, use good detailing, clear method names
            //javadoc is a documentation generator for java, extracts comments formatted correctly
            //** */ anything within these symbols will be taken by javadoc
            //can include eg @param value the value of item 
            //to designate param, similarly @return, @see (also other ones, links), @since, @depracated
            //to create a javadoc: javadoc -d directoryName javaFile.java
            //will create a bunch of .js and .html, we usually only want index.html or index-all.html
            //this can create heaps of warnings, if methods aren't commented or if not all @params are mentioned, but thats ok

        }

        if (lectureNumber == 10) {
            System.out.println("today: anonymous classes, lambdas");
            
            //anonymous classes have no name, no constructor because that needs name
            //only one instance is created at runtime, they're essentially subclasses
            
            //fish is a subclass of animal, with an extra breathe() method
            //note the semicolon at the end!!! on top of the curly
            animal fish = new animal("fish") {
                public void breathe() {
                    System.out.println("gulp");
                }
            };
            //using anon classes means that you don't need to init an entirely new class, which creates a class file of its own, and takes fewer lines
            //its popular in GUIs, make an abstract button class and then use anon button classes for the different functions
            //we can also make an anon class that uses an interface as a superclass, which seems a little illegal
            
            //make a minuser class of an interface subtractor
            subtractor minuser = new subtractor() {//line 1
                public int subtractify(int x, int y) {//line 2
                    return x - y;
                }
            };
            int julio = minuser.subtractify(7,6);
            //also good for calc, eg create a hashmap of String, operations, and for each op, do map.put(func, op)
            //then to use do maps.get("ADD").apply()
            //basically they're good for things where you might need to do alot of similar operations, esp with the same param

            
            //lambdas expression: java 8 and later
            //only for an interface that only declares 1 abstract method: aka functional interface
            //since there is only 1 method, you don't strictly need the name, cos it can only be that 1 method
            //(param, param) -> functionBody
            //since the method defines the params, you don't need to give the type, probably clearer to do so
            HashMap<String, maths> operations = new HashMap<>();
            //make a hashmap for string operator names and maths operator function objects, anon classes implementing a maths interface
            operations.put("ADD", (int x, int y) -> x + y);
            operations.put("MUL", (x, y) -> x * y);
            System.out.println("using hashmap/lambda calculator, 1+3= " +operations.get("ADD").operation(1,3));
            operations.put("SUB", (x,y) -> x - y);
            //basically see up at subtractor(), lambdas get rid of line 1 and 2
            //this uses a functional interface, which is when interface only had 1 method

            //functionalInterface lambdaName = (input) -> result;
            greeting arabic = () -> "ahalan";
            System.out.println(arabic.sayHi());
            greeting ivrit = () -> "mah koreh";
            //functionalInterface is greeting, aussie is the lambda
            //lambdas can have multiple lines
            greeting aussie = () -> "gday";

            //functional interface only has 1 abstract, if it has default methods we cannot use the defaults in lambda expression
            //you can use lambda inside a default expression but not the other way around

            //when you compile, the anonymous class creates a classfile but anon
            //eg infolecs1113$1.class, infolecs1113$2.class 
            //but for lambda, it will only do infolecs1113Lamba.class
            //saves space
        }

        if (lectureNumber == 11) {
            System.out.println("today: wildcards, debugging"); 
            
            //standard arrays can cast from Object to other types
            Object[] namesOfTree = new String[4];
            namesOfTree[0] = "oak";
            System.out.println(namesOfTree[0]);
            //but we cannot use ArrayLists like this
            //this is because of parent and child class constructors and generics
            
            //wildcards: generics
            //with generics, we cannot assign a type where the initilisation specifies a lower bound
            //like ArrayList<Object> list = new ArrayList<String>();
            //eg we can only use parents of the type, but not children
            //upper and lower bound are flipped, the 'root'/grandparent is the lower bound
            //see up, Person Employee
            List<Employee> workers = new ArrayList<Employee>();
            workers.add(new Employee("mas", 1));

            //we CANNOT pass this Employee list to a function with param Person, even though they are sub/supers
            Person p = new Person("mark");
            p.readPeople(workers);
            //instead, use wildcard <?> and lower or upper bound tags
            //Type<? super lowerBound> variable
            //Type<? extends upperBound> variable
            //List<?> j = new ArrayList<? extends Person>();
            //both are inclusive
            //when there is no bound, there is an unbounded wildcard, its extends object
            //List<?> == List<? extends Object>

            //however, when using an upperbound, we can't write to the object, only read
            //cos we won't know which child is what, if we pass a Customer to a method that takes Person, we might try use Employee or something
            //if we had a list of Media objects, books->magazines, videos, we could addBook to the list List<? super Book> and this would also be able to use magazine
            //<? super Thing> means anything Thing or any of its superclasses

            //debugging
            //logic errors are hard to find cos everything seems to work, syntactical errors are easy
            //JDB - java de bugger
            //run from terminal, commands input there too
            //can inspect current values of local values, pause execution and run 1 line at a time, see object and class instances
            //jdb programName, will then ask for breakpoints and stuff
            //we need to set up breakpoints to tell JDB to pause
            //stop at Program:lineNum, dump, locals, set, print
            //when you stop at lineNum, that line hasn't been executed yet
            //so locals will not show vars from there, but we can call step to go next
            
            //if using Scanner, there are problems
            //we can run the program to a local server, and this will let us pass inputs through another terminal that connects to that server

        }


        if (lectureNumber == 0) {
            //this stuff is separate, just random bits and things
            
            //find the char at position in a string
            String myName = "stanley";
            char letter = myName.charAt(0);
            String nameLetter = Character.toString(letter);
            
            //can also convert to int
            String ages = "21";
            int b = ages.charAt(0) - '0';
            //this uses ASCII ascii

            //accessModifier strengths
            /**public
             * protected
             * default
             * private
             * 
             * Java is pro-FOI, meaning overrides can only make the overidden method more accessible, or stronger
             * otherwise we get compile-time error
             */

            //post/pre increment and assign
            int p = 0;
            int u = p++;
            int r = ++p;
            //u == 0, r == 1 == p
             
        }



    }//end of main
}//end of infolecs1113