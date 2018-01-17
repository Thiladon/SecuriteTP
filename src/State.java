public class State {
	private Machine[] infected;
	private Graph graph;
	private actualPlayer actualPlayer;

	public State(Machine[] infected, Graph graph, actualPlayer actualPlayer) {
		this.infected = infected;
		this.graph = graph;
		this.actualPlayer = actualPlayer;
	}
}
