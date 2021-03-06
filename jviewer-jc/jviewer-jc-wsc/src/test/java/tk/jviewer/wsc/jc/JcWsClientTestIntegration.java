package tk.jviewer.wsc.jc;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import tk.jviewer.services.jc_v1_00.JcResult;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:META-INF/jviewer-jc-wsc/client-applicationContext.xml")
public class JcWsClientTestIntegration extends AbstractJUnit4SpringContextTests {

    @Autowired
    private JcWsClient wsClient;

    @Test
    public void test_compileAndExecute_compilationSuccess() {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("public static void main(String[] args) {\n" +
                "        System.out.println(\"TEST FOO\");\n" +
                "    }");
        sourceCode.append("}");
        JcResult result = wsClient.compileAndExecute(sourceCode.toString());
        assertFalse(result.isErrorOccurred());
        assertEquals("TEST FOO", result.getOutput());
    }

    @Test
    public void test_compileAndExecute_compilationFailed() {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("public static void main(String[] args) {\n" +
                "        System.out.println(\"TEST FOO\")\n" +
                "    }");
        sourceCode.append("}");
        JcResult result = wsClient.compileAndExecute(sourceCode.toString());
        assertTrue(result.isErrorOccurred());
        assertThat(result.getOutput(), CoreMatchers.containsString("error: ';' expected"));
    }
}
