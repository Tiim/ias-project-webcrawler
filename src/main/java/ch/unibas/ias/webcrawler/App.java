/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ch.unibas.ias.webcrawler;

import ch.unibas.ias.webcrawler.webcrawler.WebCrawler;

public class App {

    private static final int THREADS = 30;


    public static void run(String[] args) {
        try {
            WebCrawler.main(args);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws Exception {

        Thread[] t = new Thread[THREADS-1];

        for(int i = 0; i < THREADS-1; i++) {
            t[i] = new Thread(() -> run(args));
            t[i].start();
        }
        WebCrawler.main(args);

    }
}
