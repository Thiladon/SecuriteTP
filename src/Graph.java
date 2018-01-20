import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Graph {
	private HashMap<Machine, ArrayList<Machine>> machineMap;

	public Graph() {
		machineMap = new HashMap<>();
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
		while (!graph.isValidIntegerEntry(entry)) {
			System.out.println("Enter the machine's number from 1 to " + nbMachines);
			entry = scanner.next();
		}
		int selectedMachineNumber = Integer.parseInt(entry) - 1;
		Machine selectedMachine = graph.getMachineList().get(selectedMachineNumber);

		// Selecting which machine to remove
		System.out.println("Select which linked machine to remove :");
		int nbLinkedMachines = graph.getMachineMap().get(selectedMachine).size();
		entry = scanner.next();
		while (!graph.isValidIntegerEntry(entry)) {
			System.out.println("Enter the machine's number from 1 to " + nbLinkedMachines);
			entry = scanner.next();
		}
		int selectedLinkedMachineNumber = Integer.parseInt(entry) - 1;
		Machine selectedLinkedMachine = graph.getMachineMap().get(selectedMachine).get(selectedLinkedMachineNumber);

		scanner.close();

		graph.removeLink(selectedMachine, selectedLinkedMachine);

		System.out.println(graph);
	}

	public void generateMachineMap(int nbMachines) {
		ArrayList<Machine> machines = new ArrayList<>();

		for (int i = 0; i < nbMachines; i++)
			machines.add(new Machine());

		for (Machine machine : machines) {
			int nbLinkedMachines = (int) (Math.random() * nbMachines) + 1;

			while (nbLinkedMachines != 0) {
				Machine selectedMachine = machines.get((int) (Math.random() * nbMachines));

				if (machine != selectedMachine && !machine.getLinkedMachines().contains(selectedMachine)) {
					machine.addLinkedMachine(selectedMachine);
					selectedMachine.addLinkedMachine(machine);
				}

				nbLinkedMachines--;
			}

			machineMap.put(machine, machine.getLinkedMachines());
		}
	}

	public void removeLink(Machine machine1, Machine machine2) {
		if (machineMap.keySet().contains(machine1) && machineMap.keySet().contains(machine2))
			if (machine1.getLinkedMachines().contains(machine2)) {
				machineMap.get(machine1).remove(machine2);
				machineMap.get(machine2).remove(machine1);
			}
	}

	public ArrayList<Machine> getMachineList() {
		return new ArrayList<>(machineMap.keySet());
	}

	public HashMap<Machine, ArrayList<Machine>> getMachineMap() {
		return machineMap;
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

	public boolean isValidIntegerEntry(String s) {
		return s.matches("\\d+") && Integer.parseInt(s) - 1 > 0 && Integer.parseInt(s) - 1 < getMachineList().size();
	}
}
