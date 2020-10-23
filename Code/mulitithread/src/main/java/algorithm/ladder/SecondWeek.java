package algorithm.ladder;

import java.util.*;

public class SecondWeek {


    public static void main(String[] args) {
        SecondWeek secondWeek = new SecondWeek();
        int[] A = new int[]{1, 7, 11};
        int[] B = new int[]{2, 4, 6};
//        int res = secondWeek.kthSmallestSum(A, B,3);
//        System.out.println(res);
    }

    //son_id,father_id
    private Map<Integer, Integer> father = new HashMap<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //init email-id relation
        Map<String, List<Integer>> emailIdMap = getEmailToID(accounts);
        System.out.println(Arrays.toString(emailIdMap.keySet().toArray()));
        //union the id with same email
        for (Map.Entry<String, List<Integer>> emailIdEntry : emailIdMap.entrySet()) {
            List<Integer> idSet = emailIdEntry.getValue();
            int fatherid = idSet.get(0);
            for (int i = 1; i < idSet.size(); i++) {
                int sonid = idSet.get(i);
                union(sonid, fatherid);
            }
        }

        //merge different id with the same email(the same father id)
        //fatherid , email list
        Map<Integer, Set<String>> idToEmailMap = getIdToEmailMap(emailIdMap);

        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> faheridEmailMap : idToEmailMap.entrySet()) {
            int fatherid = faheridEmailMap.getKey();
            List<String> emailList = new ArrayList<>(faheridEmailMap.getValue());
            Collections.sort(emailList);
            emailList.add(0, accounts.get(fatherid).get(0));
            ans.add(emailList);
        }
        return ans;
    }

    private Map<Integer, Set<String>> getIdToEmailMap(Map<String, List<Integer>> emailIdMap) {
        Map<Integer, Set<String>> ans = new HashMap<>();
        for (Map.Entry<String, List<Integer>> emailIdEntry : emailIdMap.entrySet()) {
            String email = emailIdEntry.getKey();
            List<Integer> idList = emailIdEntry.getValue();
            for (Integer id : idList) {
                //将email对应的所有账户id集中到fatherid上
                int fatherid = find(id);
                Set<String> emailList = ans.getOrDefault(fatherid,  new HashSet<>());
                emailList.add(email);
                ans.put(fatherid,emailList);
            }
        }
        return ans;
    }


    private Map<String, List<Integer>> getEmailToID(List<List<String>> accounts) {
        Map<String, List<Integer>> emailIdMap = new HashMap<>();
        int i = 0;
        for (List<String> account : accounts) {
            for (int j = 1; j < account.size(); j++) {
                String emailId = account.get(j);
                List<Integer> idSet = emailIdMap.getOrDefault(emailId, new ArrayList<>());
                idSet.add(i);
                emailIdMap.put(emailId, idSet);
            }
            i++;
        }
        return emailIdMap;
    }

    //find root father id
    private int find(int id) {
        List<Integer> path = new ArrayList<>();
        while (father.containsKey(id)) {
            path.add(id);
            id = father.get(id);
        }
        //path condense
        for (Integer sonid : path) {
            father.put(sonid, id);
        }
        return id;
    }

    private void union(int aid, int bid) {
        int arootid = find(aid);
        int brootid = find(bid);
        if (arootid != brootid) {
            father.put(arootid, brootid);
        }
    }

}
