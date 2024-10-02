public class SpaceInvaderApp {

    private static int score;
    GameView view;
    GameController controller;

    SpaceInvaderApp() {
        view = new GameView();
        controller = new GameController(view);
        view.setController(controller);
    }

    public static void main(String[] args) {
        new SpaceInvaderApp();
    }
}