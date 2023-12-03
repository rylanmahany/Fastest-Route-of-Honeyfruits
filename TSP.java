/*
 * This is a soultion for a minimax/adverserial version where two players compete over the fruits
 */

import java.util.*;
public class TSP{
    public static void main(String[] args){

        //creating the graph, taking into consideration AE is not possible, as you would need to pass through C, even on a straight line
        HashMap<String, Integer> graph = new HashMap<String, Integer>();
        graph.put("ab", 80); graph.put("ba", 80);
        graph.put("ac", 75);graph.put("ca", 75);
        graph.put("ad", 130);graph.put("da", 130);
        graph.put("ae", -999);graph.put("ea", -999);
        graph.put("bc", 120);graph.put("cb", 120);
        graph.put("bd", 125);graph.put("db", 125);
        graph.put("be", 205);graph.put("eb", 205);
        graph.put("cd", 75);graph.put("dc", 75);
        graph.put("ce", 95); graph.put("ec", 95);
        graph.put("de", 95);graph.put("ed", 95);
        graph.put("ec", 95); graph.put("ce", 95);
        List<String> aPaths = permutation("bcde");
        List<String> ePaths = permutation("abcd");
        for (String p: aPaths){
            p = "a" + p;
        }
        for (String p: ePaths){
            p = "e" + p;
        }

        //with all paths generated from the permutations for all paths starting with a and e, we can find the shortest path for both players.
        HashMap<String, Integer> aDistances = getDistances(aPaths, graph);
        HashMap<String, Integer> eDistances = getDistances(ePaths, graph);
        List<String> listA = iterate(aDistances);
        List <String> listE = iterate(eDistances);
        Collections.sort(listA);
        Collections.sort(listE);

        System.out.println("From A:");
        for(String a:listA){
            System.out.println(a);
        }

        System.out.println("From E:");
        for(String e:listE){
            System.out.println(e);
        }

    }
        public static List<String> iterate(HashMap<String, Integer> graph){
            List<String> results = new ArrayList<String>();
            graph.forEach((key, value)->{
                results.add(value + " " + key);
            });
            return results;
        }
        //method to find all distances
        public static HashMap<String, Integer> getDistances(List<String> paths, HashMap<String, Integer> graph){
            HashMap<String, Integer> distances = new HashMap<>();
            String head = "";
            String tail = "";
            int dist = 0;
            for (int j = 0 ; j < paths.size(); j++){
                dist = 0;
                for(int i = 0; i <= paths.get(j).length()-2; i++){
                    head = Character.toString(paths.get(j).charAt(i));
                    tail = Character.toString(paths.get(j).charAt(i+1));
                    String lineseg = head+""+tail;
                    dist += graph.get(lineseg);
                }
                distances.put(paths.get(j), dist);
            }
            return distances;
        }


        
        //method to permute strings to generate the paths
        public static List<String> permutation (String str) {
            List<String> paths = new ArrayList<>();
            paths.add(String.valueOf(str.charAt(0)));
            
            for (int i = 1; i < str.length(); i++) {
                for (int j = paths.size() - 1; j >= 0; j--) {
                  String temp = paths.remove(j);
                    for (int k = 0; k <= temp.length(); k++) {
                        paths.add(temp.substring(0, k) + str.charAt(i) + temp.substring(k));
                    }
                }
            }
               return paths;
        }
}
