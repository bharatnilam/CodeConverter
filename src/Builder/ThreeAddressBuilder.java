package Builder;

import Elements.*;
import java.util.ArrayList;
public class ThreeAddressBuilder {
    public static ArrayList<Instruction> Build(ArrayList<String> lines){
        ArrayList<Instruction> code = new ArrayList<>();
        int start = 1;
        TreeBuilder tb = new TreeBuilder();
        for(String exp : lines){
            ArrayList<Instruction> i = new ArrayList<>();
            String[] s = exp.split("=");
            Node n = tb.GenerateTree(s[1].trim());
            n = normalize(n);
            ExprBuilder.Parse(i, n, start);
            i.get(i.size()-1).LHS = s[0].trim();
            i.get(i.size()-1).isTemp = false;
            code.addAll(i);
            start = LastNumbered(code) + 1;
        }
        return code;
    }
    public static Node normalize(Node n){
        if(n.left == null && n.right == null){
            Node t = new Node("+");
            t.left = n;
            t.right = new Node("0");
            return t;
        }
        return n;
    }
    public static int LastNumbered(ArrayList<Instruction> ins){
        for(int i = ins.size()-1; i >= 0; i--){
            if(!ins.get(i).LHS.startsWith("t_")) continue;
            return Integer.parseInt(ins.get(i).LHS.substring(2));
        }
        return 0;
    }
}