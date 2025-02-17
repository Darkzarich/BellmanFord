import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord
{
    // � �������� �������� ������������� 
    // ������� ���������� ������� ����� 10^9
    private static final int INF = 1000 * 1000 * 1000;

    public ArrayList<Object> solve(ArrayList<String> allVertex, ArrayList<Edge> edges, String start) 
    {
    	
        // ������� ������, i-�� ������� ��������
        // ����� ������� ������� ���������� �� 1-��
        // �� i-�� ������� �����
        int[] distance = new int[allVertex.size()];
        ArrayList<String> path = new ArrayList<String>();
        ArrayList<Object> result = new ArrayList<Object>();

        // �� ������ ������ ��������� ��� ����������,
        // ����� 0-��, ����� ������������� (��������)
        Arrays.fill(distance, INF);
        
        for (int i = 0; i < allVertex.size(); i++) 
        {
        	path.add("NULL");
        }

        // 0-�� ����������, �������� ����� ����,
        // ��� ��� ���������� �� 0-�� �������
        // �� ����� ���� ����� ����
        distance[allVertex.indexOf(start)] = 0;
        String x = "NULL";

        // � ������������ � ���������� �����
        // ��������� ������ ����������
        for (int i = 0; i < allVertex.size(); i++) 
        {
        	x = "NULL";
        	
            for (int j = 0; j < edges.size(); j++) 
            {
                String from = edges.get(j).from;
                String to = edges.get(j).to;
                int weight = edges.get(j).weight;
                
                // ����, ��� ���� ������� from ��
                // ������� ���� ������ ���������
                // ��������� ���������� ������ ��
                // ������, �� ��� �� ������� �����
                
                if (distance[allVertex.indexOf(from)] == INF) 
                {
                    continue;
                }

                // � ��������� ������ ��������� ��������
                // ���������� �� ������� to,
                // ��� �������� �����������
                
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
        	System.out.println("\n������������� ���� ���������� �� ��������� ������� �����������.");
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
     
    		System.out.println("\n������������� ���� ���������� �� ��������� �������: ");
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
    
