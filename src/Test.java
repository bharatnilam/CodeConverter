import Builder.*;
import Elements.*;
import Optimize.Optimizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static int MAX_ROUNDS = 25;
    public static void main(String[] args) throws FileNotFoundException{
        File file = null;
        Scanner scan = new Scanner(System.in);
        while(file == null){
            System.out.print("Instruction File Path: ");
            file = new File(scan.nextLine());
            if(!file.exists() || !file.isFile()){ 
                file = null; 
                continue;
            }
        }
        scan = new Scanner(file);
        ArrayList<String> lines = new ArrayList<>();
        System.out.println("Input File Content:");
        while(scan.hasNextLine()){
            lines.add(scan.nextLine());
            System.out.println(lines.get(lines.size()-1));
        }
        ArrayList<Instruction> ins =  ThreeAddressBuilder.Build(lines);
        System.out.println("\n\nUnoptimized 3-Address Code:");
        for(Instruction i : ins){
            System.out.println(i.toString());
        }
        Optimizer op = new Optimizer();
        boolean optimized = true;
        int i = 1;
        while(optimized && i < MAX_ROUNDS){   
            optimized = false;
            System.out.println("\n\nCode after " + i + " optimization round:");
            optimized = op.Optimize(ins);
            System.out.println(Optimizer.ToText(ins, 1));
            i++;
        }
        if(optimized) System.out.println("\n\nMaximum number of rounds is limited to " + MAX_ROUNDS);
    }
}