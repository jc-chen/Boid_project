/*

BOID RULES
1. avoid collision with other boids
2. steer towards average heading of local boids
3. steer to move towards centre of mass of local flockmates

separation alignment cohesion forces + force drawing them to resources
*/


public class Boid {
	
	/* to take into consideration later:
		-boid mass a function of age, a genetic coefficient, and food intake
	    -boid hunger/thirst a function of acceleration and mass
	    -boid max acceleration a function of fullness and age
	    -add more evolutionary features, e.g. intelligence
	    -forage weight is heavily influenced by resource abundance of an area (birds will fly for a long time and stop somewhere collectively to refuel)
		-more complex mating behaviour (nesting, dad staying, mating rituals, horniness a function of genetics, fullness, age)
	*/

	static float separationWeight = 2.0;
	static float alignmentWeight = 1.0;
	static float cohesionWeight = 1.0;
	static float forageWeight = 1.5;
	static float seekMateWeight = 1.25;

	static int FULLNESS_THRESHOLD = 90;
	static float MOTIVATION_THRESHOLD = 0.1;

	// location info
	private float[] position;
	private float[] velocity;
	private float[] acceleration;

	// boid stats
	private boolean sex;
	private float reproductiveMotivation;
	private int age;
	private int hunger;
	private int thirst;
	private int energy;
	private int mass;
	private int maxSpeed;
	private int maxAcceleration;

	public Boid(float[] position) {
		// bird base stats
		sex = Math.random() < 0.5;
		reproductiveMotivation = 1.0; // TODO make more complex into a function later
		age = 0;
		hunger = 100;
		thirst = 100;
		energy = 100;
		mass = 10;
		maxSpeed = 1;
		maxAcceleration = 1; // TODO should a boid have maxAccel or maxForce? F=ma

		// place the bird
		//float posX = Math.random() * world.getXBound();
		this.position = position;

	}


	public void process(Herd herd, World world) {		
		deathUpdate(herd);
		movementUpdate(herd, world);
		behaviourUpdate(herd, world);
	}


	// updates

	public void deathUpdate(Herd herd) {
		// boid ages
		age++;
		hunger--;
		thirst--;
		energy--;

		// boid dies if hungy or thorsty or old
		if (hunger < 0  ||  thirst < 0 || age > 1000) {
			die(herd);
		}
	}

	public void movementUpdate(Herd herd, World world) {
		// to add later: only look at local boids rather than whole herd
		// to add later: confine movement to world bounds
		// note the boid CANNOT have z accel without some base x or y accel
		acceleration = separationWeight * separationForce(herd) 
					 + alignmentWeight * alignmentForce(herd) 
					 + cohesionWeight * cohesionForce(herd)
					 + behaviourForce(herd, world);  
		// cap acceleration
		//acceleration = Math.max(...) ;
		velocity = velocity + acceleration;  // v = v + dv/dt * dt  where dt = 1
		// cap velocity
		//velocity = Math.max(...); 
		position = position + velocity;

	}

	public void behaviourUpdate(Herd herd, World world) {
		// if boid is female next to male on a surface, herd.add(this.reproduce())
		// if next to food or water, eat/drink
		if (energy < 30) { sleep(); } //more complex behaviour later

	}

	// acceleration forces
	private float[] separationForce(Herd herd) {
	}

	private float[] alignmentForce(Herd herd) {

	}

	private float[] cohesionForce(Herd herd) {

	}

	private float[] behaviourForce(Herd herd, World world) {
		// if not in need, do nothing
		if (hunger > FULLNESS_THRESHOLD && thirst > FULLNESS_THRESHOLD && reproductiveMotivation < MOTIVATION_THRESHOLD) {return new int[]{0.0, 0.0, 0.0}}

		// if no food in sight, seek mate or do nothing
		// if no mate in sight, seek food or do nothing
		// if food/water/mate in sight, choose one of them

		// return force towards resource location by calculating direction change and multiplying with forageWeight, seekMateWeight
		

		// 
	}


	// behaviours

	public void die(Herd herd) {
		herd.removeBoid(this);
		// death animation
	}

	public Boid reproduce(Boid mate) {
		// TODO put a nest and eggs on position instead
		return new Boid();
	}

	public void eat() {
		hunger = 100;
		// play eating animation
		// play eating sound
	}

	public void drink() {
		thirst = 100;
		// play drinking animation & sound
	}

	public void sleep() {
		energy = 100;
		// animation
	}

	public void land() {
		// animation landing on a surface
	}
	
	public void fly() {
		// animation
	}
	

	//add all get_field functions later

}