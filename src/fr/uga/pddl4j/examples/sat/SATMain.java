package fr.uga.pddl4j.examples.sat;

import fr.uga.pddl4j.parser.DefaultParsedProblem;
import fr.uga.pddl4j.plan.Plan;
import fr.uga.pddl4j.plan.SequentialPlan;
import fr.uga.pddl4j.planners.AbstractPlanner;
import fr.uga.pddl4j.problem.DefaultProblem;
import fr.uga.pddl4j.problem.Problem;
import fr.uga.pddl4j.problem.operator.Action;

import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sat4j.core.VecInt;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import picocli.CommandLine;


/**
 * The class is an example. It shows how to create a simple A* search planner able to
 * solve an ADL problem by choosing the heuristic to used and its weight.
 *
 * @author D. Pellier
 * @version 4.0 - 30.11.2021
 */
@CommandLine.Command(name = "ASP",
        version = "ASP 1.0",
        description = "Solves a specified planning problem using A* search strategy.",
        sortOptions = false,
        mixinStandardHelpOptions = true,
        headerHeading = "Usage:%n",
        synopsisHeading = "%n",
        descriptionHeading = "%nDescription:%n%n",
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n")
public class SATMain extends AbstractPlanner {

    private static final long serialVersionUID = 1L;
	/**
     * The class logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(SATMain.class.getName());

    /**
     * Instantiates the planning problem from a parsed problem.
     *
     * @param problem the problem to instantiate.
     * @return the instantiated planning problem or null if the problem cannot be instantiated.
     */
    @Override
    public Problem instantiate(DefaultParsedProblem problem) {
        final Problem pb = new DefaultProblem(problem);
        pb.instantiate();

        return pb;
    }

    /**
     * Search a solution plan to a specified domain and problem using A*.
     *
     * @param problem the problem to solve.
     * @return the plan found or null if no plan was found.
     */
    @Override
    public Plan solve(final Problem problem) {
//        System.out.println("mi22 test"+problem.getActions());
        SATEncoding satEncoding = new SATEncoding(problem);
        List<List<Integer>> clauses = satEncoding.Encode(1);
//        List<List<Integer>> clauses = new ArrayList<>();
//        List<Integer> clause1 = new ArrayList<>();
//        clause1.add(-1);
//        clause1.add(-2);
//        List<Integer> clause2 = new ArrayList<>();
//        clause2.add(1);
//        List<Integer> clause3 = new ArrayList<>();
//        clause3.add(-2);
//        clauses.add(clause1);
//        clauses.add(clause2);
//        clauses.add(clause3);
        ISolver solver = SolverFactory.newDefault();
        solver.setTimeout(300); // 1 hour timeout
        final int MAXVAR = 1000000;
        final int NBCLAUSES = 500000;

        
        // prepare the solver to accept MAXVAR variables. MANDATORY for MAXSAT solving
        solver.newVar(MAXVAR);
        solver.setExpectedNumberOfClauses(NBCLAUSES);
        // Feed the solver using Dimacs format, using arrays of int
        // (best option to avoid dependencies on SAT4J IVecInt)
        
        
        for (List<Integer> clause : clauses) {
            int[] intClause = clause.stream().mapToInt(i -> i).toArray();
            try {
            	VecInt v = new VecInt(intClause);
            	System.out.println(" heeooo   "+v);
				solver.addClause(v);
				
			} catch (ContradictionException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
        }

        // we are done. Working now on the IProblem interface
//        IProblem problem = solver;
//        if (problem.isSatisfiable()) {
//           ....
//        } else {
//         ...
//        }
     // Find a satisfying assignment (a plan)
        
        List<Integer> plan = new ArrayList<Integer>();
        try {
            if (solver.isSatisfiable()) {
                int[] model = solver.model();
                for (int literal : model) {
                    if (literal > 0) {
                        plan.add(literal);
                    }
                }
            }
        } catch (TimeoutException e) {
            // Handle timeout exception
            e.printStackTrace();
        }
       
        int t =(problem.getActions().size()+problem.getFluents().size());
        Plan newPlan = new SequentialPlan();
        
        for(int literal : plan) {
        	int actionId = (literal%t) - problem.getFluents().size();
        	if( actionId>= problem.getFluents().size()) {
        		Action a = problem.getActions().get(actionId-1);
        		
        		 newPlan.add(Math.round(literal/t), a);
        	}
        	
        }
        
        
        return newPlan;
    }

    @Override
    public boolean isSupported(Problem problem) {
        return false;
    }

    /**
     * The main method of the <code>ASP</code> planner.
     *
     * @param args the arguments of the command line.
     */
    public static void main(String[] args) {
        try {
        	
            final SATMain planner = new SATMain();
    
            CommandLine cmd = new CommandLine(planner);
            cmd.execute(args);
            
        } catch (IllegalArgumentException e) {
            LOGGER.fatal(e.getMessage());
        }
    }
}