public class State {
	private Machine[] infected;
	private Graph graph;
	private Player actualPlayer;

	public State(Machine[] infected, Graph graph, Player actualPlayer) {
		this.infected = infected;
		this.graph = graph;
		this.actualPlayer = actualPlayer;
	}
}
