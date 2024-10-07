public class SpaceInvaderApp {

    SpaceInvaderApp() {
        GameView view = new GameView();
        new GameController(view);
    }

    public static void main(String[] args) {
        new SpaceInvaderApp();
    }
}