import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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

	/**
	 * Method that checks if the String object in parameter is a valid integer
	 *
	 * @param s a String object
	 * @return True if the String object isn't a valid integer and False if it is
	 */
	public boolean isNotValidIntegerEntry(String s) {
		return !s.matches("\\d+") || Integer.parseInt(s) - 1 <= 0 && Integer.parseInt(s) - 1 >= getMachineList().size();
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.generateMachineMap(5, 2, 0.05f);

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
	 * Method that randomly generate a new graph.
	 * <p>
	 * <p>
	 * </p>
	 *
	 * @param nbMachines desired number of machine
	 * @see Machine
	 */
	public void generateMachineMap(int nbMachines, int nbInfectedMachines, float linkProb) {
		ArrayList<Machine> machines = new ArrayList<>();
		Random random = new Random();

		while (machines.size() < nbMachines) // creates $nbMachines machines
			machines.add(new Machine());

		ArrayList<Integer> infect = new ArrayList<>();
		while (nbInfectedMachines > 0) {
			int rand = random.nextInt(nbMachines);

			if (!infect.contains(rand)) {
				infect.add(rand);
				nbInfectedMachines--;
			}
		}

		/*
		For each machine :
		- it put it into infected state if it index is into the infect list
		- it links it with other machines following the probability
		- it links it with a random machine if it didn't got any linked machine following the probability
		 */
		for (Machine machine : machines) {
			if (infect.contains(machines.indexOf(machine)))
				machine.setInfectedState(true);

			for (Machine machine1 : machines) {
				int linkRate = random.nextInt(100) + 1;

				if (linkRate < linkProb * 100) {
					machine.addLinkedMachine(machine1);
					machine1.addLinkedMachine(machine);
				}
			}

			if (machine.getLinkedMachines().size() == 0) {
				int randMachine = random.nextInt(nbMachines);
				machine.addLinkedMachine(machines.get(randMachine));
				machines.get(randMachine).addLinkedMachine(machine);
			}

			machineMap.put(machine, machine.getLinkedMachines());
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		for (int i = 1; i <= machineMap.keySet().size(); i++) {
			res.append(i).append(". Machine's ID = ").append(System.identityHashCode(getMachineList().get(i - 1))).append(" ").append(getMachineList().get(i - 1).isInfectedState()).append("\t Machine.s liée.s : ");
			for (Machine machine1 : machineMap.get(getMachineList().get(i - 1)))
				res.append(System.identityHashCode(machine1)).append(" \t");
			res.append("\n");
		}

		return String.valueOf(res);
	}
}
