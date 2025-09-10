class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int users = languages.length;
        HashSet<Integer>[] knows = new HashSet[users+1];

        for(int user=0; user<users; user++){
            knows[user+1] = new HashSet<>();
            for(int knowsLang : languages[user]){
                knows[user+1].add(knowsLang);
            }
        }
        HashSet<Integer> needTeach = new HashSet<>();

        for(int[] f : friendships){
            int v = f[0];
            int u = f[1];
            boolean share = false;
            for(int lang : knows[v]){
                if(knows[u].contains(lang)){
                    share = true;
                    break;
                }
            }

            if(!share){
                needTeach.add(u);
                needTeach.add(v);
            }
        }

        int ans = 500;

        for(int lang=1; lang<=n; lang++){
            int count = 0;
            for(int user : needTeach){
                if(!knows[user].contains(lang))count++;
            }

            ans = Math.min(ans,count);
        }

        return ans;
    }
}