import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Graph class representing a graph of Machine.
 *
 * @version 2018-01-27
 * @see Machine
 */

public class Graph {
	/**
	 * Hashmap having machines on key and an ArrayList representing the linked machines on value.
	 *
	 * @see Machine
	 */
	private HashMap<Machine, ArrayList<Machine>> machineMap;

	/**
	 * Constructor of a Graph object that initialize it.
	 */
	public Graph() {
		machineMap = new HashMap<>();
	}

	/**
	 * Method that returns a list of Machines that are in the graph.
	 *
	 * @return list of Machines in the graph
	 * @see Machine
	 */
	public ArrayList<Machine> getMachineList() {
		return new ArrayList<>(machineMap.keySet());
	}

	/**
	 * Getter of "machineMap".
	 *
	 * @return machineMap
	 */
	public HashMap<Machine, ArrayList<Machine>> getMachineMap() {
		return machineMap;
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.generateMachineMap(5);

		System.out.println(graph);

		Scanner scanner = new Scanner(System.in);

		// Selecting machine from which we will remove a linked one
		System.out.println("Select machine to remove a linked machine from :");
		int nbMachines = graph.getMachineList().size();
		String entry = scanner.next();
		while (graph.isNotValidIntegerEntry(entry)) {
			System.out.println("Enter the machine's number from 1 to " + nbMachines);
			entry = scanner.next();
		}
		int selectedMachineNumber = Integer.parseInt(entry) - 1;
		Machine selectedMachine = graph.getMachineList().get(selectedMachineNumber);

		// Selecting which machine to remove
		System.out.println("Select which linked machine to remove :");
		int nbLinkedMachines = graph.getMachineMap().get(selectedMachine).size();
		entry = scanner.next();
		while (graph.isNotValidIntegerEntry(entry)) {
			System.out.println("Enter the machine's number from 1 to " + nbLinkedMachines);
			entry = scanner.next();
		}
		int selectedLinkedMachineNumber = Integer.parseInt(entry) - 1;
		Machine selectedLinkedMachine = graph.getMachineMap().get(selectedMachine).get(selectedLinkedMachineNumber);

		scanner.close();

		graph.removeLink(selectedMachine, selectedLinkedMachine);

		System.out.println(graph);
	}

	/**
	 * Method that removes a link between two machines.
	 *
	 * @param machine1 first machine
	 * @param machine2 second machine
	 */
	public void removeLink(Machine machine1, Machine machine2) {
		if (machineMap.keySet().contains(machine1) && machineMap.keySet().contains(machine2))
			if (machine1.getLinkedMachines().contains(machine2)) {
				machineMap.get(machine1).remove(machine2);
				machineMap.get(machine2).remove(machine1);
			}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (int i = 1; i <= machineMap.keySet().size(); i++) {
			res.append(i).append(". Machine's ID = ").append(System.identityHashCode(getMachineList().get(i - 1))).append("\t Machine.s liée.s : ");
			for (Machine machine1 : machineMap.get(getMachineList().get(i - 1)))
				res.append(System.identityHashCode(machine1)).append(" \t");
			res.append("\n");
		}

		return String.valueOf(res);
	}

	/**
	 * Method that randomly generate a new graph.
	 * <p>
	 * It first creates the desired number of machine (nbMachines) and then, for each one of them, links it randomly
	 * to other machines.
	 * </p>
	 *
	 * @param nbMachines desired number of machine
	 * @see Machine
	 */
	public void generateMachineMap(int nbMachines) {
		ArrayList<Machine> machines = new ArrayList<>();

		for (int i = 0; i < nbMachines; i++)
			machines.add(new Machine());

		for (Machine machine : machines) {
			int nbLinkedMachines = (int) (Math.random() * nbMachines) + 1; // Selects a random number of linked machine

			while (nbLinkedMachines != 0) {
				Machine selectedMachine = machines.get((int) (Math.random() * nbMachines)); // Selects a random machine

				/* Check if the machine selected isn't the one we are linking machines to and if it is not already
				linked, then links both machines
				 */
				if (machine != selectedMachine && !machine.getLinkedMachines().contains(selectedMachine)) {
					machine.addLinkedMachine(selectedMachine);
					selectedMachine.addLinkedMachine(machine);
				}

				nbLinkedMachines--;
			}

			machineMap.put(machine, machine.getLinkedMachines());
		}
	}

	/**
	 * Method that checks if the String object in parameter is a valid integer
	 *
	 * @param s a String object
	 * @return True if the String object isn't a valid integer and False if it is
	 */
	public boolean isNotValidIntegerEntry(String s) {
		return !s.matches("\\d+") || Integer.parseInt(s) - 1 <= 0 && Integer.parseInt(s) - 1 >= getMachineList().size();
	}
}
