

public class World {
	private float[] bounds;
	private List<Herd> herds;
	private List<List<Integer>> resources; //placeholder

	public World() {
		//set arbitrary world size for now
		bounds = new float[]{100.0, 100.0, 100.0};

		// initialize herds (populations of different species)
		herds = new ArrayList<Herd>();
		
		// placeholder
		resources = new ArrayList<ArrayList<Integer>>();
	}

	public void process() {
		// decay/replenishment of resources

		// update herds
		for (Herd h: herds) {
			h.process(this);
		}
	}

	public float getBounds() {return bounds;}
	public float getXBound() { return bounds[0]; }
	public float getYBound() { return bounds[1]; }
	public float getZBound() { return bounds[2]; }
	

}