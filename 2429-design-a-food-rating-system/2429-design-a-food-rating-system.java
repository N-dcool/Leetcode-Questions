class FoodRatings {

    HashMap<String, String> foodToCuisine;
    HashMap<String, Integer> foodToRating;
    HashMap<String, TreeSet<String>> cuisinesToFoodPQ;
    int n;

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisinesToFoodPQ = new HashMap<>();
        n = foods.length;

        for(int i=0; i<n; i++){
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            if(!cuisinesToFoodPQ.containsKey(cuisine)){
                cuisinesToFoodPQ.put(cuisine, new TreeSet<String>((foodA, foodB)->{
                    int ratingA = foodToRating.get(foodA);
                    int ratingB = foodToRating.get(foodB);
                return ratingA == ratingB ? foodA.compareTo(foodB) : ratingB - ratingA;
                }));
            }
            cuisinesToFoodPQ.get(cuisine).add(food);

        }
    }
    
    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        TreeSet<String> foodPQ = cuisinesToFoodPQ.get(cuisine);

        foodPQ.remove(food);
        foodToRating.put(food, newRating);
        foodPQ.add(food);
    }
    
    public String highestRated(String cuisine) {
        return cuisinesToFoodPQ.get(cuisine).first();
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */