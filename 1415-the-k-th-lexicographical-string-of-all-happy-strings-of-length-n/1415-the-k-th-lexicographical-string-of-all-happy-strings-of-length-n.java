class Solution {

    List<String> happyStrings = new ArrayList<>();

    public String getHappyString(int n, int k) {
        String currentString = "";
        // Generate all happy strings of length n
        generateHappyStrings(n, currentString);

        // Check if there are at least k happy strings
        if (happyStrings.size() < k) return "";

        return happyStrings.get(k - 1);
    }

    protected void generateHappyStrings(int n, String currentString) {
        // If the current string has reached the desired length, add it to the
        // list
        if (currentString.length() == n) {
            happyStrings.add(currentString);
            return;
        }

        // Try adding each character ('a', 'b', 'c') to the current string
        for (char currentChar = 'a'; currentChar <= 'c'; currentChar++) {
            // Skip if the current character is the same as the last character
            if (
                currentString.length() > 0 &&
                currentString.charAt(currentString.length() - 1) == currentChar
            ) continue;

            // Recursively generate the next character
            generateHappyStrings(n, currentString + currentChar);
        }
    }
}