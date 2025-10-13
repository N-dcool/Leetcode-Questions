class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();

        for(String word : words){
            int size = res.size();

            if(size == 0){ 
                res.add(word);
                continue;
            }
            
            if(!isAnagram(res.get(size -1) , word)) res.add(word);
        }

        return res;
        
    }

    public boolean isAnagram(String w1, String w2){
        int[] freq = new int[26];
        int n = w1.length();
        int m = w2.length();

        if(n != m) return false;

        for(int i=0; i<n; i++){
            freq[w1.charAt(i) - 'a']++;
            freq[w2.charAt(i) - 'a']--;
        }

        for(int f : freq){
            if(f != 0) return false;
        }

        return true;
    }
}