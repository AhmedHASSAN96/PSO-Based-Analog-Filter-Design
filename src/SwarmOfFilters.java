
public class SwarmOfFilters {
	int numOfParticles;
	Filter[] particles;
	Filter gBest;
	double c1 = 2.0, c2 = 2.0 , w = 0.7;
	private static final double V_MAX = 0.1;
	
	public SwarmOfFilters(int numOfParticles) {
		this.numOfParticles = numOfParticles;
		particles = new Filter[numOfParticles];
		for(int i = 0 ; i < numOfParticles ; i++) {
			particles[i] = new Filter(10000);
			particles[i].setpBest(particles[i]);
		}
		
		gBest = particles[0];
		for(int i = 1 ; i < numOfParticles ; i++) {
			if(particles[i].fitness() < gBest.fitness())
				gBest = particles[i];
		}
	}
	
	public void updateVelocity() {
		for(int i = 0 ; i < numOfParticles ; i++) {
			
			double[] position = particles[i].getPosition();
			double[] velocities = new double[16];
			double temp1 = 0, temp2 = 0;
			
			for(int j = 0 ; j < position.length ; j++) {
				temp1  = c1*Math.random();
				temp2  = c2*Math.random();
				temp1 *= particles[i].getpBest().getPosition()[j] - position[j]; 
				temp2 *= gBest.getPosition()[j] - position[j];
				
				double v = w*particles[i].velocities[j] + temp1 + temp2;
				if(v > V_MAX)
					v = V_MAX;
				else if(v < -V_MAX)
					v = -V_MAX;
				velocities[j] = v; 
			}	
			particles[i].velocities = velocities;
		}
	}
	
	public void updatePosition() {
		for(int i = 0 ; i < numOfParticles ; i++) {
			
			double[] position = particles[i].getPosition();
			double[] newPosition = new double[16];

			for(int j = 0 ; j < position.length ; j++) {
				newPosition[j] = particles[i].velocities[j] + position[j]; 
			}
			
			particles[i].updateFilter(newPosition);
		}
	}
	
	public void evaluateFitness() {
		for(int i = 0 ; i < numOfParticles ; i++) {
			if (particles[i].fitness() < particles[i].getpBest().fitness())
				particles[i].setpBest(particles[i]);
		}
		gBest = particles[0];
		for(int i = 1 ; i < numOfParticles ; i++) {
			if(particles[i].fitness() < gBest.fitness())
				gBest = particles[i];
		}
	}
	
	public void printgBest() {
		System.out.println("R1 = "+gBest.R1);
		System.out.println("R2 = "+gBest.R2);
		System.out.println("R3 = "+gBest.R3);
		System.out.println("R4 = "+gBest.R4);
		System.out.println("C1 = "+gBest.C1*Math.pow(10, -12));
		System.out.println("C2 = "+gBest.C2*Math.pow(10, -12));
		System.out.println("C3 = "+gBest.C3*Math.pow(10, -12));
		System.out.println("C4 = "+gBest.C4*Math.pow(10, -12));
	}
}
