import java.util.ArrayList;

public class Machine {
	private boolean infectedState;
	private ArrayList<Machine> linkedMachines;

	public Machine() {
		infectedState = false;
		linkedMachines = new ArrayList<>();
	}

	public boolean isInfectedState() {
		return infectedState;
	}

	public void setInfectedState(boolean infectedState) {
		this.infectedState = infectedState;
	}

	public void addLinkedMachine(Machine machine){
		linkedMachines.add(machine);
	}

	public ArrayList<Machine> getLinkedMachines() {
		return linkedMachines;
	}
}
