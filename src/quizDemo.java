import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

 class Question {
    private String question;
    private String[] options;
    private  int correctAnswerIndex;
    private boolean answeredCorrectly;

    public Question(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.answeredCorrectly = false;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
    public boolean isAnsweredCorrectly() {

        return answeredCorrectly;
    }
    public void setAnsweredCorrectly(boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }
}

public class quizDemo {
    private static int score = 0;
    private static Timer timer = new Timer();
    private static boolean timeUp = false;

    private static void displayResults(Question[] questions) throws InterruptedException {
        System.out.println("\nQuiz over! Your final score is: " + score + "/" + questions.length);
        Thread.sleep(1000);
        System.out.println("\nSummary:");
        for (Question question : questions) {
            System.out.println(question.getQuestion());
            Thread.sleep(1000);

            System.out.println("Correct answer: " + question.getOptions()[question.getCorrectAnswerIndex()]);
            Thread.sleep(1000);

            if (question.isAnsweredCorrectly()) {
                System.out.println("You answered correctly.\n");
            } else {
                System.out.println("You answered incorrectly.\n");
            Thread.sleep(1000);
    }
            
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

       System.out.println("-------------------------QUIZ APP-----------------------------------");
       Thread.sleep(1000);
       System.out.print("Starting");
       Thread.sleep(550);
       System.out.print(".");
       Thread.sleep(550);
       System.out.print(".");
       Thread.sleep(550);
       System.out.println(".");


        Question[] questions = {
            new Question("1.What is the capital of India?", new String[]{"1. Berlin", "2. NewDelhi", "3. Paris", "4. Rome"}, 1),
            new Question("2.Who wrote 'Hamlet'?", new String[]{"1. Charles Dickens", "2. J.K. Rowling", "3. William Shakespeare", "4. Mark Twain"}, 2),
            new Question("3.What is the chemical symbol for water?", new String[]{"1. CO2", "2. H2O", "3. NaCl", "4. O2"}, 1),
            new Question("4.Who is the current Prime Minister of India", new String[]{"1. Rajedraprasad", "2. Narendra Modi", "3. Ranveer","4. Dhrowpadhi"}, 1),
            new Question("5.What is the AI ", new String[]{"1. Artificial Intelligence", "2. Agriculture IT", "3. Articulture Industry","4. Agreement India"}, 0)


        };

      
        for (Question question : questions) {
            timeUp = false;
            System.out.println(question.getQuestion());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }

            startTimer(10);  

            String answer = "";
            while (!timeUp && scan.hasNext()) {
                answer = scan.next();
                if (timeUp) break;
                try {
                    int answerIndex = Integer.parseInt(answer) - 1;
                    if (answerIndex == question.getCorrectAnswerIndex()) {
                        score++;
                        question.setAnsweredCorrectly(true);
                    }
                    else{
                        question.setAnsweredCorrectly(false);
                    }
                   
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            if (timeUp) {
                System.out.println("Time's up!");
            }

            timer.cancel(); 
            timer = new Timer(); 
        }

        // Display final score
        displayResults(questions);
    }

    private static void startTimer(int seconds) {
        timer.schedule(new TimerTask() {
       
            public void run() {
                timeUp = true;
            }
        }, seconds * 1000);
    }
    
    
}
