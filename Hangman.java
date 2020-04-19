import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {

		
		System.out.println("Dobrodošli!\nHangman je igra ugibanja. En igralec(v našem primeru računalnik) pomisli na besedo, "
				+ "besedno zvezo ali stavek, drugi pa jih skuša uganiti, tako da predlaga črke v določenem številu ugibanj.\n"
				+ "Igralec ima lahko največ 6 poskusov preden ga se obesi v našem primeru.");
		
		drawStart();
		
	    int stevec = 0;
		String[] besede = {"biciklo", "auto", "kompjuter", "tastatura"};
		
	    String beseda = getRandomWord(besede);  //klicanje metode
	    
	    String skritaBeseda = beseda.replaceAll(".", "_"); // regex nas je "."(vsi znaki), replacement "_ " z čim zamenjamo 
	    System.out.println("\nBeseda: " + beseda.replaceAll(".", "_ ")); 
	    
	    while(stevec < 6 && skritaBeseda.contains("_")) { //vsakic zamenja _ z crko. smo v petlji dokler je stevec < 6 ali dokler beseda se vedno ni uganuta
	    	
	    	Scanner vhod = new Scanner(System.in);
		    System.out.print("Uganite črko: ");
		    char crka = vhod.next().trim().charAt(0); //program nam vzame samo eno črko, v tem primeru samo prvo vneseno črko //trim(če ima space da ga ne gleda)
		    
		    if(!beseda.contains(String.valueOf(crka))) {
		    	stevec++;
		    	
		    	drawPart(stevec);
		    	
		    } else {
	
		    	 String delnoOdkrita = findAllOccurrencesAndReplaceUnderscores(beseda, crka, skritaBeseda);
		    	 skritaBeseda = delnoOdkrita; // zaradi while uslova da ji damo tukaj novo vrednost.
		         System.out.println(delnoOdkrita.replaceAll(".(?!$)", "$0 "));
		    	 
		         if(!skritaBeseda.contains("_")) {
		        	 System.out.println("Ste zmagali! Česitam!");   
		         }
		    }
	    	
	    }
	    
	    
	    		
	    	
	    		
	    
	    
	    

	    	    
	}

	//deklaracija metode
	
	private static String getRandomWord(String[] besede) {
		
		int random = new Random().nextInt(besede.length); 
		return besede[random];
				
	} //metoda ki smo jo ustvarili da nam vrne random besedo iz niza besed katerega prosledimo
	
	
	private static String findAllOccurrencesAndReplaceUnderscores(String beseda, char crka, String skritaBeseda) { //deklariramo metodo, ki isce da ji prosledimo nek string in char. sluzi dam nam najde use index kateri vsebujejo to vneseno crko
		
		int index = beseda.indexOf(crka);//isce ta prosledjen character. character v tej beseda. da naj najde na katerem indexu je ta character, npr. beseda tastatura, ce iscemo crko a, index nam bo 1
		while (index >= 0)  { //index je lahko pri indexOf tudi -1, zto damo da isce samo od >=0, ko je index -1 pomeni da crke te vec ni
			skritaBeseda = skritaBeseda.substring(0, index) + crka + skritaBeseda.substring(index + 1); //substring uzima od stringa samo dio stringa, u nasem slucaju od skritaBeseda. zamjena _ z črka. 1 del, uzima od 0 do indexa gdje smo pronasli slovo. 2 del crka nam da crko a, 3 del index + 1 nam do konca vrne.
			index = beseda.indexOf(crka, index + 1); // ko najde npr. pri tastatura prvi index a ki je 1, nam isce od nasljednje crke, zto je index + 1, torej zacne iskat od crke s
	
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
	
	//deklaracija začetke metode risbe
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
	    System.out.println("   |        \\|"); // moramo 2 backslash-a, zto ker v javi je backslash kot escape character kot ki je \n
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

