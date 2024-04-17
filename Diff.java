import java.util.*;

public class Diff {
    static ArrayList<Integer> arr = new ArrayList<>();
    ArrayList<Integer> last_idx = new ArrayList<>();
    ArrayList<Integer> diff = new ArrayList<>();
    ArrayList<Integer> prevDiff = new ArrayList<>();
    
    public void getList(int n){
        Scanner input = new Scanner(System.in);
        boolean removeSuccess = false;
            do{
                int idx = 0;
                System.out.println("Enter the Integer pattern to be solved, Enter q when done, Enter remove to \"remove\" an element.");
                if(!input.hasNextInt()){
                    String op = input.next().trim().toLowerCase();
                    if(op.equals("q")){
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
                            if(idx > arr.size() || idx < 0){
                                System.out.println("index entered out of bounds");
                                System.out.println("Please enter index of element to be removed: ");
                                while(!input.hasNextInt()){
                                        System.out.println("Please enter index of element to be removed: ");
                                        input.next();
                                }
                                idx = input.nextInt();
                            }                       
                            }
                                input.nextLine();
                                arr.remove(idx);
                                System.out.print("List: ");
                                for(int i : arr){
                                System.out.print(i + " ");
                                }
                                System.out.println(); 
                                removeSuccess = true;
                        }
                    }else{
                    arr.add(input.nextInt());
                    System.out.print("List: ");
                    for(int i : arr){
                        System.out.print(i + " ");
                    }
                }
                input.nextLine();
                System.out.println();
            }while(true);
        }   

        
    

    public int findNextTerm(ArrayList<Integer> n) {
        if (n.get(0) == n.get(1)) {
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
            for (Integer i : diff) {
                System.out.print(i + " ");
            }
            System.out.println();
            return findNextTerm(diff); // Return the result of the recursive call
        }
    }

    public static void main(String[] args) {
        Diff obj = new Diff();
        obj.getList(0);
        arr.set(arr.size() - 1, obj.findNextTerm(arr));
        System.out.println("Next term: " + arr.get(arr.size() - 1));
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
    }
}

