package fr.uga.pddl4j.examples.sat;


import fr.uga.pddl4j.problem.Problem;
import fr.uga.pddl4j.problem.operator.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SATEncoding {
	Problem p;

	public SATEncoding(Problem problem){

		this.p = problem;
	}
	public List<List<Integer>> Encode(int nbsteps){


		List<List<Integer>> clauses = new ArrayList<>();
		int t =(p.getActions().size()+p.getFluents().size());
		for (int k=0; k<p.getFluents().size();k++) {
			List<Integer> clInit= new ArrayList<>();
			if(p.getInitialState().getPositiveFluents().get(k)) {
				clInit.add(k+1);
				clauses.add(clInit);
			}else {
				clInit.add(0-k-1);
				clauses.add(clInit);
			}

		}


		//parcourir les actions
		int fSize = p.getFluents().size();
		for(int step=0; step<nbsteps; step++) {
			for(int d =fSize+t*step; d<t*(step+1); d++) {

				//fix to debug
				Action a = p.getActions().get(d-(t*step)-fSize);
				//if(a.getUnconditionalEffect().getNegativeFluents().get(0)!= a.getUnconditionalEffect().getPositiveFluents().get(0)) {


					//parcourir les preconditions de chaque action
					String pStringValues = a.getPrecondition().getPositiveFluents().toString();
					String[] preconditions = pStringValues.substring(1, pStringValues.length() - 1).split(", ");
					for(int e=0; e< preconditions.length; e++) {
						List<Integer> cl= new ArrayList<>();
						cl.add(-d-1);
						cl.add((Integer.parseInt(preconditions[e])+1)+t*step);
						clauses.add(cl);

					}

					//parcourir les effets positifs
					String pEffetPositifs = a.getUnconditionalEffect().getPositiveFluents().toString();
					String[] effetpositifs = pEffetPositifs.substring(1, pEffetPositifs.length() - 1).split(", ");
					String pEffetNegatifs = a.getUnconditionalEffect().getNegativeFluents().toString();
					String[] effetNegatifs = pEffetNegatifs.substring(1, pEffetNegatifs.length() - 1).split(", ");

					for(int e=0; e< effetpositifs.length; e++) {
						if(!Arrays.asList(effetNegatifs).contains(effetpositifs[e])) {
							List<Integer> cl= new ArrayList<>();
							cl.add(-d-1);
							cl.add((Integer.parseInt(effetpositifs[e])+1)+t*(step+1));
							clauses.add(cl);
						}

					}
					//parcourir les effets negatifs

					for(int e=0; e< effetNegatifs.length; e++) {
						if(!Arrays.asList(effetpositifs).contains(effetNegatifs[e])) {
							List<Integer> cl= new ArrayList<>();
							cl.add(-d-1);
							int result = (Integer.parseInt(effetNegatifs[e])+1)+t*(step+1);
							cl.add(0-result);
							clauses.add(cl);
						}

					}


				//}
			}

		}
		//fluentAction contient une liste de Fluent contenant chaque action qui peut les modifier 
		HashMap<Integer, List<Integer>> fluentAction = new HashMap<>();
		for(int clause =p.getFluents().size(); clause < clauses.size();clause++) {
			List<Integer> cl = clauses.get(clause);
			for(int fluent = 1; fluent<cl.size(); fluent++) {
				List<Integer> tmp = new ArrayList<>();
				if(fluentAction.containsKey(cl.get(fluent))) {
					fluentAction.get(cl.get(fluent)).add(0-cl.get(0));
				}
				else {
					
					tmp.add(0-cl.get(0));
					fluentAction.put(cl.get(fluent), tmp);
				}
			}


		}

		//parcourir les fluents positifs 
		for(Integer key: fluentAction.keySet()) {
			int next = key+t; 
			List<Integer> numValue =  fluentAction.get(next);
			List<Integer> numValue2 =  fluentAction.get(-next);
			List<Integer> cl = new ArrayList<>();
			List<Integer> cl2 = new ArrayList<>();


			cl.add(key);
			cl.add(0-(key+t));

			cl2.add(0-(key));
			cl2.add(key+t);

			if(key>((t-1)*nbsteps) ){

			}else {
				if(numValue != null) {
					for(Integer num : numValue) {
						cl.add(num);
					}
					if(Math.abs(cl.get(0))< Math.abs(cl.get(1))) {
						clauses.add(cl);
					}
					
				}
				if(numValue2 != null) {
					for(Integer num : numValue2) {
						cl2.add(num);

					}
					if(Math.abs(cl2.get(0))< Math.abs(cl2.get(1))) {
						clauses.add(cl2);
					};
				} 

			}
		}

		//		//Goal
				for (int k=0; k<p.getFluents().size();k++) {
					List<Integer> cl= new ArrayList<>();
					if(p.getGoal().getPositiveFluents().get(k)) {
						cl.add(k+1+(t*nbsteps));
						clauses.add(cl);
					}else {
						cl.add(0-k-1-(t*nbsteps));
						clauses.add(cl);
					}
					
				}

		// Disjunction
				for(int step=0; step<nbsteps; step++) {
					List<Integer> cl= new ArrayList<>();
					for(int d =fSize+t*step; d<t*(step+1); d++) {
						cl.add(-d-1);
					}
					clauses.add(cl);
				}

		return clauses;
	}
}
