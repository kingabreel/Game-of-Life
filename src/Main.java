public class Main {
    public static void main(String[] args) {
        try {

            int w, h, g, s;
            w = StringToInt(args[0]); //width
            h = StringToInt(args[1]); //height
            g = StringToInt(args[2]); //generation
            s = StringToInt(args[3]); //speed
            String p = args[4]; //pattern
            int[][] grid;

            if (ValidateValues(w, h, g, s)) {
                //this create an object from the grid class
                Grid game = new Grid(w, h);

                grid = game.FirstGrid(p);
                if (g==0){
                    g = 10000;
                }

                for (int i = 0; i <= g; i++) {

                    int liveCells = game.countAliveCells(grid);
                    System.out.println("width: " + w + " | height: " + h + " | generation: " + i + " | speed: " + s + "ms" + " | population: " + liveCells);

                    game.PrintGrid(grid);
                    Thread.sleep(s);

                    if (liveCells == 0){
                        break;
                    }


                    if (game.StopInfinite(grid)){
                        break;
                    }

                    grid = game.updateGrid(grid);

                }
            } else {

                CommandHelp();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid number of arguments\nPlease check if your input is not greater than the width/height");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int StringToInt(String x) {
        return Integer.parseInt(x);
    }

    public static boolean ValidateValues(int w, int h, int g, int s) {
        return (w == 10 || w == 20 || w == 40 || w == 80) && (h == 10 || h == 20 || h == 40) && (g >= 0) && (s >= 250 && s <= 1000);
    }

    public static void CommandHelp() {
        System.out.println("""

                Try the following command

                -----java Main.java width height maxGenerations speed pattern-----

                Width values = 10, 20, 40, 80
                height values = 10, 20, 40
                max generation = (>0)
                speed values = 250, 1000
                                    
                the pattern should be written as: 1 of the cell is alive, 0 if the cell is dead. To jump a line you just put #
                                    
                an example of pattern 0111010#00010#1000001110#100010#01""");
    }
}