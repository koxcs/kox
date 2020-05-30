import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ponovitev {

	public static void main(String[] args) {
		
		System.out.println("Dobrodošli!\nHangman je igra ugibanja. En igralec(v našem primeru računalnik) pomisli na besedo, "
				+ "besedno zvezo ali stavek, drugi pa jih skuša uganiti, tako da predlaga črke v določenem številu ugibanj.\n"
				+ "Igralec ima lahko največ 6 poskusov preden ga se obesi v našem primeru.");
		
		
		drawStart();
		
		int stevec = 0;
		
		String[] besede = getWordsFromFile();
			
		String beseda = getRandomWord(besede);
		
		String skritaBeseda = beseda.replaceAll(".", "_");
		System.out.println("\nBeseda: " + beseda.replaceAll(".", "_ "));
		
		while(stevec < 6 && skritaBeseda.contains("_")) {
			
			Scanner vhod = new Scanner(System.in);
			System.out.print("Uganite črko: ");
			char crka = vhod.next().trim().charAt(0);
			
			if(!beseda.contains(String.valueOf(crka))) {
		    	stevec++;
		    	
		    	drawPart(stevec);
		    	
		    } else {
	
		    	 String delnoOdkrita = findAllOccurrencesAndReplaceUnderscores(beseda, crka, skritaBeseda);
		    	 skritaBeseda = delnoOdkrita; 
		         System.out.println(delnoOdkrita.replaceAll(".(?!$)", "$0 "));
		    	 
		         if(!skritaBeseda.contains("_")) {
		        	 System.out.println("Ste zmagali! Česitam!");   
		         }
		    }
	    	
	    }
		
		
		
		}
	
	
	
	
	private static String[] getWordsFromFile() {
		String file = "src/resources/besede.txt"; 
	    List<String> listaBesed = new ArrayList<String>(); 
		 try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")); 
			String line; 
		 
		   while((line = reader.readLine()) != null) {
			   listaBesed.add(line);
		   }
		   reader.close();
	} catch (Exception e ) {
		e.printStackTrace();
	}
		 String[] besede = new String[listaBesed.size()]; 
			listaBesed.toArray(besede); 
			return besede;
	}
	
	
	private static String getRandomWord(String[] besede) {
		int random = new Random().nextInt(besede.length);
		return besede[random];
		
	}
	
private static String findAllOccurrencesAndReplaceUnderscores(String beseda, char crka, String skritaBeseda) { //deklariramo metodo, ki isce da ji prosledimo nek string in char. sluzi dam nam najde use index kateri vsebujejo to vneseno crko
		
		int index = beseda.indexOf(crka);
		while (index >= 0)  { 
			skritaBeseda = skritaBeseda.substring(0, index) + crka + skritaBeseda.substring(index + 1); 
			index = beseda.indexOf(crka, index + 1); 
	
		}
		return skritaBeseda;
	}
	
	private static void drawPart(int stevec) {
		 switch (stevec) {
	  	  case 1:
	  	    drawHead();
	  	    break;
	  	  case 2:
	  	    drawBody();
	  	    break;
	  	  case 3:
	  	    drawLeftArm();
	  	    break;
	  	  case 4:
	  	    drawRightArm();
	  	    break;
	  	  case 5:
	  	    drawLeftLeg();
	  	    break;
	  	  case 6:
	  	    drawRightLeg();
	  	    break;
	  	}
	}

	private static void drawStart() {
	    System.out.println("    _________");
	    System.out.println("   |         |");
	    System.out.println("   |");
	    System.out.println("   |");
	    System.out.println("   |");
	    System.out.println("   |");
	    System.out.println("___|___");
	    
	    
	}
	
	private static void drawHead() {
	    System.out.println("    _________");
	    System.out.println("   |         |");
	    System.out.println("   |         O");
	    System.out.println("   |");
	    System.out.println("   |");
	    System.out.println("   |");
	    System.out.println("___|___");
	}
	
	private static void drawBody() {
	    System.out.println("    _________");
	    System.out.println("   |         |");
	    System.out.println("   |         O");
	    System.out.println("   |         |");
	    System.out.println("   |         |");
	    System.out.println("   |");
	    System.out.println("___|___");
	}
	
	private static void drawLeftArm() {
	    System.out.println("    _________");
	    System.out.println("   |         |");
	    System.out.println("   |         O");
	    System.out.println("   |        \\|"); 
	    System.out.println("   |         |");
	    System.out.println("   |");
	    System.out.println("___|___");
	}
	
	private static void drawRightArm() {
	    System.out.println("    _________");
	    System.out.println("   |         |");
	    System.out.println("   |         O");
	    System.out.println("   |        \\|/"); 
	    System.out.println("   |         |");
	    System.out.println("   |");
	    System.out.println("___|___");
	}
	
	private static void drawLeftLeg() {
	    System.out.println("    _________");
	    System.out.println("   |         |");
	    System.out.println("   |         O");
	    System.out.println("   |        \\|/"); 
	    System.out.println("   |         |");
	    System.out.println("   |        /");
	    System.out.println("___|___");
	}
	
	private static void drawRightLeg() {
	    System.out.println("    _________");
	    System.out.println("   |         |");
	    System.out.println("   |         O");
	    System.out.println("   |        \\|/"); 
	    System.out.println("   |         |");
	    System.out.println("   |        / \\");
	    System.out.println("___|___");
	    System.out.println("STE ZGUBILI!");
	}

}
