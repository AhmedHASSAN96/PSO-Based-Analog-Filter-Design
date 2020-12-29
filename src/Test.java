
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PSO pso = new PSO(0.00000000000001);
		pso.swarm.printgBest();
		
		System.out.println();
		
		System.out.println("*******************************Best Value********************************");
		System.out.println("omega1: "+ pso.swarm.gBest.calculateFrequency1() +" rad/s");
		System.out.println("Q1: "+ pso.swarm.gBest.calculateQ1());
		System.out.println("omega2: "+ pso.swarm.gBest.calculateFrequency2() +" rad/s");
		System.out.println("Q2: "+ pso.swarm.gBest.calculateQ2());
		
		System.out.println("******************************* Mean *****************************");
		System.out.println("w = "+( (pso.mean[1]+pso.mean[0]) / 2 ) );
		System.out.println("Q1 = "+pso.mean[2]);
		System.out.println("Q2 = "+pso.mean[3]);
		
		System.out.println("*******************************Stan Dev******************************");
		System.out.println("w = "+( (pso.StDev[1]+pso.StDev[0]) / 2 ) );
		System.out.println("Q1 = "+pso.StDev[2]);
		System.out.println("Q2 = "+pso.StDev[3]);
		
		System.out.println("******************************* MSE *******************************");
		System.out.println("w = "+( (pso.MES[1]+pso.MES[0]) / 2 ) );
		System.out.println("Q1 = "+pso.MES[2]);
		System.out.println("Q2 = "+pso.MES[3]);
		
		System.out.println("******************************* Error *******************************");
		System.out.println("error = "+pso.swarm.gBest.fitness());
	}
	
}
