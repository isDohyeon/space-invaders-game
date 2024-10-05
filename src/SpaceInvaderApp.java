public class SpaceInvaderApp {

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