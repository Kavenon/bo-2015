package pl.agh.edu.bo.projekt;


public class IndividualCrossoverTest {

	/*//Sprawdza czy poprawnie skrzyzowalo
	@Test
	public void test() {
		ArrayList <Vertex> l1 = new ArrayList<Vertex>();
		ArrayList <Vertex> l2 = new ArrayList<Vertex>();
		
		l1.add(new Vertex(33));
		l1.add(new Vertex(9));
		l1.add(new Vertex(13));
		l1.add(new Vertex(24));
		
		
		l1.add(new Vertex(14));
		l1.add(new Vertex(11));
		l1.add(new Vertex(18));
		l1.add(new Vertex(21));
		
		
		ArrayList<Vertex> result = Individual.crossover(l1, l2).path;
		
		
		ArrayList<Vertex> verList = new ArrayList<Vertex>();
		verList.add(new Vertex(33));
		verList.add(new Vertex(9));
		verList.add(new Vertex(13));
		verList.add(new Vertex(24));
		verList.add(new Vertex(14));
		verList.add(new Vertex(11));
		verList.add(new Vertex(18));
		verList.add(new Vertex(33));

		
		for(int i=0; i<verList.size(); i++){
			assertEquals(true, verList.get(i).getId()==result.get(i).getId());
		
		}
		
	}

    @Test
    public void crossoverTest(){
        ArrayList <Vertex> l1 = new ArrayList<Vertex>();
        ArrayList <Vertex> l2 = new ArrayList<Vertex>();
        l1.add(new Vertex(1));
        l1.add(new Vertex(2));
        l1.add(new Vertex(3));
        l1.add(new Vertex(4));


        l1.add(new Vertex(5));
        l1.add(new Vertex(6));
        l1.add(new Vertex(7));
        l1.add(new Vertex(8));

        l2.add(new Vertex(11));
        l2.add(new Vertex(12));
        l2.add(new Vertex(13));
        l2.add(new Vertex(14));


        l2.add(new Vertex(15));
        l2.add(new Vertex(16));
        l2.add(new Vertex(17));
        l2.add(new Vertex(18));

        ArrayList <Vertex> l1cross = new ArrayList<Vertex>();
        l1cross.add(new Vertex(1));
        l1cross.add(new Vertex(2));
        l1cross.add(new Vertex(3));
        l1cross.add(new Vertex(4));
        l1cross.add(new Vertex(11));
        l1cross.add(new Vertex(12));
        l1cross.add(new Vertex(13));
        l1cross.add(new Vertex(14));
        ArrayList <Vertex> l2cross = new ArrayList<Vertex>();

        l2cross.add(new Vertex(5));
        l2cross.add(new Vertex(6));
        l2cross.add(new Vertex(7));
        l2cross.add(new Vertex(8));
        l2cross.add(new Vertex(15));
        l2cross.add(new Vertex(16));
        l2cross.add(new Vertex(17));
        l2cross.add(new Vertex(18));


        List<Individual> crossedIndividuals = Individual.crossoverNew(l1, l2);

        System.out.println(  crossedIndividuals.get(0).path);
        System.out.println( l1cross);
        System.out.println(crossedIndividuals.get(1).path);
        System.out.println(l2cross);
        assertTrue(crossedIndividuals.get(0).path.equals(l1cross));
        assertTrue(crossedIndividuals.get(1).path.equals(l2cross));
    }
    @Test
    public void crossoverOddTest(){
        ArrayList <Vertex> l1 = new ArrayList<Vertex>();
        ArrayList <Vertex> l2 = new ArrayList<Vertex>();
        l1.add(new Vertex(1));
        l1.add(new Vertex(2));
        l1.add(new Vertex(3));

        l1.add(new Vertex(4));
        l1.add(new Vertex(5));
        l1.add(new Vertex(6));
        l1.add(new Vertex(7));


        l2.add(new Vertex(11));
        l2.add(new Vertex(12));
        l2.add(new Vertex(13));
        l2.add(new Vertex(14));


        l2.add(new Vertex(15));
        l2.add(new Vertex(16));
        l2.add(new Vertex(17));
        l2.add(new Vertex(18));

        ArrayList <Vertex> l1cross = new ArrayList<Vertex>();
        l1cross.add(new Vertex(1));
        l1cross.add(new Vertex(2));
        l1cross.add(new Vertex(3));

        l1cross.add(new Vertex(11));
        l1cross.add(new Vertex(12));
        l1cross.add(new Vertex(13));
        l1cross.add(new Vertex(14));
        ArrayList <Vertex> l2cross = new ArrayList<Vertex>();

        l2cross.add(new Vertex(4));
        l2cross.add(new Vertex(5));
        l2cross.add(new Vertex(6));
        l2cross.add(new Vertex(7));
        l2cross.add(new Vertex(15));
        l2cross.add(new Vertex(16));
        l2cross.add(new Vertex(17));
        l2cross.add(new Vertex(18));


        List<Individual> crossedIndividuals = Individual.crossoverNew(l1, l2);

        System.out.println(  crossedIndividuals.get(0).path);
        System.out.println( l1cross);
        System.out.println(crossedIndividuals.get(1).path);
        System.out.println(l2cross);
        assertTrue(crossedIndividuals.get(0).path.equals(l1cross));
        assertTrue(crossedIndividuals.get(1).path.equals(l2cross));
    }*/

}

