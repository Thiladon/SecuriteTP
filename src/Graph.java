import java.util.*;

public class Graph {
	private HashMap<Machine, ArrayList<Machine>> machineMap;

	public Graph() {
		machineMap = new HashMap<>();
	}

	private void generateMachineMap(int nbMachines){
		ArrayList<Machine> machines = new ArrayList<>();

		for( int i = 0; i < nbMachines; i++)
			machines.add(new Machine());

		for (Machine machine : machines){
			int nbLinkedMachines = (int) (Math.random()*nbMachines) + 1;

			while (nbLinkedMachines != 0){
				Machine selectedMachine = machines.get((int) (Math.random()*nbMachines));

				if (machine != selectedMachine && !machine.getLinkedMachines().contains(selectedMachine)){
					machine.addLinkedMachine(selectedMachine);
					selectedMachine.addLinkedMachine(machine);
				}

				nbLinkedMachines--;
			}

			machineMap.put(machine, machine.getLinkedMachines());
		}
	}

	private void removeLink(Machine machine1, Machine machine2){
		List<Machine> toRemove = new ArrayList<>();

		if (machineMap.containsKey(machine1))
			for(Machine machine : machineMap.get(machine1))
				if (machine == machine2)
					toRemove.add(machine2);

		for (Machine machine : toRemove)
			machineMap.get(machine1).remove(machine);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Machine machine : machineMap.keySet()) {
			res.append("Machine's ID = ").append(System.identityHashCode(machine)).append("\t Machine.s liée.s : ");
			for (Machine machine1 : machineMap.get(machine))
				res.append(System.identityHashCode(machine1)).append(" \t");
			res.append("\n");
		}

		return String.valueOf(res);
	}

	public static void main(String[] args){
		Graph graph = new Graph();
		graph.generateMachineMap(5);

		System.out.println(graph);
	}
}
