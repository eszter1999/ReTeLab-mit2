package hu.bme.mit.yakindu.analysis.workhere;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.yakindu.sct.model.sgraph.State;
import org.yakindu.sct.model.sgraph.Statechart;
import org.yakindu.sct.model.sgraph.Transition;

import hu.bme.mit.model2gml.Model2GML;
import hu.bme.mit.yakindu.analysis.modelmanager.ModelManager;

public class Main {
	@Test
	public void test() {
		main(new String[0]);
	}
	
	public static void main(String[] args) {
		ModelManager manager = new ModelManager();
		Model2GML model2gml = new Model2GML();
		
		// Loading model
		EObject root = manager.loadModel("model_input/example.sct");
		
		// Reading model
		Statechart s = (Statechart) root;
		TreeIterator<EObject> iterator = s.eAllContents();
		while (iterator.hasNext()) {
			EObject content = iterator.next();
			if(content instanceof State) {
				State state = (State) content;
				System.out.println(state.getName());
				EList<Transition> starts = state.getIncomingTransitions();
				for(int i = 0; i < starts.size();i++)
					System.out.println(starts.get(i).getSource().getName() + "->" + starts.get(i).getTarget().getName());
				EList<Transition> ends = state.getOutgoingTransitions();
				for(int i = 0; i < ends.size();i++)
					System.out.println(ends.get(i).getSource().getName() + "->" + ends.get(i).getTarget().getName());
			}
			//System.out.println();
			if(content instanceof State) {
				State state = (State) content;
				if (state.getOutgoingTransitions().isEmpty() )
					System.out.println(state.getName());
			}
			//System.out.println();
			int i=0;
			if(content instanceof State) {
				State state = (State) content;
				if(state.getName() =="") {
					System.out.println("State"+i);
					i++;
				}
			}
		}
		
		// Transforming the model into a graph representation
		String content = model2gml.transform(root);
		// and saving it
		manager.saveFile("model_output/graph.gml", content);
	}
}
