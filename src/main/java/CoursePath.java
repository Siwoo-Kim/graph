import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class {@code} represents a data type for determining the pre-requirements to take a course.
 *
 * It supports <em>requirementsOf</em> which provides pre-requirement courses to take given course;
 * <em>allCourse</em> provides the topological order of all courses.
 */
public class CoursePath {

    private List<String> courses;
    private Digraph<String> G;

    public CoursePath(String stream, String sp) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(stream)))) {
            G = new BasicDiGraph<>();
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(sp);
                String course = line[0];
                for (int i=1; i<line.length; i++)
                    G.addEdge(Edges.newDirectedEdge(course, line[i]));
            }
            courses = new ArrayList<>();
            Topological<String> tp = new BasicTopological<>(G);
            for (String c: tp.order())
                courses.add(c);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public List<String> allCourse() {
        return new ArrayList<>(courses);
    }

    public List<String> requirementsOf(String course) {
        int index = courses.indexOf(course);
        List<String> requirements = new ArrayList<>();
        for (int i=0; i<index; i++) {
            Search<String> search = new DFSSearch<>(courses.get(i), G);
            if (search.connected(course))
                requirements.add(courses.get(i));
        }
        return requirements;
    }

    public static final String JOBS_TXT = "C:\\Users\\Siwoo\\IdeaProjects\\graph\\src\\main\\resources\\algs4-data" +
            "\\jobs.txt";

    public static void main(String[] args) {
        CoursePath coursePath = new CoursePath(JOBS_TXT, "/");
        for (String s: coursePath.allCourse()) {
            System.out.printf("Req of %s\n", s);
            for (String req: coursePath.requirementsOf(s))
                System.out.printf("\t%s\n", req);
            System.out.println();
        }
    }
}
