import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Parser 
{
    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException 
    {
    	
    	BellmanFord solution = new BellmanFord();

		FileInputStream fs = new FileInputStream("Graph2.txt");
		BufferedReader sc = new BufferedReader(new InputStreamReader(fs));
		String textFromFile = "";
	    String lines_substring[];
	    
        ArrayList<String> allVertex = new ArrayList<String>();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        
    	textFromFile = sc.readLine();
	    while (textFromFile != null) 
	    {
	    	    	    
		    lines_substring = textFromFile.split(" ");	
	    	
	    	if (!allVertex.contains(lines_substring[0]))
	    		allVertex.add(lines_substring[0]);
	    	if (!allVertex.contains(lines_substring[1]))
	    		allVertex.add(lines_substring[1]);
	    	
	    	edges.add(new Edge(lines_substring[0], lines_substring[1], 
	    			           Integer.parseInt(lines_substring[2])));
	    	
	    	textFromFile = sc.readLine();
	    }
	    
	    allVertex.sort(new Comparator<String>() 
	    {
	    	public int compare(String o1, String o2) 
	    	{
                return o1.toString().compareTo(o2.toString());
	    	}
	    });
	    
	    System.out.println("Вершины: "+allVertex);
    	
	    System.out.println("Ребра:");
	    for (Edge i : edges)
	    {
	    	System.out.println(i.from+"->"+i.to+"  = "+i.weight);
	    }
	    
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    String start;
	    while(true)
	    {	
	    	System.out.print("\n"+"Введите стартовую вершину: ");
	    	start = reader.readLine();
	    	
	    	if (!allVertex.contains(start))
	    	{
	    		System.out.println("Ошибка: Вершина не существует, введите еще раз.");
	    		continue;
	    	}
	    	
	    	break;
	    }
	    
	    ArrayList<Object> result = solution.solve(allVertex, edges, start);
	    
	    if (result.size() == 0)
	    {
		    fs.close();
		    sc.close();
	    	return;
	    }
	    
        System.out.println("\nКороткие пути из стартовой вершины:\n");
        
        int j = 0;
        for(int i : (int[]) result.get(0))
        {
        	if (i != 1000 * 1000 * 1000)
        		System.out.println(start+"->"+allVertex.get(j)+"  = "+i);
        	else
        		System.out.println(start+"->"+allVertex.get(j)+"  = Нет пути");
        	j++;
        }
        
        System.out.println("");
        for (int i = 1; i < allVertex.size()+1; i++)
        {
        	System.out.print("В вершину "+allVertex.get(i-1)+"  = ");
	        for (j = ((ArrayList<String>) result.get(i)).size()-1; j > 1; j--) 
	        {
	        	System.out.print(((ArrayList<String>) result.get(i)).get(j)+"->");
	        }
	        if (((ArrayList<String>) result.get(i)).size()-1 == 1)
	        {
	        	System.out.println(" -Нет пути или данная вершина стартовая-");
	        }
	        else
	        {
	        	System.out.println(((ArrayList<String>) result.get(i)).get(j));
	        }
        }
        
	    fs.close();
	    sc.close();
    }
}
