package tk.jviewer.dialog;

import org.atmosphere.util.StringEscapeUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import tk.jviewer.services.jc_v1_00.JcResult;
import tk.jviewer.wsc.jc.JcWsClient;

import java.io.ByteArrayInputStream;

import static java.text.MessageFormat.format;

/**
 * Extension of {@link ViewerDialog} for Java rooms.
 */
public class JavaViewerDialog extends ViewerDialog {

    private static final long serialVersionUID = -5057592154732709229L;

    private static final String RESULT_TEMPLATE = "<body>{0}</body>";
    private static final String ERROR_RESULT_TEMPLATE = "<body style=\"color:red\">{0}</body>";

    private String content;
    private String result;
    private boolean errorOccurred;
    private StreamedContent file;

    private JcWsClient jcWsClient;

    /**
     * Sends entered content to JC Web Service and saves a result.
     */
    public void sendContent() {
        try {
            JcResult jcResult = jcWsClient.compileAndExecute(content);
            result = StringEscapeUtils.escapeJavaScript(jcResult.getOutput().replaceAll("\n", "<br/>"));
            errorOccurred = jcResult.isErrorOccurred();
            if (!errorOccurred) {
                file = new DefaultStreamedContent(new ByteArrayInputStream(jcResult.getBinaryResult()), "application/java-archive",
                    "result.jar");
            }
        } catch (Exception e) {
            result = e.getMessage();
            errorOccurred = true;
        }
    }

    /**
     * Clears the current state of dialog.
     */
    public void clear() {
        content = null;
        result = null;
        errorOccurred = false;
        file = null;
    }

    /**
     * Returns the result of content compilation in HTML format.
     * DON'T REMOVE, BECAUSE IT IS USED BY JAVASCRIPT!
     * @return see description.
     */
    public String getFormattedResult() {
        if (result == null) {
            return null;
        }
        if (errorOccurred) {
            return format(ERROR_RESULT_TEMPLATE, result);
        }
        return format(RESULT_TEMPLATE, result);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StreamedContent getFile() {
        return file;
    }

    public boolean isFileAvailable() {
        return file != null;
    }

    //
    // Dependency Injection
    //

    public void setJcWsClient(JcWsClient jcWsClient) {
        this.jcWsClient = jcWsClient;
    }
}
