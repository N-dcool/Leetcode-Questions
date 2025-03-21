class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> availableSupplies = new HashSet<>(Arrays.asList(supplies));
        Map<String, List<String>> ingredientToRecipes = new HashMap<>();
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, List<String>> recipeToIngredients = new HashMap<>();

        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            List<String> recipeIngredients = ingredients.get(i);
            recipeToIngredients.put(recipe, recipeIngredients);
            inDegree.put(recipe, recipeIngredients.size());

            for (String ingredient : recipeIngredients) {
                ingredientToRecipes.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipe);
            }
        }

        Queue<String> queue = new LinkedList<>(availableSupplies);
        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (recipeToIngredients.containsKey(current)) {
                result.add(current);
            }

            if (ingredientToRecipes.containsKey(current)) {
                for (String dependentRecipe : ingredientToRecipes.get(current)) {
                    inDegree.put(dependentRecipe, inDegree.get(dependentRecipe) - 1);
                    if (inDegree.get(dependentRecipe) == 0) {
                        queue.add(dependentRecipe);
                    }
                }
            }
        }

        return result;
    }
}