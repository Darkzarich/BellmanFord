import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord
{
    // В качестве условной бесконечности 
    // выберем достаточно большое число 10^9
    private static final int INF = 1000 * 1000 * 1000;

    public ArrayList<Object> solve(ArrayList<String> allVertex, ArrayList<Edge> edges, String start) 
    {
    	
        // Создаем массив, i-ый элемент которого
        // будет хранить текущее расстояние от 1-ой
        // до i-ой вершины графа
        int[] distance = new int[allVertex.size()];
        ArrayList<String> path = new ArrayList<String>();
        ArrayList<Object> result = new ArrayList<Object>();

        // До начала работы алгоритма все расстояния,
        // кроме 0-го, равны бесконечности (условной)
        Arrays.fill(distance, INF);
        
        for (int i = 0; i < allVertex.size(); i++) 
        {
        	path.add("NULL");
        }

        // 0-ое расстояние, очевидно равно нулю,
        // так как расстояние от 0-ой вершины
        // до самой себя равно нулю
        distance[allVertex.indexOf(start)] = 0;
        String x = "NULL";

        // В соответствии с алгоритмом будем
        // обновлять массив расстояний
        for (int i = 0; i < allVertex.size(); i++) 
        {
        	x = "NULL";
        	
            for (int j = 0; j < edges.size(); j++) 
            {
                String from = edges.get(j).from;
                String to = edges.get(j).to;
                int weight = edges.get(j).weight;
                
                // Ясно, что если вершина from на
                // текущем шаге работы алгоритма
                // находится бесконечно далеко от
                // нужной, то она не изменит ответ
                
                if (distance[allVertex.indexOf(from)] == INF) 
                {
                    continue;
                }

                // В противном случае попробуем обновить
                // расстояние до вершины to,
                // это называют релаксацией
                
                if (distance[allVertex.indexOf(to)] > distance[allVertex.indexOf(from)] + weight)
                {
                    distance[allVertex.indexOf(to)] = distance[allVertex.indexOf(from)] + weight;
                    path.set(allVertex.indexOf(to), from);
                    x = to;
                }
            }
        }
        
        if (x.equals("NULL"))
        {
        	System.out.println("\nОтрицательный цикл достижимый из стартовой вершины отсутствует.");
        }
        else
        {
    		String y = x;
    		for (int i=0; i<allVertex.size(); i++)
    			y = path.get(allVertex.indexOf(y));
     
    		ArrayList<String> path_to_negativ = new ArrayList<String>();
    		
    		for (String cur=y; ; cur = path.get(allVertex.indexOf(cur))) 
    		{
    			path_to_negativ.add(cur);
    			if (cur == y && path_to_negativ.size() > 1)  
    				break;
    		}
     
    		System.out.println("\nОтрицательный цикл достижимый из стартовой вершины: ");
    		for (int i=path_to_negativ.size(); i>0; i--)
    			System.out.print(path_to_negativ.get(i-1)+"->");
    		
    		return result;
        }
        
        
        
        result.add(distance);
        
        ArrayList<String> path_to_point = new ArrayList<String>();
        
        for (String s : allVertex)
        {
        	path_to_point.add(s);
        	for (String cur = s; cur != "NULL"; cur = path.get(allVertex.indexOf(cur)))
        	{
        		path_to_point.add(cur);
        	}
        	result.add(path_to_point);
        	path_to_point = new ArrayList<String>();
        }
        
		result.add(path_to_point);
		
        return result;
    }
}
    
