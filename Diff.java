import java.util.*;

public class Diff {
    static ArrayList<Integer> arr = new ArrayList<>();
    ArrayList<Integer> last_idx = new ArrayList<>();
    ArrayList<Integer> diff = new ArrayList<>();
    ArrayList<Integer> prevDiff = new ArrayList<>();
    int diffCout = 1;
    Scanner input = new Scanner(System.in);
    
    public void printIntList(ArrayList<Integer> n){
        System.out.print("List: ");
        for (Integer i : n) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    
    public void statusCheck(){
        input.nextLine();
        String option;
        do{
          System.out.println("Enter \"y\" to continue, \"n\" to exit.");  
            option = input.next().toLowerCase().trim();
          if(!option.equals("y") && !option.equals("n")){
            System.out.println("invalid input");
            continue;
          }else if(option.equals("y")){
            diffCout = 1;
            arr.clear();
            getList();
            findNextTerm(arr);
            System.out.println("Next term: " + arr.get(arr.size() - 1));
            printIntList(arr);
          }else{
            System.out.println("Bye!");
            System.exit(0);
          }
        }while(true);     
    } 
    public void getList(){
            do{
                int idx = 0;
                System.out.println("Enter the Integer pattern to be solved, Enter q when done, Enter remove to \"remove\" an element, Enter \"quit\" to quit the program.");
                if(!input.hasNextInt()){
                    String op = input.next().trim().toLowerCase();
                    if(op.equals("quit")){
                        System.out.println("Bye!");
                        System.exit(1);
                    }else if(op.equals("q")){
                        break;
                    }else if(op.equals("remove")){
                        if(arr.size() == 0){
                            System.out.println("No elements entered!");
                            continue;
                        }
                        System.out.print("Please enter index of element to be removed: ");
                            if(!input.hasNextInt()){
                                while(!input.hasNextInt()){
                                        System.out.print("Please enter index of element to be removed: ");
                                        input.next();
                                }
                                idx = input.nextInt();
                            }
                            idx = input.nextInt();
                            if(idx > arr.size() || idx < 0){
                                System.out.println("index entered out of bounds");
                                System.out.print("Please enter index of element to be removed: ");
                                while(!input.hasNextInt()){
                                        System.out.print("Please enter index of element to be removed: ");
                                        input.next();
                                }
                                idx = input.nextInt(); 
                            } 
                                input.nextLine();
                                arr.remove(idx);
                                printIntList(arr);
                                continue;                      
                            
                        }else if(op.equals("edit")){
                            if(arr.size() <= 0){
                                System.out.println("No elements in list!");
                            }
                            System.out.print("enter element index to be edited: ");
                            while (!input.hasNextInt()) {
                            System.out.println("Please enter a number!"); 
                            input.next();
                            }
                            idx = input.nextInt();
                            while(idx >= arr.size() || idx < 0){
                                System.out.println("Entered index out of bounds!");
                                System.out.print("Enter element index to be edited: ");
                                while(!input.hasNextInt()){
                                    System.out.print("Please enter a number!: ");
                                    input.next();
                                }
                                idx = input.nextInt();
                            }
                                System.out.print("Enter value: ");
                                while (!input.hasNextInt()) {
                                    System.out.print("Please enter a number!: ");
                                    input.next();
                                }
                                int value = input.nextInt();
                                arr.set(idx, value);
                                printIntList(arr);
                                continue;
                            
                        }
                }else{
                    arr.add(input.nextInt());
                    printIntList(arr);
                }
                input.nextLine();
            
        }while(true);      
    }

        
    

    public int findNextTerm(ArrayList<Integer> n) {
        boolean c = false;
        if(diff.size() <= 1 && diffCout > 1){
            throw new ArithmeticException("Next term cant be found");
        }
        if(n.get(0) == n.get(1)){
            for(int i = 0; i < diff.size(); i++){
                if(diff.get(0) == diff.get(i)){
                    c = true;
                }else{
                    c = false;
                }
            }
        }
        if (c == true) {
            if(last_idx.size() <= 0){
                return arr.get(0);
            }
            Collections.sort(last_idx);
            for (int i = 1; i < last_idx.size(); i++) {
                last_idx.set(0, last_idx.get(i) + last_idx.get(0));
                
            }
            return arr.get(arr.size() - 1) + last_idx.get(0);
        } else {
            prevDiff.clear();
            prevDiff.addAll(n);
            diff.clear();
            for (int i = 0, j = 1; j < prevDiff.size(); i++, j++) {
                diff.add(prevDiff.get(j) - prevDiff.get(i)); // Initialize and add the difference to the list
            }
            last_idx.add(diff.get(diff.size() - 1));
            for(int i = diffCout - 1; i < diffCout; i++){
                System.out.print(diffCout + " difference ");
                if(diffCout >= 2){
                    int tmp = 0;
                    do{
                        System.out.print(" ");
                        tmp++;
                    }while(tmp < diffCout);
                }
                for (Integer j : diff) {
                    System.out.print(" " + j + "  ");
                }
                System.out.println();
            }
            diffCout++;
            return findNextTerm(diff); // Return the result of the recursive call
        }
    }

    public static void main(String[] args) {
        Diff obj = new Diff();
        obj.getList();
        arr.add(obj.findNextTerm(arr));
        System.out.println("Next term: " + arr.get(arr.size() - 1));
        obj.printIntList(arr);
        System.out.println();
        obj.statusCheck();
    }
}

