import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class Execute {
		
	public static void main(String[] args) throws Exception {
		ArrayList<String> vertexList1 = new ArrayList<String>();
		ArrayList<String> vertexList2 = new ArrayList<String>();
		ArrayList<String> vertexList3 = new ArrayList<String>();
		String output;
		
		//Veuillez remplacer le chemin d'accès des fichiers suivants
		File input1 = new File("C:\\Users\\Alban\\Desktop\\ProjetJAVA\\metro.txt");
		File input2 = new File("C:\\Users\\Alban\\Desktop\\ProjetJAVA\\sonet-200.txt");
		File input3 = new File("C:\\Users\\Alban\\Desktop\\ProjetJAVA\\sonet-1000.txt");
		BufferedReader buff1 = new BufferedReader(new InputStreamReader(new FileInputStream(input1), "UTF-8")); 
		BufferedReader buff2 = new BufferedReader(new InputStreamReader(new FileInputStream(input2), "UTF-8"));
		BufferedReader buff3 = new BufferedReader(new InputStreamReader(new FileInputStream(input3), "UTF-8"));
		
		while ((output = buff1.readLine()) != null) 
			vertexList1.add(output);	
		while ((output = buff2.readLine()) != null) 
			vertexList2.add(output);	
		while ((output = buff3.readLine()) != null) 
			vertexList3.add(output);	
		
		Metro metroGraph = new Metro(vertexList1);
		SocialNetwork socialNet1 = new SocialNetwork(vertexList2);
		SocialNetwork socialNet2 = new SocialNetwork(vertexList3);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		//Pour obtenir le résultat correspondant à une question veuillez décommenter la ligne en dessous.
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		//Question1
		metroGraph.printTopTen();
		
		//Question2
		//metroGraph.Route("Dupleix", "République");
		
		//Question 3.a
		//socialNet1.printTopTen();
		
		//Question 3.b
		//socialNet1.Route("4", "54");
		
		//Question 4
		//socialNet1.sixDeg();
		
		//Question 3.a
		//socialNet2.printTopTen();
		
		//Question 3.b
		//socialNet2.Route("4", "54");
		
		//Question 4
		//socialNet2.sixDeg();
		}
} 




