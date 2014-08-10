package kagamihoge.glassfish4.javaee7.jbatchsample.servlet;

import java.io.IOException;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PartitionTestServlet")
public class PartitionTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PartitionTestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        JobOperator job = BatchRuntime.getJobOperator();

        long id = job.start("sample-job-partition", null);
        System.out.println("id = " + id);
    }
}
