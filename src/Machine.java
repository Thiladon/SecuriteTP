import java.util.ArrayList;

/**
 * Machine class representing the machines from the graph.
 *
 * @version 2018-01-27
 * @see Graph
 */
public class Machine {
	private boolean infectedState;
	private ArrayList<Machine> linkedMachines;

	/**
	 * Constructor of a Machiine object initializing it an non-infected state.
	 */
	public Machine() {
		infectedState = false;
		linkedMachines = new ArrayList<>();
	}

	/**
	 * Method returning whether or not the machine is infected.
	 *
	 * @return True if the machine is infected and False if it isn't
	 */
	public boolean isInfectedState() {
		return infectedState;
	}

	/**
	 * Method setting the infected state.
	 *
	 * @param infectedState True if the state to put is "infected" and False if it is "non-infected"
	 */
	public void setInfectedState(boolean infectedState) {
		this.infectedState = infectedState;
	}

	/**
	 * Method returning the list of all the machines that are linked to the actual machine.
	 *
	 * @return an ArrayList of Machine
	 */
	public ArrayList<Machine> getLinkedMachines() {
		return linkedMachines;
	}

	/**
	 * Method allowing to add linked machine.
	 *
	 * @param machine machine wich is linked to the actual machine
	 */
	public void addLinkedMachine(Machine machine) {
		linkedMachines.add(machine);
	}

	/**
	 * Method returning all the potential "attacks" that can be made.
	 * <p>
	 * An "attack" is representing the fact to infect another machine.
	 * </p>
	 *
	 * @return an ArrayList of linked machines not infected
	 */
	public ArrayList<Machine> getAttacks() {
		ArrayList<Machine> notInfectedLinkedMachines = new ArrayList<>();
		for (Machine machine : linkedMachines)
			if (!machine.isInfectedState())
				notInfectedLinkedMachines.add(machine);

		return notInfectedLinkedMachines;
	}

	/**
	 * Method to attack a non-infected linked machine.
	 *
	 * @param machine the Machine we want to attack
	 */
	public void attack(Machine machine) {
		if (getAttacks().contains(machine))
			machine.setInfectedState(true);
	}
}
