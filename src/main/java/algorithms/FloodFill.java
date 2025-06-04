package algorithms;

public class FloodFill {

    // Test the algorithm
    public static void main(String[] args) {
        FloodFill ff = new FloodFill();

        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int sr = 1, sc = 1, newColor = 2;

        int[][] result = ff.floodFill(image, sr, sc, newColor);

        // Print result
        for (int[] row : result) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];

        // If the color is already the same, no need to fill
        if (originalColor == newColor) return image;

        fill(image, sr, sc, originalColor, newColor);
        return image;
    }

    private void fill(int[][] image, int r, int c, int originalColor, int newColor) {
        // Check bounds
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length) return;

        // Stop if the color is not the original one
        if (image[r][c] != originalColor) return;

        // Fill the current cell
        image[r][c] = newColor;

        // Recursively fill 4 directions
        fill(image, r + 1, c, originalColor, newColor); // Down
        fill(image, r - 1, c, originalColor, newColor); // Up
        fill(image, r, c + 1, originalColor, newColor); // Right
        fill(image, r, c - 1, originalColor, newColor); // Left
    }
}
