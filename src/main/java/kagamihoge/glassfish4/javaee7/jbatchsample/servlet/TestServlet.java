package kagamihoge.glassfish4.javaee7.jbatchsample.servlet;

import java.io.IOException;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        JobOperator job = BatchRuntime.getJobOperator();

        long id = job.start("sample-job", null);
        System.out.println("id = " + id);

        long id2 = job.start("sample-job-named", null);
        System.out.println("id2 = " + id2);
    }
}
