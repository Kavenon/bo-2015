package pl.agh.edu.bo.projekt;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ IndividualCreateTest.class, IndividualCrossoverTest.class,
		IndividualEvaluateTest.class, PopulationSizeTest.class,
		VertexDistanceTest.class })
public class AllTests {



}
