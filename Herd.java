public class Herd {
	private List<Boid> boids;

	public Herd() { boids = new ArrayList<Boid>(); }

	public Herd(ArrayList<Boid> boids) { this.boids = boids; }

	public void process(World world) {
		for (Boid b: boids) {
			b.process(this, world);
		}
	}

	public void addBoid(Boid boid) { boids.add(boid); }

	public void removeBoid(Boid boid) { boids.remove(boid); }

	public int getSize() { return boids.size(); }


}