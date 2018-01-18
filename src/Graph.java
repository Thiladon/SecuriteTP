import java.util.*;

public class Graph {
	private HashMap<Machine, ArrayList<Machine>> machineMap;

	public Graph(HashMap<Machine, ArrayList<Machine>> machineMap) {
		this.machineMap = machineMap;
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
		Machine machine1 = new Machine();
		Machine machine2 = new Machine();
		Machine machine3 = new Machine();
		Machine machine4 = new Machine();

		HashMap<Machine, ArrayList<Machine>> map = new HashMap<>();
		map.put(machine1, new ArrayList<>(Collections.singletonList(machine2)));
		map.put(machine2, new ArrayList<>(Arrays.asList(machine1, machine3, machine4)));
		map.put(machine3, new ArrayList<>(Collections.singletonList(machine2)));
		map.put(machine4, new ArrayList<>(Collections.singletonList(machine2)));

		Graph graph = new Graph(map);
		System.out.println(graph);

		graph.removeLink(machine1, machine2);

		System.out.println(graph);
	}
}
