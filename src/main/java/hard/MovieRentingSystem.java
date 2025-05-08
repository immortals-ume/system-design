package hard;

import java.util.*;

class MovieRentingSystem {

    // Data structures to hold available movies and rented movies
    private final Map<Integer, PriorityQueue<Movie>> availableMovies; // movie -> (price, shop)
    private final Map<Integer, Set<Integer>> rentedMovies; // shop -> set of rented movies
    private final PriorityQueue<RentedMovie> rentedMoviesReport; // (price, shop, movie)

    // Constructor to initialize the system with n shops and the movie entries
    public MovieRentingSystem(int n, int[][] entries) {
        availableMovies = new HashMap<>();
        rentedMovies = new HashMap<>();
        rentedMoviesReport = new PriorityQueue<>(Comparator.comparingInt((RentedMovie rm) -> rm.price)
                .thenComparingInt(rm -> rm.shop)
                .thenComparingInt(rm -> rm.movie));

        for (int[] entry : entries) {
            int shop = entry[0];
            int movie = entry[1];
            int price = entry[2];

            availableMovies.putIfAbsent(movie, new PriorityQueue<>(Comparator.comparingInt((Movie m) -> m.price)
                    .thenComparingInt(m -> m.shop)));
            availableMovies.get(movie).offer(new Movie(price, shop));
        }
    }

    // Search for the cheapest 5 shops with an unrented copy of the movie
    public List<List<Integer>> search(int movie) {
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;

        for (Movie movieEntry : availableMovies.getOrDefault(movie, new PriorityQueue<>())) {
            if (!rentedMovies.containsKey(movieEntry.shop) || !rentedMovies.get(movieEntry.shop).contains(movie)) {
                result.add(Arrays.asList(movieEntry.shop, movie, movieEntry.price));
                count++;
                if (count == 5) break;
            }
        }
        return result;
    }

    // Rent a movie from a given shop
    public void rent(int shop, int movie) {
        // Remove from available list
        PriorityQueue<Movie> movieQueue = availableMovies.get(movie);
        if (movieQueue != null) {
            Iterator<Movie> iterator = movieQueue.iterator();
            while (iterator.hasNext()) {
                Movie m = iterator.next();
                if (m.shop == shop) {
                    iterator.remove();
                    break;
                }
            }
        }

        // Add to rented list
        rentedMovies.putIfAbsent(shop, new HashSet<>());
        rentedMovies.get(shop).add(movie);

        // Add to the rented movie report
        for (Movie m : availableMovies.get(movie)) {
            if (m.shop == shop) {
                rentedMoviesReport.offer(new RentedMovie(m.price, shop, movie));
                break;
            }
        }
    }

    // Drop off a rented movie
    public void drop(int shop, int movie) {
        // Remove from rented list
        Set<Integer> rented = rentedMovies.get(shop);
        if (rented != null) {
            rented.remove(movie);
        }

        // Return to available movies
        int price = -1;
        for (RentedMovie rm : rentedMoviesReport) {
            if (rm.shop == shop && rm.movie == movie) {
                price = rm.price;
                rentedMoviesReport.remove(rm);
                break;
            }
        }

        if (price != -1) {
            availableMovies.putIfAbsent(movie, new PriorityQueue<>(Comparator.comparingInt((Movie m) -> m.price)
                    .thenComparingInt(m -> m.shop)));
            availableMovies.get(movie).offer(new Movie(price, shop));
        }
    }

    // Generate the report for the cheapest 5 rented movies
    public List<List<Integer>> report() {
        List<List<Integer>> result = new ArrayList<>();
        int count = 0;

        while (!rentedMoviesReport.isEmpty() && count < 5) {
            RentedMovie rm = rentedMoviesReport.poll();
            result.add(Arrays.asList(rm.shop, rm.movie));
            count++;
        }
        return result;
    }

    // Movie class to hold movie details in an available list
    private static class Movie {
        int price;
        int shop;

        public Movie(int price, int shop) {
            this.price = price;
            this.shop = shop;
        }
    }

    // RentedMovie class to hold rented movie details for report generation
    private static class RentedMovie {
        int price;
        int shop;
        int movie;

        public RentedMovie(int price, int shop, int movie) {
            this.price = price;
            this.shop = shop;
            this.movie = movie;
        }
    }
}