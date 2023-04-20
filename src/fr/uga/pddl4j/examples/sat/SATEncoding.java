package fr.uga.pddl4j.examples.sat;

import fr.uga.pddl4j.problem.Fluent;
import fr.uga.pddl4j.problem.Problem;
import fr.uga.pddl4j.problem.operator.Action;
import fr.uga.pddl4j.problem.operator.Condition;
import fr.uga.pddl4j.util.BitVector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SATEncoding {
    Problem p;
    public SATEncoding(Problem problem){

        this.p = problem;
    }
    public List<List<String>> Encode(int nbsteps){
        List<Object> ob = new ArrayList<>();
        
        
        for(int i=0;i<nbsteps;i++) {
	        for (int j = 0; j < p.getActions().size(); j++) {
	            Action a = p.getActions().get(j);
	            ob.add(a);
	        }
	        for (int j=0;j < p.getFluents().size();j++) {
	        	Fluent f = p.getFluents().get(j);
	        	ob.add(f);
	        }
        }
        
        //ob contient la liste des action et des fluent pour chaque étape
        //on construit les clause
        for(int c=0;c<ob.size();c++) {
        	int depth = (c / (p.getActions().size()+p.getFluents().size()))-1;//pb
        	System.out.print("depht "+depth);
        	if(ob.get(c).getClass()==Action.class) {
        		
        	}
        }
        
        
        return null;
    }

}
//    BitVector pos = a.getUnconditionalEffect().getPositiveFluents();
//    BitVector neg = a.getUnconditionalEffect().getNegativeFluents();
//    BitVector precondneg =  a.getPrecondition().getNegativeFluents();
//    BitVector precondpos = a.getPrecondition().getPositiveFluents();
//int[] param = a.getParameters();
//List<String> predicateSymbols = p.getPredicateSymbols();
//// System.out.println(predicateSymbols);
//Condition goal = p.getGoal();
//List<String> types =  p.getTypes();
//for (int i = 0; i < p.getTypes().size(); i++) {
////    System.out.println(p.getTypes().get(i).toString());
//}
//
