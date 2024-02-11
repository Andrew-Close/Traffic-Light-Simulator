import java.util.Scanner;

abstract class SocialNetwork {

    public void connect() {
        // write your code here ...
        logIn();
        postMessage();
        logOut();
    }

      // write your code here ...
    public abstract void logIn();
    public abstract void postMessage();
    public abstract void logOut();
}

class Instagram extends SocialNetwork {
     // write your code here ...
    @Override
    public void logIn() {
        System.out.println("Log into Instagram");
    }
    @Override
    public void postMessage() {
        System.out.println("Post: Hello, Instagram!");
    }
    @Override
    public void logOut() {
        System.out.println("Log out of Instagram");
    }
}


class Facebook extends SocialNetwork {
  // write your code here ...
    @Override
    public void logIn() {
      System.out.println("Log into Facebook");
    }
    @Override
    public void postMessage() {
        System.out.println("Post: Hello, Facebook!");
    }
    @Override
    public void logOut() {
        System.out.println("Log out of Facebook");
    }
}

// Do not change the code below
class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String type = scanner.nextLine();
        scanner.close();
        SocialNetwork network = null;
        if ("facebook".equalsIgnoreCase(type)) {
            network = new Facebook();
        } else if ("instagram".equalsIgnoreCase(type)) {
            network = new Instagram();
        } else {
            System.out.println("Error!");
            System.exit(0);
        }
        network.connect();
    }
}