import java.util.ArrayList;

public class PSO {
	SwarmOfFilters swarm;
	double[] mean  = new double[4];
	double[] StDev = new double[4];
	double[] MES = new double[4];
	int w = 10000;
	double q1 = 1/0.7654;
	double q2 = 1/1.8478;
	ArrayList<Filter> BestFilters = new ArrayList<>();
	double maxError;
	double iteration = 0;
	int numOfParticles = 1000;
	
	public PSO(double maxError) {
		this.maxError = maxError;
		// initialize the swarm --> set the pbest of each particle with the current
		// location
		// --> set the gbest of the swarm with the best location
		swarm = new SwarmOfFilters(numOfParticles);

		double error = swarm.gBest.fitness();

		while(error > maxError) {
		//while (iteration < 150) {
			swarm.updateVelocity();
			swarm.updatePosition();
			swarm.evaluateFitness();
			error = swarm.gBest.fitness();
			System.out.println("iteration : " + iteration + " current error : " + error);
			
			BestFilters.add(swarm.gBest);
			iteration++;
		}
		updateMean();
		updateStDev();
		updateMES();
	}

	public void updateMean() {
		for(Filter f : BestFilters) {
			mean[0] += f.calculateFrequency1();
			mean[1] += f.calculateFrequency2();
			mean[2] += f.calculateQ1();
			mean[3] += f.calculateQ2();
		}
		mean[0] /= iteration;
		mean[1] /= iteration;
		mean[2] /= iteration;
		mean[3] /= iteration;
	}
	
	public void updateStDev() {
		for(Filter f : BestFilters) {
			StDev[0] += Math.pow( ( f.calculateFrequency1() - mean[0] ) , 2);
			StDev[1] += Math.pow( ( f.calculateFrequency2() - mean[1] ) , 2);
			StDev[2] += Math.pow( ( f.calculateQ1() - mean[2] ) , 2);
			StDev[3] += Math.pow( ( f.calculateQ2() - mean[3] ) , 2);
		}
		StDev[0] /= iteration;
		StDev[1] /= iteration;
		StDev[2] /= iteration;
		StDev[3] /= iteration;
		
		StDev[0] = Math.sqrt(StDev[0]);
		StDev[1] = Math.sqrt(StDev[1]);
		StDev[2] = Math.sqrt(StDev[2]);
		StDev[3] = Math.sqrt(StDev[3]);
	}
	
	public void updateMES() {
		for(Filter f : BestFilters) {
			MES[0] += Math.pow( ( f.calculateFrequency1() -  w) , 2);
			MES[1] += Math.pow( ( f.calculateFrequency2() - w ) , 2);
			MES[2] += Math.pow( ( f.calculateQ1() - q1 ) , 2);
			MES[3] += Math.pow( ( f.calculateQ2() - q2 ) , 2);
		}
		MES[0] /= iteration ;
		MES[1] /= iteration ;
		MES[2] /= iteration ;
		MES[3] /= iteration ;
	}
}
